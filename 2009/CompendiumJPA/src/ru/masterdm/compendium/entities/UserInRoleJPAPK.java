package ru.masterdm.compendium.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class UserInRoleJPAPK implements Serializable {
	@Column(name="ID_ROLE")
	private long idRole;

	@Column(name="ID_USER")
	private long idUser;

	private static final long serialVersionUID = 1L;

	public UserInRoleJPAPK() {
		super();
	}

	public UserInRoleJPAPK(long idUser, long idRole) {
		super();
		this.idUser = idUser;
		this.idRole = idRole;
	}

	public long getIdRole() {
		return this.idRole;
	}

	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}

	public long getIdUser() {
		return this.idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if ( ! (o instanceof UserInRoleJPAPK)) {
			return false;
		}
		UserInRoleJPAPK other = (UserInRoleJPAPK) o;
		return (this.idRole == other.idRole)
			&& (this.idUser == other.idUser);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + ((int) (this.idRole ^ (this.idRole >>> 32)));
		hash = hash * prime + ((int) (this.idUser ^ (this.idUser >>> 32)));
		return hash;
	}

}
