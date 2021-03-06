package es.algonz.service;

import java.io.Serializable;
import java.util.List;

import es.algonz.domain.PredioVO;



public interface PredioManager extends Serializable {


	public void persist(PredioVO transientInstance) ;

	public void remove(PredioVO persistentInstance);

	public PredioVO merge(PredioVO detachedInstance);

	public PredioVO findById(Integer id);

	public List<PredioVO> getPredios(PredioVO object);
	
}
