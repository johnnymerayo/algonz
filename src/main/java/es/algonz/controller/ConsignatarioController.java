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
import es.algonz.domain.ConsignatarioVO;
import es.algonz.service.ConsignatarioManager;
import es.algonz.validator.ConsignatarioValidator;
import es.algonz.web.utils.RequestKeys;

@Controller
@RequestMapping("consignatarios")
public class ConsignatarioController {
	@Autowired
	private ConsignatarioManager consignatarioManager;
	@Autowired
	private ControladorUtils controladorUtils;

	@InitBinder(RequestKeys.CONSIGNATARIO)
	protected void consignatario(WebDataBinder binder) {
		binder.setValidator(new ConsignatarioValidator());
	}

	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public String listado(Model model, HttpSession session) {		
		List<ConsignatarioVO> listaConsignatarios = null;
		listaConsignatarios = consignatarioManager.getConsignatarios(null);
		model.addAttribute(RequestKeys.LISTA_CONSIGNATARIOS,
				listaConsignatarios);
		// Tiles o jsp o puede ser un forwar anotado
		return "listadoConsignatarios";
	}

	@RequestMapping(value = "/listado", method = RequestMethod.POST)
	public String listadoPost(Model model, HttpSession session) {
		return listado(model, session);
	}

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(Model model,
			@RequestParam(RequestKeys.CODIGO_EMPRESA) String codEmpresa,
			@RequestParam(RequestKeys.CODIGO) String codigo, HttpSession session) {
		if (codigo != null) {			
			boolean permisos = true;			
			if (permisos) {
				if(StringUtils.isBlank(codEmpresa))
					codEmpresa=null;
				ConsignatarioVO consignatario  = consignatarioManager.getConsignatario(
							codEmpresa, codigo);
				
				model.addAttribute(RequestKeys.CONSIGNATARIO, consignatario);
			} else {
				model.addAttribute(RequestKeys.ERROR,
						"No tiene permisos para visualizar el consignatario solicitado");
				return "forward:/action/consignatarios/listado";
			}
		}
		return "detalleConsignatario";
	}

	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(Model model,
			@RequestParam(RequestKeys.CODIGO_EMPRESA) String codEmpresa,
			@RequestParam(RequestKeys.CODIGO) String codigo, HttpSession session) {
		if (codigo != null & codEmpresa != null) {
			
				consignatarioManager.deleteConsignatario(codEmpresa, codigo);
				model.addAttribute(RequestKeys.MESSAGE,
						"Eliminado correctamente");
			
		}
		return "forward:/action/consignatarios/listado";
	}

	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Model model, HttpSession session) {
		ConsignatarioVO consignatario = new ConsignatarioVO();		
		model.addAttribute(RequestKeys.CONSIGNATARIO, consignatario);
		return "nuevoConsignatario";
	}

	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(
			@Valid @ModelAttribute(RequestKeys.CONSIGNATARIO) ConsignatarioVO consignatario,
			BindingResult binding, Model model) {
		if (binding.hasErrors()) {
			model.addAttribute(RequestKeys.CONSIGNATARIO, consignatario);
			if (!StringUtils.isBlank(consignatario.getCodigo()))
				return "detalleConsignatario";
			else
				return "nuevoConsignatario";
		}
		if (consignatario != null) {
			if(StringUtils.isBlank(consignatario.getCodEmpresa()))
				consignatario.setCodEmpresa(null);
			if (!StringUtils.isBlank(consignatario.getCodigo()))
				consignatarioManager.updateConsignatario(consignatario);
			else {
				consignatario.setCodigo(null); // Si se pasa con codigo en
												// blanco intenta recuperar la
												// entidad con ese identificador
				consignatarioManager.createConsignatario(consignatario);
			}
			model.addAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");
		}
		return "forward:/action/consignatarios/listado";
	}

}