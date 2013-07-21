package es.algonz.repository;

import java.util.List;

import es.algonz.domain.ConsignatarioVO;
import es.algonz.domain.UsuarioVO;

public interface ConsignatarioDAO {

	public List<ConsignatarioVO> getConsignatarios(String codEmpresa);

	public ConsignatarioVO getConsignatario(String codEmpresa,String codigo);

	public void updateConsignatario(ConsignatarioVO consignatario);

	public void createConsignatario(ConsignatarioVO consignatario);
	
	public void deleteConsignatario(String codEmpresa,String codigo);
	
	public ConsignatarioVO getConsignatarioByUser(UsuarioVO user);
}
