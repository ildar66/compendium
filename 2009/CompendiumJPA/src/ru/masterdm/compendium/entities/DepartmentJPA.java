package ru.masterdm.compendium.entities;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "DEPARTMENTS")
@NamedQuery(name="getDepartmentListAll", query = "SELECT d FROM DepartmentJPA d ORDER BY d.fullName")
public class DepartmentJPA implements Serializable {
	@Override
	public String toString() {
		return "ID: " + getIdDepartment() + " fullName="  + getFullName();
	}

	@Id
	@Column(name="ID_DEPARTMENT")
	private long idDepartment;

	private String shortName;

	private String fullName;

	@Column(name="DEP_ID")
	private BigDecimal depId;

	@Column(name="CB_CD")
	private BigDecimal cbCd;

	@Column(name="ID_COMMITTEE_TYPE")
	private BigDecimal idCommitteeType;

	private static final long serialVersionUID = 1L;

	public DepartmentJPA() {
		super();
	}

	public long getIdDepartment() {
		return this.idDepartment;
	}

	public void setIdDepartment(long idDepartment) {
		this.idDepartment = idDepartment;
	}

	public String getShortName() {
		return this.shortName;
	}

	public void setShortName(String shortname) {
		this.shortName = shortname;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullname) {
		this.fullName = fullname;
	}

	public BigDecimal getDepId() {
		return this.depId;
	}

	public void setDepId(BigDecimal depId) {
		this.depId = depId;
	}

	public BigDecimal getCbCd() {
		return this.cbCd;
	}

	public void setCbCd(BigDecimal cbCd) {
		this.cbCd = cbCd;
	}

	public BigDecimal getIdCommitteeType() {
		return this.idCommitteeType;
	}

	public void setIdCommitteeType(BigDecimal idCommitteeType) {
		this.idCommitteeType = idCommitteeType;
	}
}
