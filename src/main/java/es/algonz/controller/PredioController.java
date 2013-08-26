package es.algonz.controller;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.algonz.controller.utils.CombosUtils;
import es.algonz.domain.PortalVO;
import es.algonz.domain.PredioVO;
import es.algonz.service.PortalManager;
import es.algonz.service.PredioManager;
import es.algonz.validator.PredioValidator;
import es.algonz.web.utils.RequestKeys;

@Controller
@RequestMapping("predios")
public class PredioController implements Printable{
	@Autowired
	private PredioManager predioManager;
	@Autowired
	private PortalManager portalManager;
	@Autowired
	private CombosUtils comboUtils;

	@InitBinder(RequestKeys.PREDIO)
	protected void predio(WebDataBinder binder) {
		binder.setValidator(new PredioValidator());
	}

	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public String listado(Model model, HttpSession session) {		
		List<PredioVO> listaPredios = null;
		listaPredios = predioManager.getPredios(null);
		model.addAttribute(RequestKeys.LISTA_PREDIOS,
				listaPredios);
		return "listadoPredios";
	}

	@RequestMapping(value = "/listado", method = RequestMethod.POST)
	public String listadoPost(Model model, HttpSession session) {
		return listado(model, session);
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session) {
		if (id != null) {	
				PredioVO predio  = predioManager.findById(new Integer (id).intValue());
				
				model.addAttribute(RequestKeys.PREDIO, predio);
				
				//Cargamos el combo de plantas
				model.addAttribute("plantasCombo", comboUtils.loadPlantas());
				//Cargamos el combo de representantes
				model.addAttribute("tiposRepresentanteCombo", comboUtils.loadTiposRrepresentante());
			
		}
		return "detallePredio";
	}

	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session, RedirectAttributes redirectAttrs) {
		PredioVO predio = new PredioVO();
		if (id != null) {
			predio  = predioManager.findById(new Integer (id).intValue());
				predioManager.remove(predio);
				model.addAttribute(RequestKeys.MESSAGE,
						"Eliminado correctamente");
				redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE, "Eliminado correctamente");
		}
		//return "forward:/action/predios/listado";
		return "redirect:/action/portales/editar?id=" + predio.getPortal().getCnPortal();
	}

	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Model model, HttpSession session) {
		PredioVO predio = new PredioVO();		
		model.addAttribute(RequestKeys.PREDIO, predio);
		//Cargamos el combo de plantas
		model.addAttribute("plantasCombo", comboUtils.loadPlantas());
		//Cargamos el combo de representantes
		model.addAttribute("tiposRepresentanteCombo", comboUtils.loadTiposRrepresentante());
		return "detallePredio";
	}
	
	@RequestMapping(value = "/nuevoPredio", method = RequestMethod.GET)
	public String nuevo(Model model, HttpSession session, @RequestParam(RequestKeys.CODIGO_PORTAL) String codPortal) {
		PredioVO predio = new PredioVO();			
		if(!GenericValidator.isBlankOrNull(codPortal)){
			PortalVO portal = portalManager.findById(new Integer (codPortal));
			predio.setPortal(portal);
		}
		
		model.addAttribute(RequestKeys.PREDIO, predio);
		//Cargamos el combo de plantas
		model.addAttribute("plantasCombo", comboUtils.loadPlantas());
		//Cargamos el combo de representantes
		model.addAttribute("tiposRepresentanteCombo", comboUtils.loadTiposRrepresentante());
		return "detallePredio";
	}
	
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(
			@Valid @ModelAttribute(RequestKeys.PREDIO) PredioVO predio,
			BindingResult binding, Model model, RedirectAttributes redirectAttrs) {
		if (binding.hasErrors()) {
			model.addAttribute(RequestKeys.PREDIO, predio);
			//Cargamos el combo de plantas
			model.addAttribute("plantasCombo", comboUtils.loadPlantas());
			//Cargamos el combo de representantes
			model.addAttribute("tiposRepresentanteCombo", comboUtils.loadTiposRrepresentante());
			return "detallePredio";
		}
		if (predio != null) {
			
			// Para permitir que guarde sin representante
			if (predio.getTipoRepresentante() == null  || predio.getTipoRepresentante().getCnTipoRepresentante() == null || GenericValidator.isBlankOrNull(predio.getTipoRepresentante().getCnTipoRepresentante().toString())){
				predio.setTipoRepresentante(null);
			}
			
			
			
			if (predio.getCnPredio() != null && !StringUtils.isBlank(predio.getCnPredio().toString()))
				predioManager.merge(predio);
			else {
				predioManager.persist(predio);
			}
			//model.addAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");
			redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");
		}
		//return "forward:/action/predios/listado";
		return "redirect:/action/portales/editar?id=" + predio.getPortal().getCnPortal();
	}
	
	
	@RequestMapping(value = "/imprimirPropietario", method = RequestMethod.GET)
	public String imprimirPropietario(Model model, HttpSession session, @RequestParam(RequestKeys.CODIGO_PREDIO) String codPredio, RedirectAttributes redirectAttrs) {
		
	
		if (!GenericValidator.isBlankOrNull(codPredio)) {
			
			 // Create an object that will hold all print parameters, such as
		      // page size, printer resolution. In addition, it manages the print
		      // process (job).
		      PrinterJob job = PrinterJob.getPrinterJob();

		      // It is first called to tell it what object will print each page.
		      job.setPrintable(new PredioController());
		      
		      // Then it is called to display the standard print options dialog.
		      if (job.printDialog())
		      {
		    	  // Predio con la información a imprimir
		    	  predioPrint = predioManager.findById(new Integer(codPredio));
										
		      try { job.print(); }
		         catch (PrinterException e) { System.out.println(e); }
		      }
			
		}

		return "redirect:/action/predios/editar?id=" + codPredio;
	}
	
	public static PredioVO predioPrint;


	@Override
	 public int print (Graphics g1, PageFormat f, int pageIndex)
	   {
		   Graphics2D g = (Graphics2D) g1; 		   
		
		   if (pageIndex > 0) {
		         return NO_SUCH_PAGE;
		    }

			
			
			Font font = new Font("Serif", Font.PLAIN, 10);
			FontMetrics metrics = g.getFontMetrics(font);
			int lineHeight = metrics.getHeight()+2;
			
			Paper paper = new Paper();
			paper.setSize(312, 652); 
			
			paper.setImageableArea(20, 20, 250, 600);
			f.setPaper(paper);
			f.setOrientation(PageFormat.PORTRAIT);
					
		
		     //--- Translate the origin to 0,0 for the top left corner
		      g.translate(f.getImageableX(), f
		          .getImageableY());

		      //--- Set the default drawing color to black
		      g.setPaint(Color.black);
			
		      Rectangle2D.Double border = new Rectangle2D.Double(0, 0, f
		          .getImageableWidth(), f.getImageableHeight());
		      g.draw(border);
			
			
		    int x = 130;  
			int y = 270; 
			

		        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		                RenderingHints.VALUE_ANTIALIAS_ON);
			
			

			if (GenericValidator.isBlankOrNull(predioPrint.getTerceroByCnPropietario().getTeDireccionSecundaria())){
			// Imprimimos la dirección primaria
				
				label_line(g, x, y, predioPrint.getTerceroByCnPropietario().getNombreCompleto());
				x -= lineHeight;
				label_line(g, x, y, predioPrint.getPortal().getTeCalle() + " " 
						 + predioPrint.getPortal().getTeNombre() + "     " + predioPrint.getPlanta().getTePlanta() + " - " + predioPrint.getTePredio());
				x -= lineHeight;
				label_line(g, x, y, predioPrint.getPortal().getComunidad().getTeCp() + " GIJÓN");
				x -= lineHeight;
				label_line(g, x, y, "ASTURIAS - ESPAÑA");
				x -= lineHeight;
				
				
				
			}else{
			// Imprimimos la dirección secundaria
				label_line(g, x, y, predioPrint.getTerceroByCnPropietario().getNombreCompleto());
			    String direccion = predioPrint.getTerceroByCnPropietario().getTeDireccionSecundaria();
			    String[] labels = direccion.split("\n");
			    

				int start = 0;
				int end   = labels.length;
			    
				for (int line=start; line<end; line++) {
			    x -= lineHeight;
				label_line(g, x, y, labels[line]);
			}
			}
		



		    return PAGE_EXISTS;
			
	   }
	
	
	
	

	  
	  
	  private void label_line(Graphics g, double x, double y, String label) {

		     Graphics2D g2D = (Graphics2D)g;
		     
		     double theta = 90 * java.lang.Math.PI/180;

		    // Create a rotation transformation for the font.
		    AffineTransform fontAT = new AffineTransform();

		    // get the current font
		    Font theFont = g2D.getFont();

		    // Derive a new font using a rotatation transform
		    fontAT.rotate(theta);
		    Font theDerivedFont = theFont.deriveFont(fontAT);

		    // set the derived font in the Graphics2D context
		    g2D.setFont(theDerivedFont);

		    // Render a string using the derived font
		    g2D.drawString(label, (int)x, (int)y);

		    // put the original font back
		    g2D.setFont(theFont);
		}

}
