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

import es.algonz.domain.TipoEmpresaVO;

/**
 * DAO TipoEmpresa.
 * @see es.algonz.TipoEmpresaVO
 * 
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

	@Override
	public List<TipoEmpresaVO> getTipoEmpresas(TipoEmpresaVO object) {
		log.debug("getting TipoEmpresa list ");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<TipoEmpresaVO> cq = cb.createQuery(TipoEmpresaVO.class);
		Root<TipoEmpresaVO> root = cq.from(TipoEmpresaVO.class);
		cq.select(root);
		if(object != null && object.getCnTipoEmpresa() != null)
			cq.where(cb.equal(root.get("cnTipoEmpresa"), object.getCnTipoEmpresa()));

		cq.orderBy(cb.asc(root.get("teTipoEmpresa")));
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}
}
