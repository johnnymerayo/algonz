package es.algonz.controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
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
import org.apache.commons.validator.GenericValidator;
import org.springframework.beans.factory.annotation.Autowired;
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

import es.algonz.controller.utils.ControladorUtils;
import es.algonz.domain.ComunidadVO;
import es.algonz.domain.DocumentoVO;
import es.algonz.domain.FileMeta;
import es.algonz.service.ComunidadManager;
import es.algonz.service.DocumentoManager;
import es.algonz.validator.ComunidadValidator;
import es.algonz.web.utils.RequestKeys;

@Controller
@RequestMapping("comunidades")
public class ComunidadController {
	@Autowired
	private ComunidadManager comunidadManager;

	@Autowired
	private DocumentoManager documentoManager;
	
	@Autowired
	private ControladorUtils controladorUtils;

	@InitBinder(RequestKeys.COMUNIDAD)
	protected void comunidad(WebDataBinder binder) {
		binder.setValidator(new ComunidadValidator());
	}

	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public String listado(Model model, HttpSession session) {		
		List<ComunidadVO> listaComunidades = null;
		listaComunidades = comunidadManager.getComunidades(null);
		model.addAttribute(RequestKeys.LISTA_COMUNIDADES,
				listaComunidades);
		return "listadoComunidades";
	}

	@RequestMapping(value = "/listado", method = RequestMethod.POST)
	public String listadoPost(Model model, HttpSession session) {
		return listado(model, session);
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session) {
		if (id != null) {	
				ComunidadVO comunidad  = comunidadManager.findById(new Integer (id).intValue());
				comunidad.setRepresentantes(comunidadManager.getRepresentantes(comunidad.getCnComunidad()));
				model.addAttribute(RequestKeys.COMUNIDAD, comunidad);
			
		}
		return "detalleComunidad";
	}

	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session) {
		if (id != null) {
			ComunidadVO comunidad  = comunidadManager.findById(new Integer (id).intValue());
			
			try{
						comunidadManager.remove(comunidad);
			}catch (Exception e){
				model.addAttribute(RequestKeys.ERROR, "No se ha podido eliminar el elemento seleccionado.");
				return "forward:/action/comunidades/listado";
			}
				
				model.addAttribute(RequestKeys.MESSAGE,
						"Eliminado correctamente");
		}
		return "forward:/action/comunidades/listado";
	}

	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Model model, HttpSession session) {
		ComunidadVO comunidad = new ComunidadVO();		
		model.addAttribute(RequestKeys.COMUNIDAD, comunidad);
		return "detalleComunidad";
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(
			@Valid @ModelAttribute(RequestKeys.COMUNIDAD) ComunidadVO comunidad,
			BindingResult binding, Model model) {
		if (binding.hasErrors()) {
			model.addAttribute(RequestKeys.COMUNIDAD, comunidad);
			return "detalleComunidad";
		}
		if (comunidad != null) {
			if (comunidad.getCnComunidad() != null && !StringUtils.isBlank(comunidad.getCnComunidad().toString()))
				comunidadManager.merge(comunidad);
			else {
				comunidadManager.persist(comunidad);
			}
			model.addAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");
		}
		return "forward:/action/comunidades/listado";
	}
	
	

	@RequestMapping(value = "/uploadDocument", method = RequestMethod.POST)
	public @ResponseBody LinkedList<FileMeta> uploadDocument(Model model, HttpSession session, MultipartRequest request, @RequestParam(RequestKeys.CODIGO_COMUNIDAD) String codComunidad) {

		LinkedList<FileMeta> ficherosSubidos = new LinkedList<FileMeta>();
		
		if(!GenericValidator.isBlankOrNull(codComunidad)){
			ComunidadVO comunidad = comunidadManager.findById(new Integer (codComunidad));

			DocumentoVO documento = new DocumentoVO();
			documento.setComunidad(comunidad);
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
			@RequestParam(RequestKeys.CODIGO_COMUNIDAD) String codComunidad, @RequestParam(RequestKeys.ID) String idDocumento, HttpSession session, RedirectAttributes redirectAttrs) {
		
		documentoManager.deleteDocument(idDocumento);

		redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE, "Documento eliminado correctamente");
		
		return "redirect:/action/comunidades/editar?id=" + codComunidad;
		
		
	}
	
	
	

    public static JasperDesign jasperDesign;
    public static JasperPrint jasperPrint;
    public static JasperReport jasperReport;
    public static String reportTemplateUrl = "comunidad.jrxml";
    
	@RequestMapping(value = "/informeComunidad", method = RequestMethod.GET)
	public String informeComunidad(Model model, @RequestParam(RequestKeys.CODIGO_COMUNIDAD) String codComunidad, HttpSession session, HttpServletResponse response) {

		try
        {
        	BufferedInputStream resourceAsStream = ((BufferedInputStream) Thread.currentThread().getContextClassLoader()
                    .getResourceAsStream(reportTemplateUrl));
            //get report file and then load into jasperDesign
            jasperDesign = JRXmlLoader.load(resourceAsStream);
            //compile the jasperDesign
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            
    		
  		   List<ComunidadVO> dataReport = new LinkedList<ComunidadVO>();
            
            if (codComunidad != null) {	
  				ComunidadVO comunidad  = comunidadManager.findById(new Integer (codComunidad).intValue());
  				comunidad.setRepresentantes(comunidadManager.getRepresentantes(comunidad.getCnComunidad()));
  				dataReport.add(comunidad);
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
         
         
//         JRRtfExporter exporter2= new JRRtfExporter();

//         // Set content type
//         fileName = "UserReport.rtf";
//         response.setHeader("Content-Disposition", "inline; filename="+ fileName);
//         response.setContentType("application/rtf");
//         exporter2.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//         exporter2.setParameter(JRExporterParameter.OUTPUT_STREAM, response.getOutputStream());
//         exporter2.exportReport();
//         


         return this.editar(model, codComunidad, session);
        }
        catch (JRException e)
        {
            e.printStackTrace();

            return this.editar(model, codComunidad, session);
        } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

            return this.editar(model, codComunidad, session);
		} 
		
	}

}