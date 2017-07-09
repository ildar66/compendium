package ru.masterdm.compendium.beans;

import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import ru.masterdm.compendium.exception.ModelException;
import ru.masterdm.compendium.model.ActionProcessorFactory;
import ru.masterdm.compendium.model.CompendiumActionProcessor;
import ru.masterdm.compendium.value.BeanKeys;
import ru.masterdm.compendium.value.Page;

/**
 * 
 * @version 1.0
 * @author IShafigullin
 */
public class UserListBean {
	public final static int BY_LOGIN = 1;
	public final static int BY_FIO = 2;
	public final static int BY_FIO_OR_LOGIN = 0;
	private int searchFilter = BY_FIO_OR_LOGIN;
	
	public final static int ROW_COUNT = 25;
	private int count = ROW_COUNT;
	
	public final static int START = 0;	
	int start = START;
	
	public int getSearchFilter() {
		return searchFilter;
	}

	public void setSearchFilter(int searchFilter) {
		this.searchFilter = searchFilter;
	}

	private String searchStr = new String("");
	private String orderBy = "login";
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
		this.start = START;
		clear();
		return null;
	}

	public String clearFilter() {
		setSearchStr("");
		clearPaging();
		clear();
		return null;
	}

	/**
	 * 
	 */
	private void clearPaging() {
		this.count = ROW_COUNT;
		this.start = START;
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

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = Math.max(Math.abs(start), 0);;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count =  Math.max(Math.abs(count), 1);
	}

	public String nextPage(){
		setStart(getStart() + count);
		clear();
		return null;
	}
	public String previousPage(){
		setStart(getStart() - count);
		clear();
		return null;
	}	
	
	public Page getPage() {
		Page page = (Page) request().getAttribute(BeanKeys.USER_PAGE);
		if (page == null) {
			try {
				// Get actionProcess (model) and delegate
				CompendiumActionProcessor processor = (CompendiumActionProcessor) ActionProcessorFactory
						.getActionProcessor("Compendium");

				page = processor.findUserPage(getSearchFilter(), getSearchStr() + "%", getStart(), getCount(), getOrderBy());

				request().setAttribute(BeanKeys.USER_PAGE, page);
			} catch (ModelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return page;
	}	

	private HttpServletRequest request() {
		return (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
	}
	
	private void clear(){
		request().setAttribute(BeanKeys.USER_LIST, null);
		request().setAttribute(BeanKeys.USER_PAGE, null);
	}
}
