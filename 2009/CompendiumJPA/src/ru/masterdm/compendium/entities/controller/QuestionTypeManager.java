/**
 * 
 */
package ru.masterdm.compendium.entities.controller;

import com.ibm.jpa.web.JPAManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import com.ibm.jpa.web.NamedQueryTarget;
import com.ibm.jpa.web.Action;
import ru.masterdm.compendium.entities.QuestionTypeJPA;
import java.util.List;
import javax.persistence.Query;
import java.lang.String;

/**
 * @author Администратор
 *
 */
@JPAManager(targetEntity=ru.masterdm.compendium.entities.QuestionTypeJPA.class)
@SuppressWarnings("unchecked")
public class QuestionTypeManager {

	private EntityManagerFactory emf;

	public QuestionTypeManager() {
	
	}

	public QuestionTypeManager(EntityManagerFactory emf) {
		this.emf = emf;
	}

	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
	}

	private EntityManager getEntityManager() {
		if (emf == null) {
			//You can create an EntityManagerFactory as shown in the code below.
			//If you create an EntityManagerFactory you must call the
			//dispose method when you are done using it.
			//emf = Persistence.createEntityManagerFactory("CompendiumJPA");
			throw new RuntimeException(
					"The EntityManagerFactory is null.  This must be passed in to the constructor or set using the setEntityManagerFactory() method.");
		}
		return emf.createEntityManager();
	}

	@Action(Action.ACTION_TYPE.CREATE)
	public String createQuestionType(QuestionTypeJPA questionType)
			throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(questionType);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "";
	}

	@Action(Action.ACTION_TYPE.DELETE)
	public String deleteQuestionType(QuestionTypeJPA questionType)
			throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			questionType = em.merge(questionType);
			em.remove(questionType);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "";
	}

	@Action(Action.ACTION_TYPE.UPDATE)
	public String updateQuestionType(QuestionTypeJPA questionType)
			throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			questionType = em.merge(questionType);
			em.getTransaction().commit();
		} catch (Exception ex) {
			try {
				if (em.getTransaction().isActive()) {
					em.getTransaction().rollback();
				}
			} catch (Exception e) {
				ex.printStackTrace();
				throw e;
			}
			throw ex;
		} finally {
			em.close();
		}
		return "";
	}

	@Action(Action.ACTION_TYPE.FIND)
	public QuestionTypeJPA findQuestionTypeByIdQuestionType(long idQuestionType) {
		QuestionTypeJPA questionType = null;
		EntityManager em = getEntityManager();
		try {
			questionType = (QuestionTypeJPA) em.find(QuestionTypeJPA.class,
					idQuestionType);
		} finally {
			em.close();
		}
		return questionType;
	}

	@Action(Action.ACTION_TYPE.NEW)
	public QuestionTypeJPA getNewQuestionType() {
		QuestionTypeJPA questionType = new QuestionTypeJPA();
		return questionType;
	}

	@NamedQueryTarget("getQuestionTypeLike")
	public List<QuestionTypeJPA> getQuestionTypeLike(String questionType) {
		EntityManager em = getEntityManager();
		List<QuestionTypeJPA> results = null;
		try {
			Query query = em.createNamedQuery("getQuestionTypeLike");
			query.setParameter("questionType", questionType);
			results = (List<QuestionTypeJPA>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	}

	@NamedQueryTarget("getQuestionTypes")
	public List<QuestionTypeJPA> getQuestionTypes() {
		EntityManager em = getEntityManager();
		List<QuestionTypeJPA> results = null;
		try {
			Query query = em.createNamedQuery("getQuestionTypes");
			results = (List<QuestionTypeJPA>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	} 
 }