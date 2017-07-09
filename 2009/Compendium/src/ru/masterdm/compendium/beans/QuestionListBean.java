package ru.masterdm.compendium.beans;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

import ru.masterdm.compendium.domain.QuestionType;
import ru.masterdm.compendium.exception.ModelException;
import ru.masterdm.compendium.model.ActionProcessorFactory;
import ru.masterdm.compendium.model.CompendiumActionProcessor;

/**
 * Form bean for a QuestionListForm.
 * 
 * @version 1.0
 * @author IShafigullin
 */
public class QuestionListBean {
	private String searchStr = new String("");
	private String orderBy = "c.questionType";
	private String activeState = "Y";

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
		FacesContext context = FacesContext.getCurrentInstance();
		String newOrderBy = getParam(context, "orderBy");
		setOrderBy(newOrderBy);
		clear();
		return null;
	}

	private String getParam(FacesContext context, String paramName) {
		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();
		return params.get(paramName);
	}

	public QuestionType[] getList() {
		QuestionType[] list = (QuestionType[]) request().getAttribute("list");
		if (list == null) {
			try {
				// Get actionProcess (model) and delegate
				CompendiumActionProcessor processor = (CompendiumActionProcessor) ActionProcessorFactory
						.getActionProcessor("Compendium");
				list = processor.findQuestionTypeList(searchStr + "%", orderBy);
				request().setAttribute("list", list);
			} catch (ModelException e) {
				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "ModelException:" + e, e.getMessage()), e);
			}
		}
		return list;
	}

	private HttpServletRequest request() {
		return (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
	}
	
	private void clear(){
		request().setAttribute("list", null);
	}
}
