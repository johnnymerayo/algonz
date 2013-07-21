package es.algonz.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.commons.validator.GenericValidator;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import es.algonz.domain.ConsignatarioVO;
import es.algonz.domain.UsuarioVO;

@Repository(value = "consignatarioDAO")
public class ConsignatarioDAOImpl implements ConsignatarioDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<ConsignatarioVO> getConsignatarios(String codEmpresa) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ConsignatarioVO> cq = cb.createQuery(ConsignatarioVO.class);
		Root<ConsignatarioVO> root = cq.from(ConsignatarioVO.class);
		cq.select(root);
		if(!GenericValidator.isBlankOrNull(codEmpresa))
			cq.where(cb.equal(root.get("codEmpresa"), codEmpresa));
		//Sino se devuelven todos
		return em.createQuery(cq).getResultList();
	}

	@Override
	public ConsignatarioVO getConsignatario(String codEmpresa,String idConsignatario) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ConsignatarioVO> cq = cb.createQuery(ConsignatarioVO.class);
		Root<ConsignatarioVO> root = cq.from(ConsignatarioVO.class);
		cq.select(root);
		if(!GenericValidator.isBlankOrNull(codEmpresa))
			cq.where(cb.equal(root.get("codEmpresa"), codEmpresa), cb.equal(root.get("codigo"), idConsignatario));
		else
			cq.where(cb.isNull(root.get("codEmpresa")), cb.equal(root.get("codigo"), idConsignatario));
		return em.createQuery(cq).getSingleResult();
		/*
		 * string query="select ....."; //query nativa como sería en sql
		 * em.createNativeQuery(query,ConsignatarioVO.class).getResultList();
		 * */
		 
	}

	@Transactional
	@Override
	public void updateConsignatario(ConsignatarioVO consignatario) {
		em.merge(consignatario);
	}

	@Override
	@Transactional
	public void createConsignatario(ConsignatarioVO consignatario) {
		em.persist(consignatario);
		em.flush();
		em.refresh(consignatario);
	}

	@Override
	@Transactional
	public void deleteConsignatario(String codEmpresa,String codigo) {
		ConsignatarioVO consignatario = getConsignatario(codEmpresa, codigo);
		em.remove(consignatario);
		em.flush();	
	}

	@Override
	public ConsignatarioVO getConsignatarioByUser(UsuarioVO user){
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ConsignatarioVO> cq = cb.createQuery(ConsignatarioVO.class);
		Root<ConsignatarioVO> root = cq.from(ConsignatarioVO.class);
		cq.select(root);
		cq.where(cb.equal(root.get("usuario"), user));
		return em.createQuery(cq).getSingleResult();
	}
}
