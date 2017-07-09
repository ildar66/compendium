package ru.masterdm.compendium.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CC_ROLE")
public class CcRoleJPA implements Serializable {
	@Id
	@Column(name="ID_ROLE")
	private long idRole;

	@Column(name="ROLE_NAME")
	private String roleName;

	@Column(name="ID_PARENT_ROLE")
	private BigDecimal idParentRole;

	private BigDecimal active;

	private static final long serialVersionUID = 1L;

	public CcRoleJPA() {
		super();
	}

	public long getIdRole() {
		return this.idRole;
	}

	public void setIdRole(long idRole) {
		this.idRole = idRole;
	}

	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public BigDecimal getIdParentRole() {
		return this.idParentRole;
	}

	public void setIdParentRole(BigDecimal idParentRole) {
		this.idParentRole = idParentRole;
	}

	public BigDecimal getActive() {
		return this.active;
	}

	public void setActive(BigDecimal active) {
		this.active = active;
	}

}
