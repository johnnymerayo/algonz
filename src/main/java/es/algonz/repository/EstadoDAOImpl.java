package es.algonz.repository;

// Generated 15-jul-2013 17:23:48 by Hibernate Tools 3.4.0.CR1

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.algonz.domain.EstadoVO;

/**
 * DAOImpl object for domain model class Estado.
 * @see es.algonz.EstadoVO
 * @author Hibernate Tools
 */
@Repository(value = "EstadoDAO")
public class EstadoDAOImpl implements EstadoDAO {

	private static final Log log = LogFactory.getLog(EstadoDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(EstadoVO transientInstance) {
		log.debug("persisting Estado instance");
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
	public void remove(EstadoVO persistentInstance) {
		log.debug("removing Estado instance");
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
	public EstadoVO merge(EstadoVO detachedInstance) {
		log.debug("merging Estado instance");
		try {
			EstadoVO result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EstadoVO findById(Integer id) {
		log.debug("getting Estado instance with id: " + id);
		try {
			EstadoVO instance = entityManager.find(EstadoVO.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
