package ru.masterdm.compendium.entities.test;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ru.masterdm.compendium.entities.*;

public class EntityTester {
	static EntityManager em;
	protected static final String findListByFilterSqlString = "SELECT u.ID_USER id, u.LOGIN login, u.SURNAME, u.NAME, u.PATRONYMIC, u.ID_DEPARTMENT, u.MAIL_USER eMail, d.fullName depName FROM "
		+ "USERS u, DEPARTMENTS d WHERE u.ID_DEPARTMENT = d.ID_DEPARTMENT ";
	
	/**
	 * @param args
	 */

	public static void main(String[] args) {
		//String customerId = "111-11-1111";
		//if (args.length > 0) customerId = args[0];
		System.out.println("Entity Testing");
		System.out.println("\nCreating EntityManager");
		em = Persistence.createEntityManagerFactory("CompendiumJPA_Test").createEntityManager();
		System.out.println("CompendiumJPA EntityManager successfully created\n");
		//em.getTransaction().begin();
		//getQuestionTypes();
		//getUserInRoleJPA();
		//getCrUserInRoleJPA();
		findListUsersByFilter(10, 5);		
		//em.getTransaction().commit ();
	}

	@SuppressWarnings("unchecked")
	private static void findListUsersByFilter(int max, int start) {
		Query query;
		System.out.println("\n getUserJPA: ");
		query = em.createNativeQuery(findListByFilterSqlString).setMaxResults(max).setFirstResult(start);
		//query.setParameter("pk_idUser", (long)27);
		List<Object[]> jpaList = query.getResultList();
		for (Iterator iterator = jpaList.iterator(); iterator.hasNext();) {
			Object[] jpa = (Object[]) iterator.next();
			System.out.println(jpa[0].getClass());
		}
	}
	
	@SuppressWarnings("unchecked")
	protected static void getCrUserInRoleJPA() {
		Query query;
		System.out.println("\n getCrUserInRoleJPA: ");
		query = em.createNamedQuery("getCrUserInRoleJPA");
		query.setParameter("pk_idUser", (long)27);
		List<CrUserInRoleJPA> jpaList3 = query.getResultList();
		for (CrUserInRoleJPA jpa : jpaList3) {
			System.out.println("getNameRole=" + jpa.getRole().getNameRole() );
		}
	}
	
	@SuppressWarnings("unchecked")	
	protected static void getUserInRoleJPA() {
		Query query;
		System.out.println("\n getUserInRoleJPA: ");
		query = em.createNamedQuery("getUserInRoleJPA");
		query.setParameter("pk_idUser", (long)27);
		List<UserInRoleJPA> jpaList2 = query.getResultList();
		for (UserInRoleJPA jpa : jpaList2) {
			System.out.println("getNameRole=" + jpa.getRole().getNameRole() + "; getDescriptionProcess=" + jpa.getRole().getProcess().getDescriptionProcess());
		}
	}
	
	@SuppressWarnings("unchecked")	
	protected static void getQuestionTypes() {
		System.out.println("\n getQuestionTypes: ");
		Query query = em.createNamedQuery("getQuestionTypes");
		List<QuestionTypeJPA> jpaList1 = query.getResultList();
		for (QuestionTypeJPA jpa : jpaList1) {
			System.out.println(jpa);
		}
	}

}
