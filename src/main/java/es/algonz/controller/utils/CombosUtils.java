package es.algonz.controller.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.algonz.domain.EmpresaVO;
import es.algonz.domain.EstadoVO;
import es.algonz.domain.PlantaVO;
import es.algonz.domain.TipoEmpresaVO;
import es.algonz.domain.TipoRepresentanteVO;
import es.algonz.service.EmpresaManager;
import es.algonz.service.EstadoManager;
import es.algonz.service.PlantaManager;
import es.algonz.service.TipoEmpresaManager;
import es.algonz.service.TipoRepresentanteManager;

@Service("combosUtils")
public class CombosUtils
{
	@Autowired
	private EmpresaManager empresaManager;
	@Autowired
	private PlantaManager plantaManager;
	@Autowired
	private TipoRepresentanteManager tipoRepresentanteManager;
	@Autowired
	private TipoEmpresaManager tipoEmpresaManager;
	@Autowired
	private EstadoManager estadoManager;

	public List<EmpresaVO> loadEmpresas()
	{
		return empresaManager.getEmpresas(new EmpresaVO());
		
	}
	public List<EmpresaVO> loadEmpresasByTipo(Integer idTipo)
	{
		EmpresaVO empresa = new EmpresaVO();
		TipoEmpresaVO tipoEmpresa = new TipoEmpresaVO();
		tipoEmpresa.setCnTipoEmpresa(idTipo);
		empresa.setTipoEmpresa(tipoEmpresa);
		return empresaManager.getEmpresas(empresa);
		
	}

	public List<PlantaVO> loadPlantas()
	{
		return plantaManager.getPlantas(new PlantaVO());
		
	}
	

	public List<TipoRepresentanteVO> loadTiposRrepresentante()
	{
		return tipoRepresentanteManager.getTipoRepresentantes(new TipoRepresentanteVO());
		
	}
	
	public List<EstadoVO> loadEstados()
	{
		return estadoManager.getEstados(new EstadoVO());
		
	}


	public List<TipoEmpresaVO> loadTiposEmpresa()
	{
		return tipoEmpresaManager.getTipoEmpresas(new TipoEmpresaVO());
		
	}
	
	
}
