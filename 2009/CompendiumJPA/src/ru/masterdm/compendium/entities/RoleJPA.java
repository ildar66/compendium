package ru.masterdm.compendium.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Roles")
public class RoleJPA implements Serializable {
	@Id
	@Column(name="ID_ROLE")
	private long idRole;

	@Column(name="NAME_ROLE")
	private String nameRole;

	@ManyToOne
	@JoinColumn(name="ID_TYPE_PROCESS")
	private ProcessTypeJPA process;

	public ProcessTypeJPA getProcess() {
		return process;
	}

	public void setProcess(ProcessTypeJPA process) {
		this.process = process;
	}

	private BigDecimal active;

	@Column(name="IS_ADMIN")
	private BigDecimal isAdmin;

	private static final long serialVersionUID = 1L;

	public RoleJPA() {
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

	public BigDecimal getIsAdmin() {
		return this.isAdmin;
	}

	public void setIsAdmin(BigDecimal isAdmin) {
		this.isAdmin = isAdmin;
	}

}
