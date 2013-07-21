package es.algonz.repository;

// Generated 20-jul-2013 18:00:17 by Hibernate Tools 3.4.0.CR1


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.algonz.domain.TipoEmpresaVO;

/**
 * Home object for domain model class TipoEmpresa.
 * @see es.algonz.TipoEmpresaVO
 * @author Hibernate Tools
 */
@Repository(value = "TipoEmpresaDAO")
public class TipoEmpresaDAOImpl implements TipoEmpresaDAO{

	private static final Log log = LogFactory.getLog(TipoEmpresaDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(TipoEmpresaVO transientInstance) {
		log.debug("persisting TipoEmpresa instance");
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
	public void remove(TipoEmpresaVO persistentInstance) {
		log.debug("removing TipoEmpresa instance");
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
	public TipoEmpresaVO merge(TipoEmpresaVO detachedInstance) {
		log.debug("merging TipoEmpresa instance");
		try {
			TipoEmpresaVO result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public TipoEmpresaVO findById(Integer id) {
		log.debug("getting TipoEmpresa instance with id: " + id);
		try {
			TipoEmpresaVO instance = entityManager.find(TipoEmpresaVO.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
