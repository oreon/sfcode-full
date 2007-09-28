package org.witchcraft.model.support;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.acegisecurity.userdetails.jdbc.JdbcDaoImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.springframework.dao.DataAccessException;

public class AuthenticationJdbcDaoImpl extends JdbcDaoImpl {
	private String[] userInfoObjectTypes = { "Customer", "Employee" };

	// FIXME: This has to be replaced with JPA EntityManager
	private static final SessionFactory sessionFactory = new AnnotationConfiguration()
			.configure().buildSessionFactory();

	//private EntityManager entityManager;

	@Override
	public UserDetails loadUserByUsername(String username) {
		try {
			UserDetails user = super.loadUserByUsername(username);
			System.out.println("Authenticating " + username);
			
			Session session = sessionFactory.openSession();
			//Session session = (Session) entityManager.getDelegate();

			for (int i = 0; i < userInfoObjectTypes.length; i++) {
				
				Object userInfo = session.createQuery(
						"Select e from " + userInfoObjectTypes[i]
								+ " e where e.userAccount.username = '"
								+ username + "'").uniqueResult();
				if (userInfo != null)
					return new CustomUser(user.getUsername(), user
							.getPassword(), user.isEnabled(), user
							.getAuthorities(), userInfo);
			}

			System.out.println("This user doesnt belong to any of userinfoobjects " + username + " " + user.getPassword());
			 return new CustomUser(user.getUsername(), user.getPassword(),user
					 .isEnabled(), user.getAuthorities());
		} catch (UsernameNotFoundException ex1) {
			ex1.printStackTrace();
			throw ex1;
		} catch (DataAccessException ex2) {
			ex2.printStackTrace();
			throw ex2;
		}
	}

	public void setUserInfoObjectTypes(String[] userInfoObjectTypes) {
		this.userInfoObjectTypes = userInfoObjectTypes;
	}
}