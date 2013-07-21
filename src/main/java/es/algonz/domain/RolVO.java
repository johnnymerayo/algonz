package es.algonz.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROL")
public class RolVO implements Serializable {

	private static final long serialVersionUID = 237892378234L;
	
	@Id
	@Column(name = "IdRol")
	private String idRol;
	@Column(name = "Authority")
	private String authority;
	@Column(name = "DescRol")
	private String descRol;
	
	public RolVO() {
	}

	public RolVO(String idRol) {
		this.idRol = idRol;
	}
	
	public String getIdRol() {
		return idRol;
	}

	public void setIdRol(String idRol) {
		this.idRol = idRol;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getDescRol() {
		return descRol;
	}

	public void setDescRol(String descRol) {
		this.descRol = descRol;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RolVO) {
			RolVO other = (RolVO) obj;
			if ((this.idRol == null && other.idRol != null)
					|| (this.idRol != null && !this.idRol
							.equals(other.idRol))) {
				return false;
			}
			return true;
		}
		return false;
	}

}
