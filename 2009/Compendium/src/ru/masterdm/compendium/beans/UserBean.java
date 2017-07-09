package ru.masterdm.compendium.beans;

import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

import ru.masterdm.compendium.custom.UserTO;
import ru.masterdm.compendium.custom.UserToDepartmentTO;
import ru.masterdm.compendium.domain.User;
import ru.masterdm.compendium.exception.ModelException;
import ru.masterdm.compendium.model.ActionProcessorFactory;
import ru.masterdm.compendium.model.CompendiumActionProcessor;
import ru.masterdm.compendium.value.Name;

import com.corejsf.ActiveBean;
import com.corejsf.Section;

/**
 * @author IShafigullin
 *
 */
public class UserBean extends ActiveBean{
	private CompendiumActionProcessor processor = null;
	private static final Logger LOGGER = Logger
			.getLogger(UserBean.class.getName());
	private User vo = null;
	private String departmentName = null;
	
	private Integer collegOrgan = null;
	private String collegOrganName = null;
	private boolean showCollegOrgan = false;
	
	public boolean isShowCollegOrgan() {
		return showCollegOrgan;
	}

	public void setShowCollegOrgan(boolean showCollegOrgan) {
		this.showCollegOrgan = showCollegOrgan;
	}

	public Integer getCollegOrgan() {
		return collegOrgan;
	}

	public void setCollegOrgan(Integer collegOrgan) {
		this.collegOrgan = collegOrgan;
	}

	public String getCollegOrganName() {
		return collegOrganName;
	}

	public void setCollegOrganName(String collegOrganName) {
		this.collegOrganName = collegOrganName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	private CompendiumActionProcessor getProcessor() {
		if(processor == null){
			processor = (CompendiumActionProcessor) ActionProcessorFactory
			.getActionProcessor("Compendium");			
		}
		return processor;
	}	

	public User getVo() {
		return this.vo;
	}

	public void setVo(User vo) {
		this.vo = vo;
	}

	/**
	 * Default constructor.
	 */
	public UserBean() {
		super();
	}

	private String getParam(String paramName) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();
		return params.get(paramName);
	}

	/**
	 * Initializes bean.
	 */
	@SuppressWarnings("unchecked")
	public String initVO() {
		LOGGER.info("initVO User");
		String action = getParam("action");
		setAction(action);
		LOGGER.info("action=" + getAction());
		String idStr = getParam("id");
		LOGGER.info("id=" + idStr);		
		User aVO = clear();
		if ( idStr != null && (isEdit() || isDelete() || isView())) {
			try {
				//aVO = getProcessor().getUser(new User(Integer.valueOf(idStr)));
				//departmentName = getProcessor().findUserTO(idStr).getDepName();
				UserTO userTO = getProcessor().findUserTO(idStr);
				aVO = userTO.getVo();
				departmentName = userTO.getDepName();
				//департаменты Кредитных комитетов:
				Integer depUserKey = aVO.getDepartmentID();
				java.util.ArrayList depListForCrCo = getProcessor().findUserToDepartmentCrCoList(idStr, null);
				for (java.util.Iterator iterator = depListForCrCo.iterator(); iterator.hasNext();) {
					UserToDepartmentTO to = (UserToDepartmentTO) iterator.next();
					Integer depCrCo = to.getVo().getId();
					String depCrCoName = to.getVo().getFullName();
					LOGGER.info("Department for userID:" + idStr + " = " + depCrCoName);					
					if(!depUserKey.equals(depCrCo)){
						setCollegOrgan(depCrCo);
						setCollegOrganName(depCrCoName);
						setShowCollegOrgan(true);
						break;
					}
				}
				} catch (ModelException e) { 
					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "ModelException:" + e, e.getMessage()), e);
			}
		}
		setVo(aVO);
		LOGGER.info("initVO vo=" + getVo());
		return null;
	}

	/**
	 * @return
	 */
	private User clear() {
		User aVO = new User(1);
		aVO.setName(new Name());
		setDepartmentName(null);
		setCollegOrgan(null);
		setCollegOrganName(null);
		setShowCollegOrgan(false);
		return aVO;
	}

	/**
	 * Cancels meeting creation.
	 * 
	 * @return jsf navigation outcome
	 */
	public String cancel() {
		return computeNextPage();
	}

	/**
	 * 
	 */
	private String computeNextPage() {
		String chapter = getParam("chapter")== null ? "userList" : getParam("chapter");
		Section selectedSection = (Section)request().getSession().getAttribute("section");
		selectedSection.setChapter(chapter);
		LOGGER.info("computeNextPage next chapter=" + chapter);
		return null;
	}
	
	private HttpServletRequest request() {
		return (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
	}	

	public String saveAction() {
		LOGGER.info("update row:" + getVo());
		try {
			getProcessor().updateUser(getVo());
			addLinkUserToDepCrCo("" + getVo().getId());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
		return computeNextPage();

	}

	/**
	 * @throws ModelException
	 */
	@SuppressWarnings("unchecked")
	private void addLinkUserToDepCrCo(String userKey) throws ModelException {
		//привязки к департаментам КК:
		ArrayList depCrCoKeys = new ArrayList();
		if(getVo().getDepartmentID() != null){
			depCrCoKeys.add(getVo().getDepartmentID());
		}
		if(getCollegOrgan() != null){
			depCrCoKeys.add(getCollegOrgan());
		}
		if(depCrCoKeys.size() > 0){
			getProcessor().setLinkUserToDepartmentCrCo(userKey, (Integer[])depCrCoKeys.toArray(new Integer[depCrCoKeys.size()]));
		}
	}

	public String deleteAction() {
		LOGGER.info("delete row:" + getVo());
		try {
			getProcessor().removeUser(getVo());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
		return computeNextPage();
	}
	
	public String createAction() {
		LOGGER.info("create row:" + getVo());
		try {
			String login = getVo().getLogin();
			validateLogin(login);
			//добавление пользователя:
			getProcessor().addUser(getVo());
			UserTO to = getProcessor().findUserByLogin(login);
			if(to != null){
				addLinkUserToDepCrCo("" + to.getVo().getId());
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
		return computeNextPage();

	}

	private void validateLogin(String login) {
		//проверка существует ли логин? 
		try {
			UserTO to = getProcessor().findUserByLogin(login);
			String errorStr = "Логин: " + login + " уже существует у пользователя с userID=" + to.getVo().getId();
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, errorStr, errorStr);
			throw new ValidatorException(message);
		} catch (ModelException e) {
			//логин не существует
		}
	}	
}
