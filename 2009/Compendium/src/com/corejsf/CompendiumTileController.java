package com.corejsf;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tiles.ComponentContext;
import org.apache.tiles.Controller;

import ru.masterdm.compendium.beans.QuestionTypeBean;

/**
 * @author IShafigullin
 *
 */
public class CompendiumTileController implements Controller {
	private static final Logger LOGGER = Logger.getLogger(QuestionTypeBean.class.getName());
	public void execute(ComponentContext tilesContext,
			HttpServletRequest request, HttpServletResponse response,
			ServletContext context) throws IOException, ServletException {
		HttpSession session = request.getSession();
		// init справочника:
		Compendium compendium = (Compendium) session.getAttribute("compendium");
		if (compendium == null) {
			compendium = new Compendium();
			session.setAttribute("compendium", compendium);
		}
		// init секции(меню) справочника:
		Section selectedSection = compendium.getSelectedSection();
		if (selectedSection != null) {
			session.setAttribute("section", selectedSection);
		}
		// init текущего раздела секции(меню) справочника:
		String chapter = (String) request.getParameter("chapter");
		if (chapter != null && !"".equals(chapter)) {
			//session.setAttribute("chapter", chapter);
			selectedSection.setChapter(chapter);
		}
		/**
		if (session.getAttribute("chapter") == null) {
			session.setAttribute("chapter", "chapter1");
		}
		*/
		LOGGER.info("=======From TilesConrtoller: chapter = " + selectedSection.getChapter());
	}

	public void perform(ComponentContext tilesContext,
			HttpServletRequest request, HttpServletResponse response,
			ServletContext context) throws IOException, ServletException {
		// HttpSession session = request.getSession();
		execute(tilesContext, request, response, context);
	}
}
