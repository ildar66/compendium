/**
 * 
 */
package ru.masterdm.compendium.entities.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import ru.masterdm.compendium.entities.ProcessTypeJPA;

import com.ibm.jpa.web.Action;
import com.ibm.jpa.web.JPAManager;
import com.ibm.jpa.web.NamedQueryTarget;

/**
 * @author Администратор
 *
 */
@JPAManager(targetEntity=ru.masterdm.compendium.entities.ProcessTypeJPA.class)
@SuppressWarnings("unchecked")
public class ProcessTypeJPAManager {

	private EntityManagerFactory emf;

	public ProcessTypeJPAManager() {
	
	}

	public ProcessTypeJPAManager(EntityManagerFactory emf) {
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
	public String createProcessTypeJPA(ProcessTypeJPA processTypeJPA)
			throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(processTypeJPA);
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
	public String deleteProcessTypeJPA(ProcessTypeJPA processTypeJPA)
			throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			processTypeJPA = em.merge(processTypeJPA);
			em.remove(processTypeJPA);
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
	public String updateProcessTypeJPA(ProcessTypeJPA processTypeJPA)
			throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			processTypeJPA = em.merge(processTypeJPA);
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
	public ProcessTypeJPA findProcessTypeJPAByIdTypeProcess(long idTypeProcess) {
		ProcessTypeJPA processTypeJPA = null;
		EntityManager em = getEntityManager();
		try {
			processTypeJPA = (ProcessTypeJPA) em.find(ProcessTypeJPA.class,
					idTypeProcess);
		} finally {
			em.close();
		}
		return processTypeJPA;
	}

	@Action(Action.ACTION_TYPE.NEW)
	public ProcessTypeJPA getNewProcessTypeJPA() {
		ProcessTypeJPA processTypeJPA = new ProcessTypeJPA();
		return processTypeJPA;
	}

	@NamedQueryTarget("findProcessTypeList")
	public List<ProcessTypeJPA> findProcessTypeList(String descriptionProcess) {
		EntityManager em = getEntityManager();
		List<ProcessTypeJPA> results = null;
		try {
			Query query = em.createNamedQuery("findProcessTypeList");
			query.setParameter("descriptionProcess", descriptionProcess);
			results = (List<ProcessTypeJPA>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	} 
 }