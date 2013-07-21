package es.algonz.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.algonz.domain.ConsignatarioVO;
import es.algonz.domain.UsuarioVO;
import es.algonz.repository.ConsignatarioDAO;

@Service("consignatarioManager")
public class ConsignatarioManagerImpl implements ConsignatarioManager {

	private static final long serialVersionUID = 1913666680422746714L;

	@Autowired
	private ConsignatarioDAO agenciaDAO;

	@Override
	public List<ConsignatarioVO> getConsignatarios(String codEmpresa) {
		return agenciaDAO.getConsignatarios(codEmpresa);
	}

	@Override
	public ConsignatarioVO getConsignatario(String codEmpresa, String codigo) {
		return agenciaDAO.getConsignatario(codEmpresa, codigo);
	}

	@Override
	public void updateConsignatario(ConsignatarioVO agencia) {
		agenciaDAO.updateConsignatario(agencia);

	}

	@Override
	public void createConsignatario(ConsignatarioVO agencia) {
		agenciaDAO.createConsignatario(agencia);

	}

	@Override
	public void deleteConsignatario(String codEmpresa, String codigo) {
		agenciaDAO.deleteConsignatario(codEmpresa, codigo);

	}

	@Override
	public ConsignatarioVO getConsignatarioByUser(UsuarioVO user) {
		return agenciaDAO.getConsignatarioByUser(user);
	}
}
