package es.algonz.controller;

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

import es.algonz.controller.utils.ControladorUtils;
import es.algonz.domain.ComunidadVO;
import es.algonz.domain.PortalVO;
import es.algonz.service.ComunidadManager;
import es.algonz.service.PortalManager;
import es.algonz.validator.PortalValidator;
import es.algonz.web.utils.RequestKeys;

@Controller
@RequestMapping("portales")
public class PortalController {
	@Autowired
	private PortalManager portalManager;
	@Autowired
	private ComunidadManager comunidadManager;
	@Autowired
	private ControladorUtils controladorUtils;

	@InitBinder(RequestKeys.PORTAL)
	protected void portal(WebDataBinder binder) {
		binder.setValidator(new PortalValidator());
	}

	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public String listado(Model model, HttpSession session) {		
		List<PortalVO> listaPortales = null;
		listaPortales = portalManager.getPortales(null);
		model.addAttribute(RequestKeys.LISTA_PORTALES,
				listaPortales);
		return "listadoPortales";
	}

	@RequestMapping(value = "/listado", method = RequestMethod.POST)
	public String listadoPost(Model model, HttpSession session) {
		return listado(model, session);
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session) {
		if (id != null) {	
				PortalVO portal  = portalManager.findById(new Integer (id).intValue());
				
				model.addAttribute(RequestKeys.PORTAL, portal);
			
		}
		return "detallePortal";
	}

	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session, RedirectAttributes redirectAttrs) {
		PortalVO portal = new PortalVO();
		if (id != null) {
			 portal  = portalManager.findById(new Integer (id).intValue());
			 try{
				portalManager.remove(portal);
		}catch (Exception e){
			redirectAttrs.addFlashAttribute(RequestKeys.ERROR, "No se ha podido eliminar el elemento seleccionado.");
			return "redirect:/action/comunidades/editar?id=" + portal.getComunidad().getCnComunidad();
		}
				model.addAttribute(RequestKeys.MESSAGE,
						"Eliminado correctamente");
				redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE, "Eliminado correctamente");
			}
			//return "forward:/action/portales/listado";
			return "redirect:/action/comunidades/editar?id=" + portal.getComunidad().getCnComunidad();
	}

	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Model model, HttpSession session) {
		PortalVO portal = new PortalVO();		
		model.addAttribute(RequestKeys.PORTAL, portal);
		return "detallePortal";
	}

	@RequestMapping(value = "/nuevoPortal", method = RequestMethod.GET)
	public String nuevo(Model model, HttpSession session, @RequestParam(RequestKeys.CODIGO_COMUNIDAD) String codComunidad) {
		PortalVO portal = new PortalVO();		
		if(!GenericValidator.isBlankOrNull(codComunidad)){
			ComunidadVO comunidad = comunidadManager.findById(new Integer (codComunidad));
			portal.setComunidad(comunidad);
		}
		
		model.addAttribute(RequestKeys.PORTAL, portal);
		return "detallePortal";
	}
	
	
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(
			@Valid @ModelAttribute(RequestKeys.PORTAL) PortalVO portal,
			BindingResult binding, Model model, RedirectAttributes redirectAttrs) {
		if (binding.hasErrors()) {
			model.addAttribute(RequestKeys.PORTAL, portal);
			return "detallePortal";
		}
		if (portal != null) {
			if (portal.getCnPortal() != null && !StringUtils.isBlank(portal.getCnPortal().toString()))
				portalManager.merge(portal);
			else {
				portalManager.persist(portal);
			}
			//model.addAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");
			redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");
		}
		//return "forward:/action/portales/listado";
		return "redirect:/action/comunidades/editar?id=" + portal.getComunidad().getCnComunidad();
		}

}