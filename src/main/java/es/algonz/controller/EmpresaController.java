package es.algonz.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
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

import es.algonz.controller.utils.ControladorUtils;
import es.algonz.domain.EmpresaVO;
import es.algonz.service.EmpresaManager;
import es.algonz.validator.EmpresaValidator;
import es.algonz.web.utils.RequestKeys;

@Controller
@RequestMapping("empresas")
public class EmpresaController {
	@Autowired
	private EmpresaManager empresaManager;
	@Autowired
	private ControladorUtils controladorUtils;

	@InitBinder(RequestKeys.EMPRESA)
	protected void empresa(WebDataBinder binder) {
		binder.setValidator(new EmpresaValidator());
	}

	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public String listado(Model model, HttpSession session) {		
		List<EmpresaVO> listaEmpresas = null;
		listaEmpresas = empresaManager.getEmpresas(null);
		model.addAttribute(RequestKeys.LISTA_EMPRESAS,
				listaEmpresas);
		return "listadoEmpresas";
	}

	@RequestMapping(value = "/listado", method = RequestMethod.POST)
	public String listadoPost(Model model, HttpSession session) {
		return listado(model, session);
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session) {
		if (id != null) {	
				EmpresaVO empresa  = empresaManager.findById(new Integer (id).intValue());
				
				model.addAttribute(RequestKeys.EMPRESA, empresa);
			
		}
		return "detalleEmpresa";
	}

	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session) {
		if (id != null) {
			EmpresaVO empresa  = empresaManager.findById(new Integer (id).intValue());
				empresaManager.remove(empresa);
				model.addAttribute(RequestKeys.MESSAGE,
						"Eliminado correctamente");
		}
		return "forward:/action/empresas/listado";
	}

	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Model model, HttpSession session) {
		EmpresaVO empresa = new EmpresaVO();		
		model.addAttribute(RequestKeys.EMPRESA, empresa);
		return "detalleEmpresa";
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(
			@Valid @ModelAttribute(RequestKeys.EMPRESA) EmpresaVO empresa,
			BindingResult binding, Model model) {
		if (binding.hasErrors()) {
			model.addAttribute(RequestKeys.EMPRESA, empresa);
			return "detalleEmpresa";
		}
		if (empresa != null) {
			if (empresa.getCnEmpresa() != null && !StringUtils.isBlank(empresa.getCnEmpresa().toString()))
				empresaManager.merge(empresa);
			else {
				empresaManager.persist(empresa);
			}
			model.addAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");
		}
		return "forward:/action/empresas/listado";
	}

}