package es.algonz.controller;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
				comunidadManager.remove(comunidad);
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
	public LinkedList<FileMeta> uploadDocument(Model model, HttpSession session, MultipartRequest request, @RequestParam(RequestKeys.CODIGO_COMUNIDAD) String codComunidad) {

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
	

}