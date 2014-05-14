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

import es.algonz.domain.AvisoEmpresaVO;
import es.algonz.web.utils.ConstantesKeys;

/**
 * DAO AvisoEmpresa.
 * @see es.algonz.AvisoEmpresaVO
 * 
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

	@Override
	public List<AvisoEmpresaVO> getAvisosEmpresa(AvisoEmpresaVO object) {
		log.debug("getting AvisoEmpresa list ");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<AvisoEmpresaVO> cq = cb.createQuery(AvisoEmpresaVO.class);
		Root<AvisoEmpresaVO> root = cq.from(AvisoEmpresaVO.class);
		cq.select(root);
		if(object != null && object.getCnAvisoEmpresa() != null)
			cq.where(cb.equal(root.get("cnAvisoEmpresa"), object.getCnAvisoEmpresa()));
		//Sino se devuelven todos
		return entityManager.createQuery(cq).getResultList();
	}

	@Override
	public List<AvisoEmpresaVO> getAvisosEmpresaProximoVencimiento() {				
		// El aviso salta nu_dias_aviso antes de la fecha de vencimiento o bien 15 días antes si no se indica.
		String query="select * from aviso_empresa " +
				"where fe_vencimiento <= DATE_ADD(CURDATE(),INTERVAL IFNULL (nu_dias_aviso, 0) + " + ConstantesKeys.DIAS_AVISO_ALARMAS + " DAY) " +
				"and cn_estado <> 2 " + // Estado CERRADO no se muestran
				"order by fe_vencimiento asc;";
		List<AvisoEmpresaVO> resultList = entityManager.createNativeQuery(query,AvisoEmpresaVO.class).getResultList();
		return  resultList;
	
	}

	@Override
	public List<AvisoEmpresaVO> getAvisosEmpresaAbiertas(Integer cnEmpresa) {
		String query="select a.* from aviso_empresa a, empresa_comunidad ec " +
				"where ec.cn_empresa = '" + cnEmpresa + "' " +
				"and a.cn_empresa_comunidad = ec.cn_empresa_comunidad " +
				"and a.cn_estado <> 2 " + // Estado CERRADO no se muestran
				"order by a.fe_vencimiento asc;";
		List<AvisoEmpresaVO> resultList = entityManager.createNativeQuery(query,AvisoEmpresaVO.class).getResultList();
		return  resultList;
	}
}
