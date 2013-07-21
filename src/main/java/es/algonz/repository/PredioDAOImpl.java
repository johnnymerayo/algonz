package es.algonz.repository;

// Generated 15-jul-2013 17:23:48 by Hibernate Tools 3.4.0.CR1

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.algonz.domain.PredioVO;

/**
 * DAOImpl object for domain model class Predio.
 * @see es.algonz.PredioVO
 * @author Hibernate Tools
 */
@Repository(value = "PredioDAO")
public class PredioDAOImpl implements PredioDAO {

	private static final Log log = LogFactory.getLog(PredioDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(PredioVO transientInstance) {
		log.debug("persisting Predio instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}
	
	@Transactional
	public void remove(PredioVO persistentInstance) {
		log.debug("removing Predio instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	@Transactional
	public PredioVO merge(PredioVO detachedInstance) {
		log.debug("merging Predio instance");
		try {
			PredioVO result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PredioVO findById(Integer id) {
		log.debug("getting Predio instance with id: " + id);
		try {
			PredioVO instance = entityManager.find(PredioVO.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
