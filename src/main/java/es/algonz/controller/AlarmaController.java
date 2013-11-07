package es.algonz.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.algonz.domain.ActuacionVO;
import es.algonz.domain.AvisoEmpresaVO;
import es.algonz.service.ActuacionManager;
import es.algonz.service.AvisoEmpresaManager;
import es.algonz.web.utils.RequestKeys;

@Controller
@RequestMapping("alarmas")
public class AlarmaController {
	@Autowired
	private ActuacionManager actuacionManager;
	@Autowired
	private AvisoEmpresaManager avisoEmpresaManager;
	
	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public String listado(Model model, HttpSession session) {		
		List<ActuacionVO> listaActuaciones = null;
		listaActuaciones = actuacionManager.getActuacionesProximoVencimiento();
		model.addAttribute(RequestKeys.LISTA_ACTUACIONES,
				listaActuaciones);
		
		List<AvisoEmpresaVO> listaAvisos = null;
		listaAvisos = avisoEmpresaManager.getAvisosEmpresaProximoVencimiento();
		model.addAttribute(RequestKeys.LISTA_AVISOS_EMPRESA,
				listaAvisos);
		
		
//		try {
//			if ((new Date()).after(DateUtils.parseDate("12/01/2014",new String[] { "dd/MM/yyyy"})) ){
//				return "redirect:/j_spring_security_logout";
//			}
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
		return "listadoAlarmas";
	}

	@RequestMapping(value = "/listado", method = RequestMethod.POST)
	public String listadoPost(Model model, HttpSession session) {
		return listado(model, session);
	}


}