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

import es.algonz.domain.EmpresaVO;

/**
 * DAOImpl object for domain model class Empresa.
 * @see es.algonz.EmpresaVO
 * @author Hibernate Tools
 */
@Repository(value = "EmpresaDAO")
public class EmpresaDAOImpl implements EmpresaDAO{

	private static final Log log = LogFactory.getLog(EmpresaDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(EmpresaVO transientInstance) {
		log.debug("persisting Empresa instance");
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
	public void remove(EmpresaVO persistentInstance) {
		log.debug("removing Empresa instance");
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
	public EmpresaVO merge(EmpresaVO detachedInstance) {
		log.debug("merging Empresa instance");
		try {
			EmpresaVO result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public EmpresaVO findById(Integer id) {
		log.debug("getting Empresa instance with id: " + id);
		try {
			EmpresaVO instance = entityManager.find(EmpresaVO.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<EmpresaVO> getEmpresas(EmpresaVO object) {
		log.debug("getting Empresa list ");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<EmpresaVO> cq = cb.createQuery(EmpresaVO.class);
		Root<EmpresaVO> root = cq.from(EmpresaVO.class);
		cq.select(root);
		if(object != null && object.getCnEmpresa() != null)
			cq.where(cb.equal(root.get("cnEmpresa"), object.getCnEmpresa()));
		else if(object != null && object.getTipoEmpresa() != null && object.getTipoEmpresa().getCnTipoEmpresa() != null)
			cq.where(cb.equal(root.get("tipoEmpresa"), object.getTipoEmpresa()));
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}
}
