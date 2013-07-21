package es.algonz.repository;

// Generated 15-jul-2013 17:23:48 by Hibernate Tools 3.4.0.CR1

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.algonz.domain.ActuacionVO;

/**
 * DAOImpl object for domain model class Actuacion.
 * @see es.algonz.ActuacionVO
 * @author Hibernate Tools
 */
@Repository(value = "ActuacionDAO")
public class ActuacionDAOImpl implements ActuacionDAO {

	private static final Log log = LogFactory.getLog(ActuacionDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(ActuacionVO transientInstance) {
		log.debug("persisting Actuacion instance");
		try {
			entityManager.persist(transientInstance);
			entityManager.flush();
			entityManager.refresh(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Transactional
	public void remove(ActuacionVO persistentInstance) {
		log.debug("removing Actuacion instance");
		try {
			entityManager.remove(persistentInstance);
			entityManager.flush();
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	@Transactional
	public ActuacionVO merge(ActuacionVO detachedInstance) {
		log.debug("merging Actuacion instance");
		try {
			ActuacionVO result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ActuacionVO findById(Integer id) {
		log.debug("getting Actuacion instance with id: " + id);
		try {
			ActuacionVO instance = entityManager.find(ActuacionVO.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
