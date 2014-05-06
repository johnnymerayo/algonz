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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import es.algonz.domain.UsuarioVO;
import es.algonz.service.UsuarioManager;
import es.algonz.validator.UsuarioValidator;
import es.algonz.web.utils.RequestKeys;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
	@Autowired
	private UsuarioManager usuarioManager;


	@InitBinder(RequestKeys.USUARIO)
	protected void usuario(WebDataBinder binder) {
		binder.setValidator(new UsuarioValidator());
	}
	
	
	
/*
	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public String listado(Model model, HttpSession session) {		
		List<UsuarioVO> listaUsuarios = null;
		listaUsuarios = usuarioManager.getUsuarios(null);
		model.addAttribute(RequestKeys.LISTA_USUARIOS,
				listaUsuarios);
		return "listadoUsuarios";
	}

	@RequestMapping(value = "/listado", method = RequestMethod.POST)
	public String listadoPost(Model model, HttpSession session) {
		return listado(model, session);
	}
*/

	@RequestMapping(value = "/editar", method = RequestMethod.GET)
	public String editar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session) {
		if (id != null) {	
				UsuarioVO usuario  = usuarioManager.findById(new Integer (id).intValue());
				model.addAttribute(RequestKeys.USUARIO, usuario);
		}
		

		model.addAttribute("colapsar", true);
		
		
	return "detalleUsuario";
	}

	/*
	@RequestMapping(value = "/eliminar", method = RequestMethod.GET)
	public String eliminar(Model model,
			@RequestParam(RequestKeys.ID) String id, HttpSession session, RedirectAttributes redirectAttrs) {
		
			return "forward:/action/usuario/listado";
	}

	@RequestMapping(value = "/nuevo", method = RequestMethod.GET)
	public String nuevo(Model model, HttpSession session) {
		UsuarioVO usuario = new UsuarioVO();		
		model.addAttribute(RequestKeys.USUARIO, usuario);
		return "detalleUsuario";
	}
	*/
	
	@RequestMapping(value = "/guardar", method = RequestMethod.POST)
	public String guardar(
			@Valid @ModelAttribute(RequestKeys.USUARIO) UsuarioVO usuario,
			BindingResult binding, Model model, RedirectAttributes redirectAttrs) {
		if (binding.hasErrors()) {

			usuario.setPwdSistemaOld(null);
			usuario.setPwdSistemaNew(null);
			usuario.setPwdSistemaConfirm(null);
			
			model.addAttribute(RequestKeys.USUARIO, usuario);
			

			model.addAttribute("colapsar", false);
			
			return "detalleUsuario";
		}
		if (usuario != null) {
			
			if(StringUtils.isNotBlank(usuario.getPwdSistemaNew())){
				usuario.setPwdSistema(usuario.getPwdSistemaNew());
			}
			
			if (usuario.getIdUsuario() != null && !StringUtils.isBlank(usuario.getIdUsuario().toString())){
				// Para que no se pierdan los roles al insertar. 
				UsuarioVO usuarioRoles  = usuarioManager.findById(new Integer (usuario.getIdUsuario()).intValue());
				usuario.setRoles(usuarioRoles.getRoles());
				
				usuarioManager.merge(usuario);
			}
			else {
				usuarioManager.merge(usuario);
			}
			

			usuario.setPwdSistemaOld(null);
			usuario.setPwdSistemaNew(null);
			usuario.setPwdSistemaConfirm(null);
			

			model.addAttribute("colapsar", true);
			
			model.addAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");
			//redirectAttrs.addFlashAttribute(RequestKeys.MESSAGE, "Almacenado correctamente");
		}

		return "detalleUsuario";
	}


}