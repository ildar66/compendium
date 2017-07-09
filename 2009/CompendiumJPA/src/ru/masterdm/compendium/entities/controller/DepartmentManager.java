/**
 * 
 */
package ru.masterdm.compendium.entities.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import ru.masterdm.compendium.entities.DepartmentJPA;

import com.ibm.jpa.web.Action;
import com.ibm.jpa.web.JPAManager;
import com.ibm.jpa.web.NamedQueryTarget;

/**
 * @author Администратор
 *
 */
@JPAManager(targetEntity=ru.masterdm.compendium.entities.DepartmentJPA.class)
@SuppressWarnings("unchecked")
public class DepartmentManager {

	private EntityManagerFactory emf;

	public DepartmentManager() {
	
	}

	public DepartmentManager(EntityManagerFactory emf) {
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
	public String createDepartment(DepartmentJPA department) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(department);
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
	public String deleteDepartment(DepartmentJPA department) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			department = em.merge(department);
			em.remove(department);
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
	public String updateDepartment(DepartmentJPA department) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			department = em.merge(department);
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
	public DepartmentJPA findDepartmentByIdDepartment(long idDepartment) {
		DepartmentJPA department = null;
		EntityManager em = getEntityManager();
		try {
			department = (DepartmentJPA) em.find(DepartmentJPA.class, idDepartment);
		} finally {
			em.close();
		}
		return department;
	}

	@Action(Action.ACTION_TYPE.NEW)
	public DepartmentJPA getNewDepartment() {
		DepartmentJPA department = new DepartmentJPA();
		return department;
	}

	@NamedQueryTarget("getDepartmentListAll")
	public List<DepartmentJPA> getDepartmentListAll() {
		EntityManager em = getEntityManager();
		List<DepartmentJPA> results = null;
		try {
			Query query = em.createNamedQuery("getDepartmentListAll");
			results = (List<DepartmentJPA>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	} 
 }