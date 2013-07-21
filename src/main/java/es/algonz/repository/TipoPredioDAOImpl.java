package es.algonz.repository;

// Generated 15-jul-2013 17:23:48 by Hibernate Tools 3.4.0.CR1

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.algonz.domain.TipoPredioVO;

/**
 * DAOImpl object for domain model class TipoPredio.
 * @see es.algonz.TipoPredioVO
 * @author Hibernate Tools
 */
@Repository(value = "TipoPredioDAO")
public class TipoPredioDAOImpl implements TipoPredioDAO{

	private static final Log log = LogFactory.getLog(TipoPredioDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(TipoPredioVO transientInstance) {
		log.debug("persisting TipoPredio instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Transactional
	public void remove(TipoPredioVO persistentInstance) {
		log.debug("removing TipoPredio instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	@Transactional
	public TipoPredioVO merge(TipoPredioVO detachedInstance) {
		log.debug("merging TipoPredio instance");
		try {
			TipoPredioVO result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TipoPredioVO findById(Integer id) {
		log.debug("getting TipoPredio instance with id: " + id);
		try {
			TipoPredioVO instance = entityManager.find(TipoPredioVO.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
