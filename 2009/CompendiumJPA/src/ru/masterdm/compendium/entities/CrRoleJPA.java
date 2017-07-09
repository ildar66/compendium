package ru.masterdm.compendium.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQuery;

@Entity
@Table(name="CR_ROLES")
@NamedQuery(name="getCrRoleJPA", query = "SELECT c FROM CrRoleJPA c WHERE c.active = :active")
public class CrRoleJPA implements Serializable {
	@Id
	@Column(name="ID_ROLE")
	private long idRole;

	@Column(name="NAME_ROLE")
	private String nameRole;

	private BigDecimal active;

	private static final long serialVersionUID = 1L;

	public CrRoleJPA() {
		super();
	}

	public long getIdRole() {
		return this.idRole;
	}

	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}

	public String getNameRole() {
		return this.nameRole;
	}

	public void setNameRole(String nameRole) {
		this.nameRole = nameRole;
	}

	public BigDecimal getActive() {
		return this.active;
	}

	public void setActive(BigDecimal active) {
		this.active = active;
	}

}
