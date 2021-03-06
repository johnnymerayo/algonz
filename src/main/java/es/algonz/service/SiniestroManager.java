package es.algonz.service;

import java.io.Serializable;
import java.util.List;

import es.algonz.domain.SiniestroVO;



public interface SiniestroManager extends Serializable {


	public void persist(SiniestroVO transientInstance) ;

	public void remove(SiniestroVO persistentInstance);

	public SiniestroVO merge(SiniestroVO detachedInstance);

	public SiniestroVO findById(Integer id);

	public List<SiniestroVO> getSiniestros(SiniestroVO object);
	
}
