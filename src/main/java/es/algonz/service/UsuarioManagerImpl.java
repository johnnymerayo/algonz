package es.algonz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.algonz.domain.UsuarioVO;
import es.algonz.repository.UsuarioDAO;

@Service(value="usuarioManager")
public class UsuarioManagerImpl implements UsuarioManager {

	@Autowired
	private UsuarioDAO usuarioDAO;

	@Override
	public UsuarioVO getUsuarioByIdSistema(String idSistema) {
		return usuarioDAO.getUsuarioByIdSistema(idSistema);
	}
}
