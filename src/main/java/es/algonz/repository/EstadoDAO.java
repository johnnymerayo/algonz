package es.algonz.repository;

import es.algonz.domain.EstadoVO;



public interface EstadoDAO {


	public void persist(EstadoVO transientInstance) ;

	public void remove(EstadoVO persistentInstance);

	public EstadoVO merge(EstadoVO detachedInstance);

	public EstadoVO findById(Integer id);
	
}
