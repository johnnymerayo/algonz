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

import es.algonz.domain.EmpresaComunidadVO;

/**
 * DAOImpl object for domain model class EmpresaComunidad.
 * @see es.algonz.EmpresaComunidadVO
 * @author Hibernate Tools
 */
@Repository(value = "EmpresaComunidadDAO")
public class EmpresaComunidadDAOImpl implements EmpresaComunidadDAO{

	private static final Log log = LogFactory
			.getLog(EmpresaComunidadDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(EmpresaComunidadVO transientInstance) {
		log.debug("persisting EmpresaComunidad instance");
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
	public void remove(EmpresaComunidadVO persistentInstance) {
		log.debug("removing EmpresaComunidad instance");
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
	public EmpresaComunidadVO merge(EmpresaComunidadVO detachedInstance) {
		log.debug("merging EmpresaComunidad instance");
		try {
			EmpresaComunidadVO result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EmpresaComunidadVO findById(Integer id) {
		log.debug("getting EmpresaComunidad instance with id: " + id);
		try {
			EmpresaComunidadVO instance = entityManager.find(
					EmpresaComunidadVO.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<EmpresaComunidadVO> getEmpresasComunidad(
			EmpresaComunidadVO object) {
		log.debug("getting EmpresaComunidad list ");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<EmpresaComunidadVO> cq = cb.createQuery(EmpresaComunidadVO.class);
		Root<EmpresaComunidadVO> root = cq.from(EmpresaComunidadVO.class);
		cq.select(root);
		if(object != null && object.getCnEmpresaComunidad() != null)
			cq.where(cb.equal(root.get("cnEmpresaComunidad"), object.getCnEmpresaComunidad()));
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}
}
