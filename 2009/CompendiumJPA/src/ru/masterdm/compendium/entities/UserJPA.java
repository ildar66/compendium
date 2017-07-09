package ru.masterdm.compendium.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQuery;

@Entity
@Table(name = "Users")
@NamedQuery(name="findUserByLogin", query = "SELECT u FROM UserJPA u WHERE u.login = :login")
public class UserJPA implements Serializable {
	@Id
	@Column(name="ID_USER")
	private long idUser;

	@Column(name="MAIL_USER")
	private String mailUser;

	@Column(name="IP_USER")
	private String ipUser;

	private String login;

	private String surname;

	private String name;

	private String patronymic;

	@Column(name="ID_DEPARTMENT")
	private BigDecimal idDepartment;

	@Column(name="IS_ACTIVE")
	private BigDecimal isActive;

	private static final long serialVersionUID = 1L;

	public UserJPA() {
		super();
	}

	public long getIdUser() {
		return this.idUser;
	}

	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}

	public String getMailUser() {
		return this.mailUser;
	}

	public void setMailUser(String mailUser) {
		this.mailUser = mailUser;
	}

	public String getIpUser() {
		return this.ipUser;
	}

	public void setIpUser(String ipUser) {
		this.ipUser = ipUser;
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPatronymic() {
		return this.patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public BigDecimal getIdDepartment() {
		return this.idDepartment;
	}

	public void setIdDepartment(BigDecimal idDepartment) {
		this.idDepartment = idDepartment;
	}

	public BigDecimal getIsActive() {
		return this.isActive;
	}

	public void setIsActive(BigDecimal isActive) {
		this.isActive = isActive;
	}

}
