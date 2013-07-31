package es.algonz.repository;


import java.util.List;

import es.algonz.domain.ActuacionVO;

public interface ActuacionDAO {


	public void persist(ActuacionVO transientInstance) ;

	public void remove(ActuacionVO persistentInstance);

	public ActuacionVO merge(ActuacionVO detachedInstance);

	public ActuacionVO findById(Integer id);

	public List<ActuacionVO> getActuaciones(ActuacionVO object);
	
}
