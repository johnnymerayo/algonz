package es.algonz.repository;

import java.util.List;

import es.algonz.domain.ComunidadVO;



public interface ComunidadDAO {


	public void persist(ComunidadVO transientInstance) ;

	public void remove(ComunidadVO persistentInstance);

	public ComunidadVO merge(ComunidadVO detachedInstance);

	public ComunidadVO findById(Integer id);

	public List<ComunidadVO> getComunidades(ComunidadVO object);
	
}
