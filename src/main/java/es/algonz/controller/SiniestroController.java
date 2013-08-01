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
import es.algonz.domain.PortalVO;
import es.algonz.domain.SiniestroVO;
import es.algonz.service.PortalManager;
import es.algonz.service.SiniestroManager;
import es.algonz.validator.SiniestroValidator;
import es.algonz.web.utils.RequestKeys;

@Controller
@RequestMapping("siniestros")
public class SiniestroController {
	@Autowired
	private SiniestroManager siniestroManager;
	@Autowired
	private PortalManager portalManager;
	@Autowired
	private CombosUtils combosUtils;

	@InitBinder(RequestKeys.SINIESTRO)
	protected void siniestro(WebDataBinder binder) {
		binder.setValidator(new SiniestroValidator());
	}

	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public String listado(Model model, HttpSession session) {		
		List<SiniestroVO> listaSiniestros = null;
		listaSiniestros = siniestroManager.getSiniestros(null);
		model.addAttribute(RequestKeys.LISTA_SINIESTROS,
				listaSiniestros);
		return "listadoSiniestros";
	}

	@RequestMapping(value = "/listado", method = RequestMethod.POST)
	public String listadoPost(Model model, HttpSession session) {
		return listado(model, session);
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session) {
		if (id != null) {	
				SiniestroVO siniestro  = siniestroManager.findById(new Integer (id).intValue());
				siniestro.setCnTipoSiniestro(siniestro.getEmpresaComunidad().getEmpresa().getTipoEmpresa().getCnTipoEmpresa());
				model.addAttribute(RequestKeys.SINIESTRO, siniestro);
			
		}
		

		// Cargamos el combo de tipos de empresa
		model.addAttribute("tiposEmpresaCombo", combosUtils.loadTiposEmpresa());
		// TODO hacerlo dinamico desde la vista
		// Cargamos el combo de empresa
		model.addAttribute("empresasCombo", combosUtils.loadEmpresas());
		return "detalleSiniestro";
	}

	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session) {
		if (id != null) {
			SiniestroVO siniestro  = siniestroManager.findById(new Integer (id).intValue());
				siniestroManager.remove(siniestro);
				model.addAttribute(RequestKeys.MESSAGE,
						"Eliminado correctamente");
		}
		return "forward:/action/siniestros/listado";
	}

	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Model model, HttpSession session) {
		SiniestroVO siniestro = new SiniestroVO();		
		model.addAttribute(RequestKeys.SINIESTRO, siniestro);
		// Cargamos el combo de tipos de empresa
		model.addAttribute("tiposEmpresaCombo", combosUtils.loadTiposEmpresa());
		// TODO hacerlo dinamico desde la vista
		// Cargamos el combo de empresa
		model.addAttribute("empresasCombo", combosUtils.loadEmpresas());
		return "detalleSiniestro";
	}
	
	@RequestMapping(value = "/nuevoSiniestro", method = RequestMethod.GET)
	public String nuevo(Model model, HttpSession session, @RequestParam(RequestKeys.CODIGO_PORTAL) String codPortal) {
		SiniestroVO siniestro = new SiniestroVO();			
		if(!GenericValidator.isBlankOrNull(codPortal)){
			PortalVO portal = portalManager.findById(new Integer (codPortal));
			siniestro.setPortal(portal);
		}
		
		model.addAttribute(RequestKeys.SINIESTRO, siniestro);
		// Cargamos el combo de tipos de empresa
		model.addAttribute("tiposEmpresaCombo", combosUtils.loadTiposEmpresa());
		// TODO hacerlo dinamico desde la vista
		// Cargamos el combo de empresa
		model.addAttribute("empresasCombo", combosUtils.loadEmpresas());
		return "detalleSiniestro";
	}
	
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(
			@Valid @ModelAttribute(RequestKeys.SINIESTRO) SiniestroVO siniestro,
			BindingResult binding, Model model, RedirectAttributes redirectAttrs) {
		if (binding.hasErrors()) {
			model.addAttribute(RequestKeys.SINIESTRO, siniestro);
			// Cargamos el combo de tipos de empresa
			model.addAttribute("tiposEmpresaCombo", combosUtils.loadTiposEmpresa());
			// TODO hacerlo dinamico desde la vista
			// Cargamos el combo de empresa
			model.addAttribute("empresasCombo", combosUtils.loadEmpresas());
			return "detalleSiniestro";
		}
		if (siniestro != null) {
			if (siniestro.getCnSiniestro() != null && !StringUtils.isBlank(siniestro.getCnSiniestro().toString()))
				siniestroManager.merge(siniestro);
			else {
				siniestroManager.persist(siniestro);
			}
			//model.addAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");
			redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");
		}
		return "forward:/action/siniestros/listado";
		//return "redirect:/action/portales/editar?id=" + siniestro.getPortal().getCnPortal();
	}

}