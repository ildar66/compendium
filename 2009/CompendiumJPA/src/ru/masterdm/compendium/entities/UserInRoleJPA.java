package ru.masterdm.compendium.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.NamedQuery;

@Entity
@Table(name="USER_IN_ROLE")
@NamedQuery(name="getUserInRoleJPA", query = "SELECT u FROM UserInRoleJPA u WHERE u.pk.idUser = :pk_idUser")
public class UserInRoleJPA implements Serializable {
	@EmbeddedId
	private UserInRoleJPAPK pk;

	private String status = "N";

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="ID_ROLE")
	private RoleJPA role;	

	public RoleJPA getRole() {
		return role;
	}

	public void setRole(RoleJPA role) {
		this.role = role;
	}

	public UserInRoleJPA(UserInRoleJPAPK pk) {
		super();
		this.pk = pk;
	}

	public UserInRoleJPA() {
		super();
	}

	public UserInRoleJPAPK getPk() {
		return this.pk;
	}

	public void setPk(UserInRoleJPAPK pk) {
		this.pk = pk;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
