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
import es.algonz.controller.utils.ControladorUtils;
import es.algonz.domain.ComunidadVO;
import es.algonz.domain.PortalVO;
import es.algonz.domain.PredioVO;
import es.algonz.service.PortalManager;
import es.algonz.service.PredioManager;
import es.algonz.validator.PredioValidator;
import es.algonz.web.utils.RequestKeys;

@Controller
@RequestMapping("predios")
public class PredioController {
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
			@RequestParam(RequestKeys.ID) String id, HttpSession session) {
		if (id != null) {
			PredioVO predio  = predioManager.findById(new Integer (id).intValue());
				predioManager.remove(predio);
				model.addAttribute(RequestKeys.MESSAGE,
						"Eliminado correctamente");
		}
		return "forward:/action/predios/listado";
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

}