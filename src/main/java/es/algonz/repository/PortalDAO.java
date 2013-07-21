package es.algonz.repository;

import es.algonz.domain.PortalVO;



public interface PortalDAO {


	public void persist(PortalVO transientInstance) ;

	public void remove(PortalVO persistentInstance);

	public PortalVO merge(PortalVO detachedInstance);

	public PortalVO findById(Integer id);
	
}
