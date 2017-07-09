package ru.masterdm.compendium.beans;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import ru.masterdm.compendium.custom.UserTO;
import ru.masterdm.compendium.exception.ModelException;
import ru.masterdm.compendium.model.ActionProcessorFactory;
import ru.masterdm.compendium.model.CompendiumActionProcessor;
import ru.masterdm.compendium.value.BeanKeys;

/**
 * Form bean for a QuestionListForm.
 * 
 * @version 1.0
 * @author IShafigullin
 */
public class UserToRoleListBean extends CompendiumBean{
	public final static String ADD_LINK = "addLink";
	public final static String DELETE_LINK = "deleteLink";
	public final static String ACTIVE_LINK = "activeLink";
	public final static String PASSIVE_LINK = "passiveLink";
	public final static String ACTIVE_ALL_LINK = "activeAllLink";
	public final static String PASSIVE_ALL_LINK = "passiveAllLink";
	public final static String ADD_ALL_BY_PROCESS_LINK = "addAllByProcessLink";
	public final static String DELETE_ALL_BY_PROCESS_LINK = "deleteAllByProcessLink";
	
	private String searchStr = new String("");
	private String orderBy = "c.role.nameRole";
	private String orderAccessBy = "c.nameRole";	
	private String activeState = "Y";
	
	private String id = null;
	private UserTO userTO = null;

	private String processID = "ALL";
	
	private java.lang.String operation = "";
	private String selectedID = null;
	
	public String passiveAllLink(){
		//setStatusAllLinkUserToRole(userKey, "N");
		return null;
		
	}
	
	public String activeAllLink(){
		//setStatusAllLinkUserToRole(userKey, "Y");
		return null;
	}
	
	public String processOperation() {
		String operation = getOperation();
		String userKey = getId();
		System.out.println("processOperation: operationt="+ operation +" id="+ userKey);//TODO
		//String userKey = getUserVO().getLogin();
		String roleKey = getSelectedID();
		// Get actionProcess (model) and delegate
		CompendiumActionProcessor processor = (CompendiumActionProcessor) ActionProcessorFactory
				.getActionProcessor("Compendium");		
		try {
			if (DELETE_LINK.equalsIgnoreCase(operation)) {
				processor.deleteLinkUserToRole(userKey, roleKey);
			} else if (ADD_LINK.equalsIgnoreCase(operation)) {
				processor.addLinkUserToRole(userKey, roleKey);
			} else if (ACTIVE_LINK.equalsIgnoreCase(operation)) {
				processor.setStatusLinkUserToRole(userKey, roleKey, "Y");
			} else if (PASSIVE_LINK.equalsIgnoreCase(operation)) {
				processor.setStatusLinkUserToRole(userKey, roleKey, "N");
			} else if (ACTIVE_ALL_LINK.equalsIgnoreCase(operation)){
				processor.setStatusAllLinkUserToRole(userKey, "Y");
			} else if (PASSIVE_ALL_LINK.equalsIgnoreCase(operation)){
				processor.setStatusAllLinkUserToRole(userKey, "N");
			} else if (DELETE_ALL_BY_PROCESS_LINK.equalsIgnoreCase(operation)){
				processor.deleteAllLinkUserToRoleByProcessSPO(userKey, processID);
			} else if (ADD_ALL_BY_PROCESS_LINK.equalsIgnoreCase(operation)) {
				processor.addAllLinkUserToRoleByProcessSPO(userKey, processID);
			}
			clear();
		} catch (ModelException e) {
			//errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.txt", e.getMessage()));/TODO
			Logger.getAnonymousLogger().log(Level.SEVERE, "Exception caught in UserToRoleListAction.processOperation:" + e);
		}
		
		return "";
	}	
	
	public String getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(String selectedID) {
		this.selectedID = selectedID;
	}

	public java.lang.String getOperation() {
		return operation;
	}

	public void setOperation(java.lang.String operation) {
		this.operation = operation;
	}

	public String getProcessID() {
		return processID;
	}

	public void setProcessID(String processID) {
		this.processID = processID;
	}

	public String getId() {
		return id;
	}

	public UserTO getUserTO() {
		initUserID();
		return userTO;
	}

	private void setUserTO(UserTO userTO) {
		this.userTO = userTO;
	}	

	public String getActiveState() {
		return activeState;
	}

	public void setActiveState(String activeState) {
		this.activeState = activeState;
	}

	/**
	 * @return
	 */
	public String getSearchStr() {
		return searchStr;
	}

	/**
	 * @param string
	 */
	public void setSearchStr(String string) {
		searchStr = string;
		clear();
	}

	/**
	 * @return
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * @param string
	 */
	public void setOrderBy(String string) {
		orderBy = string;
		clear();
	}

	public String getOrderAccessBy() {
		return orderAccessBy;
	}

	public void setOrderAccessBy(String orderAccessBy) {
		this.orderAccessBy = orderAccessBy;
	}

	public String search() {
		clear();
		return null;
	}

	public String clearFilter() {
		setSearchStr("");
		clear();
		return null;
	}

	public String changeOrderBy() {
		String newOrderBy = getParam("orderBy");
		setOrderBy(newOrderBy);
		clear();
		return null;
	}

	public ArrayList<?> getList() {
		ArrayList<?> list = (ArrayList<?>) request().getAttribute(BeanKeys.USER_TO_ROLE_LIST);
		if (list == null) {
			try {
				// Get actionProcess (model) and delegate
				CompendiumActionProcessor processor = (CompendiumActionProcessor) ActionProcessorFactory
						.getActionProcessor("Compendium");
				list = processor.findUserToRoleList(getId(), getOrderBy());

				request().setAttribute(BeanKeys.USER_TO_ROLE_LIST, list);
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}
	
	public ArrayList<?> getAccessList() {
		ArrayList<?> list = (ArrayList<?>) request().getAttribute(BeanKeys.USER_TO_ROLE_ACCESS_LIST);
		if (list == null) {
			try {
				// Get actionProcess (model) and delegate
				CompendiumActionProcessor processor = (CompendiumActionProcessor) ActionProcessorFactory
						.getActionProcessor("Compendium");

				list = processor.findUserToRoleAccessList(getId(), getProcessID(), getOrderAccessBy());

				request().setAttribute(BeanKeys.USER_TO_ROLE_ACCESS_LIST, list);
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	/**
	 * 
	 */
	private void initUserID() {
		addProcessTypeListBean();
		String userID = getParam("id");
		if (userID != null && !"".equals(userID)) {
			this.id = userID;
					// Get actionProcess (model) and delegate
			CompendiumActionProcessor processor = (CompendiumActionProcessor) ActionProcessorFactory
					.getActionProcessor("Compendium");
			UserTO user;
			try {
				user = (UserTO) processor.findUserTO(userID);
				setUserTO(user);
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}	

	private void clear(){
		request().setAttribute(BeanKeys.USER_TO_ROLE_ACCESS_LIST, null);
		request().setAttribute(BeanKeys.USER_TO_ROLE_LIST, null);
	}
}
