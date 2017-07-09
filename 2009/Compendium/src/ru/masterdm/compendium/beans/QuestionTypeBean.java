package ru.masterdm.compendium.beans;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

import ru.masterdm.compendium.domain.QuestionType;
import ru.masterdm.compendium.exception.ModelException;
import ru.masterdm.compendium.model.ActionProcessorFactory;
import ru.masterdm.compendium.model.CompendiumActionProcessor;

import com.corejsf.ActiveBean;
import com.corejsf.Section;

/**
 * @author IShafigullin
 *
 */
public class QuestionTypeBean extends ActiveBean{
	private static final Logger LOGGER = Logger
			.getLogger(QuestionTypeBean.class.getName());
	private QuestionType vo = null;

	public QuestionType getVo() {
		if (this.vo == null) {
			initVO();
		}
		return this.vo;
	}

	public void setVo(QuestionType vo) {
		this.vo = vo;
	}

	/**
	 * Default constructor.
	 */
	public QuestionTypeBean() {
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
	public String initVO() {
		LOGGER.info("initVO QuestionType");
		String action = getParam("action");
		setAction(action);		
		String idStr = getParam("id");
		QuestionType aVO = new QuestionType(null);
		try {
			if (idStr != null && (isEdit() || isDelete() || isView())) {
				aVO = getProcessor().findQuestionType(new QuestionType(Integer.valueOf(idStr)));
			}
		} catch (ModelException e) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "ModelException:" + e, e.getMessage()), e);
		}
		setVo(aVO);
		LOGGER.info("initVO vo=" + getVo());
		return null;
	}
	
	// Get actionProcess (model) and delegate
	private CompendiumActionProcessor getProcessor() {
		CompendiumActionProcessor processor = (CompendiumActionProcessor) ActionProcessorFactory
				.getActionProcessor("Compendium");
		return processor;
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
		String chapter = getParam("chapter")== null ? "questionList" : getParam("chapter");
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
			getProcessor().updateQuestionType(getVo());
		} catch (ModelException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
		return computeNextPage();

	}

	public String deleteAction() {
		LOGGER.info("delete row:" + getVo());
		try {
			getProcessor().removeQuestionType(getVo());
		} catch (ModelException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
		return computeNextPage();
	}
	
	public String createAction() {
		LOGGER.info("create row:" + getVo());
		try {
			getProcessor().addQuestionType(getVo());
		} catch (ModelException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
		return computeNextPage();

	}	
}
