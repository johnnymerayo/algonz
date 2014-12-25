package es.algonz.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRSwapFile;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.algonz.controller.utils.CombosUtils;
import es.algonz.domain.ActuacionVO;
import es.algonz.domain.DocumentoVO;
import es.algonz.domain.FileMeta;
import es.algonz.domain.SiniestroVO;
import es.algonz.domain.UsuarioVO;
import es.algonz.service.ActuacionManager;
import es.algonz.service.DocumentoManager;
import es.algonz.service.SiniestroManager;
import es.algonz.validator.ActuacionValidator;
import es.algonz.web.utils.ConstantesKeys;
import es.algonz.web.utils.RequestKeys;

@Controller
@RequestMapping("actuaciones")
public class ActuacionController {
	@Autowired
	private ActuacionManager actuacionManager;
	@Autowired
	private SiniestroManager siniestroManager;
	@Autowired
	private CombosUtils combosUtils;


	private static final Log LOGGER = LogFactory.getLog(ActuacionController.class);
	
	@Autowired
	private DocumentoManager documentoManager;

	@InitBinder(RequestKeys.ACTUACION)
	protected void actuacion(WebDataBinder binder) {
		binder.setValidator(new ActuacionValidator());
		
		// Para que salga la fecha formateada al entrar en los detalles
		// y permitir guardar vacias
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, true));
	}

	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public String listado(Model model, HttpSession session) {		
		List<ActuacionVO> listaActuaciones = null;
		listaActuaciones = actuacionManager.getActuaciones(null);
		model.addAttribute(RequestKeys.LISTA_ACTUACIONES,
				listaActuaciones);
		return "listadoActuaciones";
	}

	@RequestMapping(value = "/listado", method = RequestMethod.POST)
	public String listadoPost(Model model, HttpSession session) {
		return listado(model, session);
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session) {
		if (id != null) {	
				ActuacionVO actuacion  = actuacionManager.findById(new Integer (id).intValue());
				
				model.addAttribute(RequestKeys.ACTUACION, actuacion);

				// Cargamos el combo de estados
				model.addAttribute("estadosCombo", combosUtils.loadEstados());
				
			
		}
		return "detalleActuacion";
	}

	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session, RedirectAttributes redirectAttrs) {

		
		String idSiniestro = "";
		if (id != null) {
			ActuacionVO actuacion  = actuacionManager.findById(new Integer (id).intValue());
				
			idSiniestro = actuacion.getSiniestro().getCnSiniestro().toString();	
			try{
			actuacionManager.remove(actuacion);
		}catch (Exception e){
			redirectAttrs.addFlashAttribute(RequestKeys.ERROR, "No se ha podido eliminar el elemento seleccionado.");
			return "redirect:/action/siniestros/editar?id=" + idSiniestro;
		}
			redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE,
						"Eliminado correctamente");
		}

		return "redirect:/action/siniestros/editar?id=" + idSiniestro;
	}

	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Model model, HttpSession session, @RequestParam(RequestKeys.CODIGO_SINIESTRO) String codSiniestro) {
		ActuacionVO actuacion = new ActuacionVO();	
		if(!GenericValidator.isBlankOrNull(codSiniestro)){
			SiniestroVO siniestro = siniestroManager.findById(new Integer (codSiniestro));
			actuacion.setSiniestro(siniestro);
		}
		model.addAttribute(RequestKeys.ACTUACION, actuacion);

		// Cargamos el combo de estados
		model.addAttribute("estadosCombo", combosUtils.loadEstados());
		return "detalleActuacion";
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(
			@Valid @ModelAttribute(RequestKeys.ACTUACION) ActuacionVO actuacion,
			BindingResult binding, Model model, RedirectAttributes redirectAttrs) {
		if (binding.hasErrors()) {
			model.addAttribute(RequestKeys.ACTUACION, actuacion);

			// Cargamos el combo de estados
			model.addAttribute("estadosCombo", combosUtils.loadEstados());
			return "detalleActuacion";
		}
		if (actuacion != null) {
			if (actuacion.getFeCierre() == null && ConstantesKeys.ESTADO_CERRADO.equalsIgnoreCase(actuacion.getEstado().getCnEstado().toString())){
				actuacion.setFeCierre(new Date());
			}
			
			if (actuacion.getCnActuacion() != null && !StringUtils.isBlank(actuacion.getCnActuacion().toString()))
				actuacionManager.merge(actuacion);
			else {
				actuacionManager.persist(actuacion);
			}

			redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");
		}

		return "redirect:/action/siniestros/editar?id=" + actuacion.getSiniestro().getCnSiniestro();
	}
	

	@RequestMapping(value = "/uploadDocument", method = RequestMethod.POST)
	public  @ResponseBody LinkedList<FileMeta> uploadDocument(Model model, HttpSession session, MultipartRequest request, @RequestParam(RequestKeys.CODIGO_ACTUACION) String codActuacion) {
		
		LinkedList<FileMeta> ficherosSubidos = new LinkedList<FileMeta>();
		
		if(!GenericValidator.isBlankOrNull(codActuacion)){
			ActuacionVO actuacion = actuacionManager.findById(new Integer (codActuacion));
		
			DocumentoVO documento = new DocumentoVO();
			documento.setActuacion(actuacion);
			ficherosSubidos = documentoManager.uploadDocument(request, documento);
		}
		
		return ficherosSubidos;
	}
	
	
	@RequestMapping(value = "/downloadDocument", method = RequestMethod.GET)
	public void downloadDocument(HttpServletResponse response,  @RequestParam(RequestKeys.ID) String idDocumento) {
		
		documentoManager.downloadDocument(response, idDocumento);
		
	}
	

	@RequestMapping(value = "/deleteDocument", method = RequestMethod.GET)
	public String deleteDocument(Model model,
			@RequestParam(RequestKeys.CODIGO_ACTUACION) String codActuacion, @RequestParam(RequestKeys.ID) String idDocumento, HttpSession session, RedirectAttributes redirectAttrs) {
		
		documentoManager.deleteDocument(idDocumento);

		redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE, "Documento eliminado correctamente");
		
		return "redirect:/action/actuaciones/editar?id=" + codActuacion;
		
		
	}


    public static JasperDesign jasperDesign;
    public static JasperPrint jasperPrint;
    public static JasperReport jasperReport;
    public static String reportTemplateUrl = "actuacion.jrxml";
    
	@RequestMapping(value = "/informeActuacion", method = RequestMethod.GET)
	public String informeActuacion(Model model, @RequestParam(RequestKeys.CODIGO_ACTUACION) String codActuacion, HttpSession session, HttpServletResponse response) {

		try
        {
        	BufferedInputStream resourceAsStream = ((BufferedInputStream) Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(reportTemplateUrl));
            //get report file and then load into jasperDesign
            jasperDesign = JRXmlLoader.load(resourceAsStream);
            //compile the jasperDesign
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            
    		
  		   List<ActuacionVO> dataReport = new LinkedList<ActuacionVO>();
            
            if (codActuacion != null) {	
            	ActuacionVO actuacion  = actuacionManager.findById(new Integer (codActuacion).intValue());
  				dataReport.add(actuacion);
            }
            
        	
            JRSwapFileVirtualizer virtualizer = null;
            try {
                JRSwapFile swapFile = new JRSwapFile("/tmp", 1024, 100);
                virtualizer = new JRSwapFileVirtualizer(50, swapFile, true);

                Map params = new HashMap();
                params.put(JRParameter.REPORT_VIRTUALIZER, virtualizer);
                

                BufferedImage image = ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("../../images/ALGONZ-logo.jpg"));
                params.put("logo", image );
              
                //fill the ready report with data and parameter
                jasperPrint = JasperFillManager.fillReport(jasperReport, params,  new JRBeanCollectionDataSource(dataReport));
                //view the report using JasperViewer
                // JasperViewer.viewReport(jasperPrint);
                 
            }
            finally {
                if (virtualizer != null) virtualizer.cleanup();
            }    
           

         // Set our response properties
         // Here you can declare a custom filename
         String fileName = "UserReport.pdf";
         response.setHeader("Content-Disposition", "inline; filename="+ fileName);

         // Set content type
         response.setContentType("application/pdf");


         // Export is most important part of reports
         JRPdfExporter exporter = new JRPdfExporter(); 
         exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
         exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
         exporter.exportReport();
         
         


         return this.editar(model, codActuacion, session);
        }
        catch (JRException e)
        {
            LOGGER.error(e.getMessage(),e);
            return this.editar(model, codActuacion, session);
        } catch (IOException e) {
			// TODO Auto-generated catch block
        	LOGGER.error(e.getMessage(),e);
            return this.editar(model, codActuacion, session);
		} 
		
	}
	
	
	

	
	
}