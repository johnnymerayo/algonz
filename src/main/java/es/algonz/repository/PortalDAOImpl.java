package es.algonz.repository;

// Generated 15-jul-2013 17:23:48 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.algonz.domain.PortalVO;
import es.algonz.domain.PortalVO;

/**
 * DAOImpl object for domain model class Portal.
 * @see es.algonz.PortalVO
 * @author Hibernate Tools
 */
@Repository(value = "PortalDAO")
public class PortalDAOImpl implements PortalDAO{

	private static final Log log = LogFactory.getLog(PortalDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(PortalVO transientInstance) {
		log.debug("persisting Portal instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Transactional
	public void remove(PortalVO persistentInstance) {
		log.debug("removing Portal instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	@Transactional
	public PortalVO merge(PortalVO detachedInstance) {
		log.debug("merging Portal instance");
		try {
			PortalVO result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PortalVO findById(Integer id) {
		log.debug("getting Portal instance with id: " + id);
		try {
			PortalVO instance = entityManager.find(PortalVO.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<PortalVO> getPortales(PortalVO object) {
		log.debug("getting Portal list ");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PortalVO> cq = cb.createQuery(PortalVO.class);
		Root<PortalVO> root = cq.from(PortalVO.class);
		cq.select(root);
		if(object != null && object.getCnPortal() != null)
			cq.where(cb.equal(root.get("cnPortal"), object.getCnPortal()));
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}
}
