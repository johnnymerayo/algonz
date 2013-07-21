package es.algonz.repository;

import es.algonz.domain.AvisoEmpresaVO;



public interface AvisoEmpresaDAO {


	public void persist(AvisoEmpresaVO transientInstance) ;

	public void remove(AvisoEmpresaVO persistentInstance);

	public AvisoEmpresaVO merge(AvisoEmpresaVO detachedInstance);

	public AvisoEmpresaVO findById(Integer id);
	
}
