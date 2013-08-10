package es.algonz.repository;

import java.util.List;

import es.algonz.domain.AvisoEmpresaVO;



public interface AvisoEmpresaDAO {


	public void persist(AvisoEmpresaVO transientInstance) ;

	public void remove(AvisoEmpresaVO persistentInstance);

	public AvisoEmpresaVO merge(AvisoEmpresaVO detachedInstance);

	public AvisoEmpresaVO findById(Integer id);

	public List<AvisoEmpresaVO> getAvisosEmpresa(AvisoEmpresaVO object);

	public List<AvisoEmpresaVO> getAvisosEmpresaProximoVencimiento();

	public List<AvisoEmpresaVO> getAvisosEmpresaAbiertas(Integer cnEmpresa);
	
}
