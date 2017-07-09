package ru.masterdm.compendium.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CcUserDepJPAPK implements Serializable {
	@Column(name="ID_USER")
	private long idUser;

	@Column(name="ID_DEPARTMENT")
	private long idDepartment;

	private static final long serialVersionUID = 1L;

	public CcUserDepJPAPK() {
		super();
	}
	
	public CcUserDepJPAPK(long idUser, long idDepartment) {
		super();
		this.idUser = idUser;
		this.idDepartment = idDepartment;
	}	

	public long getIdUser() {
		return this.idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public long getIdDepartment() {
		return this.idDepartment;
	}

	public void setIdDepartment(long idDepartment) {
		this.idDepartment = idDepartment;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if ( ! (o instanceof CcUserDepJPAPK)) {
			return false;
		}
		CcUserDepJPAPK other = (CcUserDepJPAPK) o;
		return (this.idUser == other.idUser)
			&& (this.idDepartment == other.idDepartment);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idUser ^ (this.idUser >>> 32)));
		hash = hash * prime + ((int) (this.idDepartment ^ (this.idDepartment >>> 32)));
		return hash;
	}

}
