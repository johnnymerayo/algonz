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

import es.algonz.domain.ComunidadVO;
import es.algonz.domain.PredioVO;

/**
 * DAO Comunidad.
 * @see es.algonz.ComunidadVO
 * 
 */
@Repository(value = "ComunidadDAO")
public class ComunidadDAOImpl implements ComunidadDAO {

	private static final Log log = LogFactory.getLog(ComunidadDAOImpl.class);

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void persist(ComunidadVO transientInstance) {
		log.debug("persisting Comunidad instance");
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
	public void remove(ComunidadVO persistentInstance) {
		log.debug("removing Comunidad instance");
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
	public ComunidadVO merge(ComunidadVO detachedInstance) {
		log.debug("merging Comunidad instance");
		try {
			ComunidadVO result = entityManager.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public ComunidadVO findById(Integer id) {
		log.debug("getting Comunidad instance with id: " + id);
		try {
			ComunidadVO instance = entityManager.find(ComunidadVO.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@Override
	public List<ComunidadVO> getComunidades(ComunidadVO object) {
		log.debug("getting Comunidad list ");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<ComunidadVO> cq = cb.createQuery(ComunidadVO.class);
		Root<ComunidadVO> root = cq.from(ComunidadVO.class);
		cq.select(root);
		if(object != null && object.getCnComunidad() != null)
			cq.where(cb.equal(root.get("cnComunidad"), object.getCnComunidad()));

		cq.orderBy(cb.asc(root.get("teNombre")));
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}

	@Override
	public List<PredioVO> getRepresentantes(Integer cnComunidad) {
	
		String query="SELECT pr.* " +
				"FROM  portal p, predio pr " +
				"WHERE p.cn_Comunidad = " + cnComunidad +
				" AND pr.cn_portal = p.cn_portal " +
				"AND pr.cn_tipo_representante is not null order by pr.cn_tipo_representante asc;";
		List<PredioVO> resultList = entityManager.createNativeQuery(query,PredioVO.class).getResultList();
		return  resultList;
	

		
	}
}
