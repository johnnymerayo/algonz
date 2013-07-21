package es.algonz.repository;

// Generated 20-jul-2013 18:00:17 by Hibernate Tools 3.4.0.CR1


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.algonz.domain.DocumentoVO;

/**
 * Home object for domain model class Documento.
 * @see es.algonz.DocumentoVO
 * @author Hibernate Tools
 */
@Repository(value = "DocumentoDAO")
public class DocumentoDAOImpl implements DocumentoDAO{

	private static final Log log = LogFactory.getLog(DocumentoDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(DocumentoVO transientInstance) {
		log.debug("persisting Documento instance");
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
	public void remove(DocumentoVO persistentInstance) {
		log.debug("removing Documento instance");
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
	public DocumentoVO merge(DocumentoVO detachedInstance) {
		log.debug("merging Documento instance");
		try {
			DocumentoVO result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public DocumentoVO findById(Integer id) {
		log.debug("getting Documento instance with id: " + id);
		try {
			DocumentoVO instance = entityManager.find(DocumentoVO.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}
}
