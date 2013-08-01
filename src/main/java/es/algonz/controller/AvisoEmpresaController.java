package es.algonz.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.algonz.controller.utils.CombosUtils;
import es.algonz.domain.AvisoEmpresaVO;
import es.algonz.domain.EmpresaComunidadVO;
import es.algonz.service.AvisoEmpresaManager;
import es.algonz.service.EmpresaComunidadManager;
import es.algonz.validator.AvisoEmpresaValidator;
import es.algonz.web.utils.RequestKeys;

@Controller
@RequestMapping("avisosEmpresa")
public class AvisoEmpresaController {
	@Autowired
	private AvisoEmpresaManager avisoEmpresaManager;
	@Autowired
	private EmpresaComunidadManager empresaComunidadManager;
	@Autowired
	private CombosUtils combosUtils;

	@InitBinder(RequestKeys.AVISO_EMPRESA)
	protected void avisoEmpresa(WebDataBinder binder) {
		binder.setValidator(new AvisoEmpresaValidator());
		
		
		// Para que salga la fecha formateada al entrar en los detalles
		// y permitir guardar vacias
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	    binder.registerCustomEditor(Date.class, new CustomDateEditor(
	            dateFormat, true));
	   
	}

	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public String listado(Model model, HttpSession session) {		
		List<AvisoEmpresaVO> listaAvisosEmpresa = null;
		listaAvisosEmpresa = avisoEmpresaManager.getAvisosEmpresa(null);
		model.addAttribute(RequestKeys.LISTA_AVISOS_EMPRESA,
				listaAvisosEmpresa);
		return "listadoAvisosEmpresa";
	}

	@RequestMapping(value = "/listado", method = RequestMethod.POST)
	public String listadoPost(Model model, HttpSession session) {
		return listado(model, session);
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session) {
		if (id != null) {	
				AvisoEmpresaVO avisoEmpresa  = avisoEmpresaManager.findById(new Integer (id).intValue());
				
				model.addAttribute(RequestKeys.AVISO_EMPRESA, avisoEmpresa);

				// Cargamos el combo de estados
				model.addAttribute("estadosCombo", combosUtils.loadEstados());
				
			
		}
		return "detalleAvisoEmpresa";
	}

	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session) {
		
		String idEmpresaComunidad = "";
		if (id != null) {
			AvisoEmpresaVO avisoEmpresa  = avisoEmpresaManager.findById(new Integer (id).intValue());
			idEmpresaComunidad = avisoEmpresa.getEmpresaComunidad().getCnEmpresaComunidad().toString();
				avisoEmpresaManager.remove(avisoEmpresa);
				model.addAttribute(RequestKeys.MESSAGE,
						"Eliminado correctamente");
		}
		//return "forward:/action/avisosEmpresa/listado";

		return "redirect:/action/empresasComunidad/editar?id=" + idEmpresaComunidad;
	}

	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Model model, HttpSession session, @RequestParam(RequestKeys.CODIGO_EMPRESA_COMUNIDAD) String codEmpresaComunidad) {
		AvisoEmpresaVO avisoEmpresa = new AvisoEmpresaVO();	
		if(!GenericValidator.isBlankOrNull(codEmpresaComunidad)){
			EmpresaComunidadVO empresaComunidad = empresaComunidadManager.findById(new Integer (codEmpresaComunidad));
			avisoEmpresa.setEmpresaComunidad(empresaComunidad);
		}
		model.addAttribute(RequestKeys.AVISO_EMPRESA, avisoEmpresa);

		// Cargamos el combo de estados
		model.addAttribute("estadosCombo", combosUtils.loadEstados());
		return "detalleAvisoEmpresa";
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(
			@Valid @ModelAttribute(RequestKeys.AVISO_EMPRESA) AvisoEmpresaVO avisoEmpresa,
			BindingResult binding, Model model, RedirectAttributes redirectAttrs) {
		if (binding.hasErrors()) {
			model.addAttribute(RequestKeys.AVISO_EMPRESA, avisoEmpresa);

			// Cargamos el combo de estados
			model.addAttribute("estadosCombo", combosUtils.loadEstados());
			return "detalleAvisoEmpresa";
		}
		if (avisoEmpresa != null) {
			if (avisoEmpresa.getCnAvisoEmpresa() != null && !StringUtils.isBlank(avisoEmpresa.getCnAvisoEmpresa().toString()))
				avisoEmpresaManager.merge(avisoEmpresa);
			else {
				avisoEmpresaManager.persist(avisoEmpresa);
			}
			//model.addAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");

			redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");
		}
		//return "forward:/action/avisosEmpresa/listado";

		return "redirect:/action/empresasComunidad/editar?id=" + avisoEmpresa.getEmpresaComunidad().getCnEmpresaComunidad();
	}

}