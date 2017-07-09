package com.corejsf;

import javax.faces.context.FacesContext;

/**
 * @author IShafigullin
 *
 */
public class OperatorBean {
	private boolean isSecure = false;
	
	public OperatorBean() {
		super();
		if(isSecure){
			init();
		}
	}

	/**
	 * 
	 */
	private void init() {
		FacesContext context = FacesContext.getCurrentInstance();
	    this.adminSPO = context.getExternalContext().isUserInRole(ADMIN_SPO);
	    this.adminUsers = context.getExternalContext().isUserInRole(ADMIN_USERS);
	    this.adminRoles = context.getExternalContext().isUserInRole(ADMIN_ROLES);
	    this.adminApprove = context.getExternalContext().isUserInRole(ADMIN_APPROVE);
	    this.adminDictionary = context.getExternalContext().isUserInRole(ADMIN_DICTIONARY);
	}

	// Администратор СПО:
	public static final String ADMIN_SPO = "administratorSPO";
	private boolean adminSPO = true;
	
	// Администратор пользователей(доступно добавление пользователей):
	public static final String ADMIN_USERS = "administratorUsers";
	private boolean adminUsers = true;
	
	// Администратор ролей(доступно добавление ролей пользователю на все системы
	// (СПО, КК, Рейтинг)):
	public static final String ADMIN_ROLES = "administratorRoles";
	private boolean adminRoles = true;
	
	// Акцепт-Администратор(доступен акцепт(активация) ролей пользователя):
	public static final String ADMIN_APPROVE = "administratorApprove";
	private boolean adminApprove = true;
	
	// Администратор справочников(доступно добавление/изменение/удаление записей
	// всех справочников):
	public static final String ADMIN_DICTIONARY = "administratorDictionary";
	private boolean adminDictionary = true;

	public boolean isAdminSPO() {
		return adminSPO;
	}

	public boolean isAdminUsers() {
		return adminUsers;
	}

	public boolean isAdminRoles() {
		return adminRoles;
	}

	public boolean isAdminApprove() {
		return adminApprove;
	}

	public boolean isAdminDictionary() {
		return adminDictionary;
	}

}
