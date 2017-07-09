/**
 * 
 */
package ru.masterdm.compendium.entities.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import ru.masterdm.compendium.entities.DepartmentJPAsPar;

import com.ibm.jpa.web.Action;
import com.ibm.jpa.web.JPAManager;
import com.ibm.jpa.web.NamedQueryTarget;

/**
 * @author Администратор
 *
 */
@JPAManager(targetEntity=ru.masterdm.compendium.entities.DepartmentJPAsPar.class)
@SuppressWarnings("unchecked")
public class DepartmentsParManager {

	private EntityManagerFactory emf;

	public DepartmentsParManager() {
	
	}

	public DepartmentsParManager(EntityManagerFactory emf) {
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
	public String createDepartmentsPar(DepartmentJPAsPar departmentsPar)
			throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(departmentsPar);
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
	public String deleteDepartmentsPar(DepartmentJPAsPar departmentsPar)
			throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			departmentsPar = em.merge(departmentsPar);
			em.remove(departmentsPar);
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
	public String updateDepartmentsPar(DepartmentJPAsPar departmentsPar)
			throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			departmentsPar = em.merge(departmentsPar);
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
	public DepartmentJPAsPar findDepartmentsParByIdDepartmentChild(
			long idDepartmentChild) {
		DepartmentJPAsPar departmentsPar = null;
		EntityManager em = getEntityManager();
		try {
			departmentsPar = (DepartmentJPAsPar) em.find(DepartmentJPAsPar.class,
					idDepartmentChild);
		} finally {
			em.close();
		}
		return departmentsPar;
	}

	@Action(Action.ACTION_TYPE.NEW)
	public DepartmentJPAsPar getNewDepartmentsPar() {
		DepartmentJPAsPar departmentsPar = new DepartmentJPAsPar();
		return departmentsPar;
	}

	@NamedQueryTarget("getDepartmentsParAll")
	public List<DepartmentJPAsPar> getDepartmentsParAll() {
		EntityManager em = getEntityManager();
		List<DepartmentJPAsPar> results = null;
		try {
			Query query = em.createNamedQuery("getDepartmentsParAll");
			results = (List<DepartmentJPAsPar>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	} 
 }