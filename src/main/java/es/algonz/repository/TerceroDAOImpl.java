package es.algonz.repository;



import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.algonz.domain.TerceroVO;

/**
 * DAO Tercero.
 * @see es.algonz.TerceroVO
 * 
 */
@Repository(value = "TerceroDAO")
public class TerceroDAOImpl implements TerceroDAO{

	private static final Log log = LogFactory.getLog(TerceroDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(TerceroVO transientInstance) {
		log.debug("persisting Tercero instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Transactional
	public void remove(TerceroVO persistentInstance) {
		log.debug("removing Tercero instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	@Transactional
	public TerceroVO merge(TerceroVO detachedInstance) {
		log.debug("merging Tercero instance");
		try {
			TerceroVO result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TerceroVO findById(Integer id) {
		log.debug("getting Tercero instance with id: " + id);
		try {
			TerceroVO instance = entityManager.find(TerceroVO.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
