package es.algonz.repository;

// Generated 15-jul-2013 17:23:48 by Hibernate Tools 3.4.0.CR1

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.algonz.domain.AvisoEmpresaVO;

/**
 * DAOImpl object for domain model class AvisoEmpresa.
 * @see es.algonz.AvisoEmpresaVO
 * @author Hibernate Tools
 */
@Repository(value = "AvisoEmpresaDAO")
public class AvisoEmpresaDAOImpl implements AvisoEmpresaDAO{

	private static final Log log = LogFactory.getLog(AvisoEmpresaDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(AvisoEmpresaVO transientInstance) {
		log.debug("persisting AvisoEmpresa instance");
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
	public void remove(AvisoEmpresaVO persistentInstance) {
		log.debug("removing AvisoEmpresa instance");
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
	public AvisoEmpresaVO merge(AvisoEmpresaVO detachedInstance) {
		log.debug("merging AvisoEmpresa instance");
		try {
			AvisoEmpresaVO result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public AvisoEmpresaVO findById(Integer id) {
		log.debug("getting AvisoEmpresa instance with id: " + id);
		try {
			AvisoEmpresaVO instance = entityManager.find(AvisoEmpresaVO.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
