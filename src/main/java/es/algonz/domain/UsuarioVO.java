package es.algonz.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class UsuarioVO implements Serializable {

	private static final long serialVersionUID = 2412879844301530763L;
	
	@Id
	@Column(name = "IdUsuario")
	private String idUsuario;
	@Column(name = "IdSistema")
	private String idSistema;
	@Column(name = "PwdSistema")
	private String pwdSistema;
	@Column(name = "Enabled")
	private boolean enabled;
	@OneToOne(mappedBy = "usuario")
	private ConsignatarioVO consignatario;
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "USUARIO_ROL", joinColumns = { @JoinColumn(name = "IdUsuario", referencedColumnName = "IdUsuario") }, inverseJoinColumns = { @JoinColumn(name = "IdRol", referencedColumnName = "IdRol") })
	private List<RolVO> roles;

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdSistema() {
		return idSistema;
	}

	public void setIdSistema(String idSistema) {
		this.idSistema = idSistema;
	}

	public String getPwdSistema() {
		return pwdSistema;
	}

	public void setPwdSistema(String pwdSistema) {
		this.pwdSistema = pwdSistema;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public ConsignatarioVO getConsignatario() {
		return consignatario;
	}

	public void setConsignatario(ConsignatarioVO consignatario) {
		this.consignatario = consignatario;
	}

	public List<RolVO> getRoles() {
		return roles;
	}

	public void setRoles(List<RolVO> roles) {
		this.roles = roles;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof UsuarioVO) {
			UsuarioVO other = (UsuarioVO) obj;
			if ((this.idUsuario == null && other.idUsuario != null)
					|| (this.idUsuario != null && !this.idUsuario
							.equals(other.idUsuario))) {
				return false;
			}
			return true;
		}
		return false;
	}

}
