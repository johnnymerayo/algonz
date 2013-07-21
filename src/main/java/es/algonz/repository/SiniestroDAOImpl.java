package es.algonz.repository;

// Generated 15-jul-2013 17:23:48 by Hibernate Tools 3.4.0.CR1

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.algonz.domain.SiniestroVO;

/**
 * DAOImpl object for domain model class Siniestro.
 * @see es.algonz.SiniestroVO
 * @author Hibernate Tools
 */
@Repository(value = "SiniestroDAO")
public class SiniestroDAOImpl implements SiniestroDAO {

	private static final Log log = LogFactory.getLog(SiniestroDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(SiniestroVO transientInstance) {
		log.debug("persisting Siniestro instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Transactional
	public void remove(SiniestroVO persistentInstance) {
		log.debug("removing Siniestro instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	@Transactional
	public SiniestroVO merge(SiniestroVO detachedInstance) {
		log.debug("merging Siniestro instance");
		try {
			SiniestroVO result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public SiniestroVO findById(Integer id) {
		log.debug("getting Siniestro instance with id: " + id);
		try {
			SiniestroVO instance = entityManager.find(SiniestroVO.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
