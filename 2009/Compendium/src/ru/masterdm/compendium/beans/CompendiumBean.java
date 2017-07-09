/**
 * 
 */
package ru.masterdm.compendium.beans;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ru.masterdm.compendium.domain.ProcessType;
import ru.masterdm.compendium.exception.ModelException;
import ru.masterdm.compendium.model.ActionProcessorFactory;
import ru.masterdm.compendium.model.CompendiumActionProcessor;
import ru.masterdm.compendium.value.BeanKeys;

/**
 * @author IShafigullin
 * 
 */
public class CompendiumBean {

	/**
	 * 
	 */
	public CompendiumBean() {
		super();
	}

	/**
	 * 
	 * @param request

	protected void addOwnershipFormTypeListBean(HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (session.getAttribute(BeanKeys.OWNERSHIP_FORM_TYPE_LIST) != null) {
			return;
		}
		CompendiumActionProcessor processor = (CompendiumActionProcessor) ActionProcessorFactory
				.getActionProcessor("Compendium");
		ArrayList ownershipFormTypeList = null;
		try {
			ownershipFormTypeList = processor.findOwnershipFormTypeList("%", "name");
		} catch (ModelException mme) {
			mme.printStackTrace();
		}

		// Add ValueBean to request context and forward response
		// request.setAttribute(BeanKeys.COMPANIES, companyes);

		session.setAttribute(BeanKeys.OWNERSHIP_FORM_TYPE_LIST, ownershipFormTypeList);
	}
	 */	
	
	/**
	 * 
	 * @param request
	 */
	protected void addProcessTypeListBean() {
		HttpSession session = request().getSession();
		if (session.getAttribute(BeanKeys.PROCESS_TYPE_LIST) != null) {
			return;
		}
		CompendiumActionProcessor processor = (CompendiumActionProcessor) ActionProcessorFactory
				.getActionProcessor("Compendium");
		List<SelectItem> sectionItems = new LinkedList<SelectItem>();
		try {
			List<?> processList = processor.findProcessTypeList("%", "p.descriptionProcess");
			Iterator<?> it = processList.iterator();
			while (it.hasNext()) {
				ProcessType item = (ProcessType)it.next();
				sectionItems.add(new SelectItem(item.getId(), item.getDescription()));

			}			
		} catch (ModelException mme) {
			mme.printStackTrace();
		}

		// Add ValueBean to request context and forward response
		// request.setAttribute(BeanKeys.COMPANIES, companyes);

		session.setAttribute(BeanKeys.PROCESS_TYPE_LIST, sectionItems);
	}

	/**
	 * @param paramName
	 * @return
	 */
	protected String getParam(String paramName) {
		FacesContext context = FacesContext.getCurrentInstance();
		Map<String, String> params = context.getExternalContext()
				.getRequestParameterMap();
		return params.get(paramName);
	}

	/**
	 * @return
	 */
	protected HttpServletRequest request() {
		return (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
	}	
	
	/**
	 * 
	 * @param request
	protected void addRegionListBean(HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (session.getAttribute(BeanKeys.REGION_LIST) != null) {
			return;
		}
		DictionaryActionProcessor processor = (DictionaryActionProcessor) ActionProcessorFactory
				.getActionProcessor("Dictionary");
		ArrayList regionList = null;
		try {
			regionList = processor.findRegions("%", "name");
		} catch (ModelException mme) {
			mme.printStackTrace();
		}

		// Add ValueBean to request context and forward response
		// request.setAttribute(BeanKeys.COMPANIES, companyes);

		session.setAttribute(BeanKeys.REGION_LIST, regionList);
	}
	 */
	
	/**
	 * 
	 * @param request

	protected void addIndustryListBean(HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (session.getAttribute(BeanKeys.INDUSTRY_LIST) != null) {
			return;
		}
		DictionaryActionProcessor processor = (DictionaryActionProcessor) ActionProcessorFactory
				.getActionProcessor("Dictionary");
		ArrayList aList = null;
		try {
			aList = processor.findIndustries("%", "name");
		} catch (ModelException mme) {
			mme.printStackTrace();
		}

		// Add ValueBean to request context and forward response
		// request.setAttribute(BeanKeys.COMPANIES, companyes);

		session.setAttribute(BeanKeys.INDUSTRY_LIST, aList);
	}
*/
}
