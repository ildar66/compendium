package ru.masterdm.compendium.entities;
import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.NamedQuery;

@Entity
@Table(name="CR_USER_IN_ROLE")
@NamedQuery(name="getCrUserInRoleJPA", query = "SELECT c FROM CrUserInRoleJPA c WHERE c.pk.idUser = :pk_idUser")
public class CrUserInRoleJPA implements Serializable {
	@EmbeddedId
	private CrUserInRoleJPAPK pk;

	private String status = "N";

	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="ID_ROLE")
	private CrRoleJPA role;	

	public CrRoleJPA getRole() {
		return role;
	}

	public void setRole(CrRoleJPA role) {
		this.role = role;
	}

	public CrUserInRoleJPA() {
		super();
	}

	public CrUserInRoleJPA(CrUserInRoleJPAPK pk) {
		super();
		this.pk = pk;
	}

	public CrUserInRoleJPAPK getPk() {
		return this.pk;
	}

	public void setPk(CrUserInRoleJPAPK pk) {
		this.pk = pk;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
