package hibernate.mgr;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

public class HibernateUtil {

	private static SessionFactory sessionFactory; 
	
	static{
		try {
			AnnotationConfiguration config = new AnnotationConfiguration();
			sessionFactory = config.configure("hibernate.cfg.xml").buildSessionFactory();
		}catch (Throwable e) {
			throw new ExceptionInInitializerError(e);
		}
		
	}
	
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public static void shutdown(){
		getSessionFactory().close();
	}

	
}
