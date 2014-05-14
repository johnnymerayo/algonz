package es.algonz.repository;



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

import es.algonz.domain.TipoRepresentanteVO;

/**
 * DAO TipoRepresentante.
 * @see es.algonz.TipoRepresentanteVO
 * 
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

	@Override
	public List<TipoRepresentanteVO> getTipoRepresentantes(
			TipoRepresentanteVO object) {
		log.debug("getting TipoRepresentante list ");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TipoRepresentanteVO> cq = cb.createQuery(TipoRepresentanteVO.class);
		Root<TipoRepresentanteVO> root = cq.from(TipoRepresentanteVO.class);
		cq.select(root);
		if(object != null && object.getCnTipoRepresentante() != null)
			cq.where(cb.equal(root.get("cnTipoRepresentante"), object.getCnTipoRepresentante()));
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}
}
