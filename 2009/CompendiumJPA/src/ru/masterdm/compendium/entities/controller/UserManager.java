/**
 * 
 */
package ru.masterdm.compendium.entities.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import ru.masterdm.compendium.entities.UserJPA;

import com.ibm.jpa.web.Action;
import com.ibm.jpa.web.JPAManager;
import com.ibm.jpa.web.NamedQueryTarget;

/**
 * @author Администратор
 *
 */
@JPAManager(targetEntity=ru.masterdm.compendium.entities.UserJPA.class)
@SuppressWarnings("unchecked")
public class UserManager {

	private EntityManagerFactory emf;

	public UserManager() {
	
	}

	public UserManager(EntityManagerFactory emf) {
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
	public String createUserJPA(UserJPA userJPA) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(userJPA);
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
	public String deleteUserJPA(UserJPA userJPA) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			userJPA = em.merge(userJPA);
			em.remove(userJPA);
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
	public String updateUserJPA(UserJPA userJPA) throws Exception {
		EntityManager em = getEntityManager();
		try {
			em.getTransaction().begin();
			userJPA = em.merge(userJPA);
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
	public UserJPA findUserJPAByIdUser(long idUser) {
		UserJPA userJPA = null;
		EntityManager em = getEntityManager();
		try {
			userJPA = (UserJPA) em.find(UserJPA.class, idUser);
		} finally {
			em.close();
		}
		return userJPA;
	}

	@Action(Action.ACTION_TYPE.NEW)
	public UserJPA getNewUserJPA() {
		UserJPA userJPA = new UserJPA();
		return userJPA;
	}

	@NamedQueryTarget("findUserByLogin")
	public List<UserJPA> findUserByLogin(String login) {
		EntityManager em = getEntityManager();
		List<UserJPA> results = null;
		try {
			Query query = em.createNamedQuery("findUserByLogin");
			query.setParameter("login", login);
			results = (List<UserJPA>) query.getResultList();
		} finally {
			em.close();
		}
		return results;
	} 
 }