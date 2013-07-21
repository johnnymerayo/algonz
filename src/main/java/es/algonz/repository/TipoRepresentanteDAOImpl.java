package es.algonz.repository;

// Generated 15-jul-2013 17:23:48 by Hibernate Tools 3.4.0.CR1

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.algonz.domain.TipoRepresentanteVO;

/**
 * DAOImpl object for domain model class TipoRepresentante.
 * @see es.algonz.TipoRepresentanteVO
 * @author Hibernate Tools
 */
@Repository(value = "TipoRepresentanteDAO")
public class TipoRepresentanteDAOImpl implements TipoRepresentanteDAO {

	private static final Log log = LogFactory
			.getLog(TipoRepresentanteDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(TipoRepresentanteVO transientInstance) {
		log.debug("persisting TipoRepresentante instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Transactional
	public void remove(TipoRepresentanteVO persistentInstance) {
		log.debug("removing TipoRepresentante instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	@Transactional
	public TipoRepresentanteVO merge(TipoRepresentanteVO detachedInstance) {
		log.debug("merging TipoRepresentante instance");
		try {
			TipoRepresentanteVO result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TipoRepresentanteVO findById(Integer id) {
		log.debug("getting TipoRepresentante instance with id: " + id);
		try {
			TipoRepresentanteVO instance = entityManager.find(
					TipoRepresentanteVO.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
