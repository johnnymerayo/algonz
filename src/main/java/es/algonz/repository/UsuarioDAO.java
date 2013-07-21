package es.algonz.repository;

import es.algonz.domain.UsuarioVO;

public interface UsuarioDAO {

	
	public UsuarioVO getUsuarioByIdSistema(String idSistema);
	
}
