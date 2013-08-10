package es.algonz.domain;

// Generated 20-jul-2013 18:00:15 by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TipoRepresentante generated by hbm2java
 */
@Entity
@Table(name = "tipo_representante", catalog = "algonz")
public class TipoRepresentanteVO implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7226941503271440995L;
	private Integer cnTipoRepresentante;
	private String teTipoRepresentante;
	private Set<PredioVO> predios = new HashSet<PredioVO>(0);

	public TipoRepresentanteVO() {
	}

	public TipoRepresentanteVO(Integer cnTipoRepresentante, String teTipoRepresentante) {
		this.cnTipoRepresentante = cnTipoRepresentante;
		this.teTipoRepresentante = teTipoRepresentante;
	}

	public TipoRepresentanteVO(Integer cnTipoRepresentante,
			String teTipoRepresentante, Set<PredioVO> predios) {
		this.cnTipoRepresentante = cnTipoRepresentante;
		this.teTipoRepresentante = teTipoRepresentante;
		this.predios = predios;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CN_TIPO_REPRESENTANTE", unique = true, nullable = false)
	public Integer getCnTipoRepresentante() {
		return this.cnTipoRepresentante;
	}

	public void setCnTipoRepresentante(Integer cnTipoRepresentante) {
		this.cnTipoRepresentante = cnTipoRepresentante;
	}

	@Column(name = "TE_TIPO_REPRESENTANTE", nullable = false, length = 50)
	public String getTeTipoRepresentante() {
		return this.teTipoRepresentante;
	}

	public void setTeTipoRepresentante(String teTipoRepresentante) {
		this.teTipoRepresentante = teTipoRepresentante;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tipoRepresentante")
	public Set<PredioVO> getPredios() {
		return this.predios;
	}

	public void setPredios(Set<PredioVO> predios) {
		this.predios = predios;
	}

}
