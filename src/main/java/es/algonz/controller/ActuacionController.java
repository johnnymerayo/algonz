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

import es.algonz.controller.utils.CombosUtils;
import es.algonz.domain.ActuacionVO;
import es.algonz.domain.SiniestroVO;
import es.algonz.service.ActuacionManager;
import es.algonz.service.SiniestroManager;
import es.algonz.validator.ActuacionValidator;
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

	@InitBinder(RequestKeys.ACTUACION)
	protected void actuacion(WebDataBinder binder) {
		binder.setValidator(new ActuacionValidator());
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
			@RequestParam(RequestKeys.ID) String id, HttpSession session) {

		
		String idSiniestro = "";
		if (id != null) {
			ActuacionVO actuacion  = actuacionManager.findById(new Integer (id).intValue());
				
			idSiniestro = actuacion.getSiniestro().getCnSiniestro().toString();	
			actuacionManager.remove(actuacion);
				model.addAttribute(RequestKeys.MESSAGE,
						"Eliminado correctamente");
		}
//		return "forward:/action/actuaciones/listado";

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
			if (actuacion.getCnActuacion() != null && !StringUtils.isBlank(actuacion.getCnActuacion().toString()))
				actuacionManager.merge(actuacion);
			else {
				actuacionManager.persist(actuacion);
			}
			//model.addAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");

			redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");
		}
		//return "forward:/action/actuaciones/listado";

		return "redirect:/action/siniestros/editar?id=" + actuacion.getSiniestro().getCnSiniestro();
	}

}