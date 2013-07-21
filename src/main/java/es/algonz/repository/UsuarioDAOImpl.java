package es.algonz.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import es.algonz.domain.UsuarioVO;

@Repository(value = "usuarioDAO")
public class UsuarioDAOImpl implements UsuarioDAO
{
	@PersistenceContext
	private EntityManager em;

	@Override
	public UsuarioVO getUsuarioByIdSistema(String idSistema)
	{
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UsuarioVO> cq = cb.createQuery(UsuarioVO.class);
		Root<UsuarioVO> root = cq.from(UsuarioVO.class);
		cq.select(root);
		cq.where(cb.equal(root.get("idSistema"), idSistema));
		return em.createQuery(cq).getSingleResult();
	}
}
