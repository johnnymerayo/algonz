		package es.algonz.service;

import java.io.Serializable;
import java.util.List;

import es.algonz.domain.ActuacionVO;



public interface ActuacionManager extends Serializable {


	public void persist(ActuacionVO transientInstance) ;

	public void remove(ActuacionVO persistentInstance);

	public ActuacionVO merge(ActuacionVO detachedInstance);

	public ActuacionVO findById(Integer id);

	public List<ActuacionVO> getActuaciones(ActuacionVO object);

	public List<ActuacionVO> getActuacionesProximoVencimiento();

	public List<ActuacionVO> getActuacionesAbiertas(Integer cnEmpresa);
	
}
