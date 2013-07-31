package es.algonz.repository;

// Generated 15-jul-2013 17:23:48 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.algonz.domain.ActuacionVO;
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

	@Override
	public List<ActuacionVO> getActuaciones(ActuacionVO object) {
		log.debug("getting Actuacion list ");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ActuacionVO> cq = cb.createQuery(ActuacionVO.class);
		Root<ActuacionVO> root = cq.from(ActuacionVO.class);
		cq.select(root);
		if(object != null && object.getCnActuacion() != null)
			cq.where(cb.equal(root.get("cnActuacion"), object.getCnActuacion()));
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}
}
