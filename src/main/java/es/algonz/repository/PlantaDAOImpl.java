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

import es.algonz.domain.PlantaVO;
import es.algonz.domain.PlantaVO;

/**
 * DAOImpl object for domain model class Planta.
 * @see es.algonz.PlantaVO
 * @author Hibernate Tools
 */
@Repository(value = "PlantaDAO")
public class PlantaDAOImpl implements PlantaDAO{

	private static final Log log = LogFactory.getLog(PlantaDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(PlantaVO transientInstance) {
		log.debug("persisting Planta instance");
		try {
			entityManager.persist(transientInstance);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	@Transactional
	public void remove(PlantaVO persistentInstance) {
		log.debug("removing Planta instance");
		try {
			entityManager.remove(persistentInstance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
	}

	@Transactional
	public PlantaVO merge(PlantaVO detachedInstance) {
		log.debug("merging Planta instance");
		try {
			PlantaVO result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public PlantaVO findById(Integer id) {
		log.debug("getting Planta instance with id: " + id);
		try {
			PlantaVO instance = entityManager.find(PlantaVO.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<PlantaVO> getPlantas(PlantaVO object) {
		log.debug("getting Planta list ");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PlantaVO> cq = cb.createQuery(PlantaVO.class);
		Root<PlantaVO> root = cq.from(PlantaVO.class);
		cq.select(root);
		if(object != null && object.getCnPlanta() != null)
			cq.where(cb.equal(root.get("cnPlanta"), object.getCnPlanta()));
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}
}
