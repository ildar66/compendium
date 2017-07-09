/**
 * 
 */
package ru.masterdm.compendium.domain;

import ru.masterdm.compendium.value.Name;

/**
 * VtbObject "пользователи системы"
 * @author IShafigullin
 * 
 */
public class User extends VtbObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id = null; // id
	private String login = null; // Логин
	private String pass = null; // password
	private Name name = null; // ФИО
	private Integer departmentID = null;// id филиала
	private String eMail = null; // почта оператора
	
	public User(Integer id) {
		super();
		this.id = id;
	}	

	public User(Integer aId, String aLogin) {
		this(aId);
		setLogin(aLogin);
	}	

	@Override
	public boolean equals(Object anObject) {
		if (anObject == null) {
			return false;
		}
		if (!(anObject instanceof User)) {
			return false;
		}
		User aOperators = (User) anObject;
		return aOperators.getId().equals(getId());
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("User: ");
		sb.append(getId() + "(" + getLogin() + ")");
		// sb.append('\n');
		// sb.append(" IsActive: ");
		// sb.append(getIsActive());

		return sb.toString();
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}	

	public Integer getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(Integer departmentID) {
		this.departmentID = departmentID;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEMail() {
		return eMail;
	}

	public void setEMail(String mail) {
		eMail = mail;
	}
}
