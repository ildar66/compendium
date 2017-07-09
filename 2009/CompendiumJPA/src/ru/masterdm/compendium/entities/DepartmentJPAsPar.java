package ru.masterdm.compendium.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQuery;


@Entity
@Table(name="DEPARTMENTS_PAR")
@NamedQuery(name="getDepartmentsParAll", query = "SELECT d FROM DepartmentJPAsPar d")
public class DepartmentJPAsPar implements Serializable {
	@Column(name="ID_DEPARTMENT_PAR")
	private long idDepartmentPar;

	@Id
	@Column(name="ID_DEPARTMENT_CHILD")
	private long idDepartmentChild;

	private static final long serialVersionUID = 1L;

	public DepartmentJPAsPar() {
		super();
	}
	
	public DepartmentJPAsPar(long key) {
		super();
		setIdDepartmentChild(key);
	}	

	public long getIdDepartmentPar() {
		return idDepartmentPar;
	}

	public void setIdDepartmentPar(long idDepartmentPar) {
		this.idDepartmentPar = idDepartmentPar;
	}

	public long getIdDepartmentChild() {
		return idDepartmentChild;
	}

	public void setIdDepartmentChild(long idDepartmentChild) {
		this.idDepartmentChild = idDepartmentChild;
	}

}
