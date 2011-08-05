package org.witchcraft.action.test;

import java.util.List;
import java.util.Random;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.seam.security.Identity;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.smartsis.Gender;
import com.oreon.smartsis.domain.Employee;
import com.oreon.smartsis.domain.Exam;
import com.oreon.smartsis.domain.Grade;
import com.oreon.smartsis.domain.GradeSubject;
import com.oreon.smartsis.domain.Student;
import com.oreon.smartsis.domain.Subject;
import com.oreon.smartsis.users.Role;
import com.oreon.smartsis.users.User;
import com.oreon.smartsis.web.action.users.UserAction;

public class AuthenticatorTest extends BaseTest<User> {

	UserAction action = new UserAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<User> getAction() {
		return action;
	}

	@Test(dependsOnMethods = { "testRegisterAction" })
	public void validateAuthenticationBadUser() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				Identity.instance().getCredentials().setUsername("admin");
				Identity.instance().getCredentials().setPassword("admin");
				Identity.instance().addRole("lenderContact");
				Identity.instance().addRole("lawyer");
				Identity.instance().addRole("pm");

				assert invokeMethod("#{authenticator.authenticate}").equals(
						true);

			}

		}.run();
	}

	@Test
	public void testRegisterAction() throws Exception {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();

		Query query = em
				.createQuery("Select u From User u where u.userName = ?1 ");
		query.setParameter(1, "admin");
		if (!query.getResultList().isEmpty())
			return;

		new ComponentTest() {

			protected void testComponents() throws Exception {
				Identity.instance().getCredentials().setUsername("lender");
				Identity.instance().getCredentials().setPassword("lender");

				createUserAndRole("admin", "admin", "admin");
				createUserAndRole("jim", "jim", "support");
				createUserAndRole("roger", "roger", "teacher");
				createUserAndRole("erica", "erica", "clerk");
				
				createSubjects();
				createGrades();
				createExams();
			}

		}.run();

		// em.getTransaction().commit();
		em.close();
	}

	int userCounter = 0;
	
	private User createUserAndRole(String username, String password, String role) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();

		User user = new User();
		user.setUserName(username);
		user.setPassword(password);

		Role adminRole = new Role();
		adminRole.setName(role);
		user.getRoles().add(adminRole);
		user.setEmail(username + "@gmail.com");
		user.setEnabled(true);
		
		Employee employee = new Employee();
		employee.setFirstName(username);
		employee.setLastName(userCounter++ % 2 == 0 ? "Somerson":"Kirvokian");
		employee.setUser(user);

		// setValue("#{userAction.instance}", admin);
		// invokeMethod("#{userAction.save}");
		txPersist(employee);
		
		return user;
	}

	private void createGrades() {
		String grades[] = { "Play", "LKG", "UKG", "I ", "II", "III", "IV", "V",
				"VI", "VII", "VIII", "IX", "X", "XI", "XII" };

		int i = 0;

		for (String gradeStr : grades) {
			Grade grade = new Grade();
			grade.setName(gradeStr);
			grade.setOrdinal(++i);
			for (int j = 0; j < 5; j++) {
				grade.addStudents(createStudent());
			}
			List<Subject> subjects = em.createQuery("Select s from Subject s ").getResultList();
			for (Subject subject : subjects) {
				GradeSubject gradeSubject = new GradeSubject();
				gradeSubject.setGrade(grade);
				gradeSubject.setSubject(subject);
				grade.addGradeSubjects(gradeSubject);
			}
			
			txPersist(grade);
			
		}
	}

	private Student createStudent() {
		String firstName = names[new Random().nextInt(names.length )];
		String lastName = lastNames[new Random().nextInt(lastNames.length )];
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		if(firstName.endsWith("a"))
			student.setGender(Gender.F);
		else
			student.setGender(Gender.M);
		return student;
	}
	
	private void createSubjects(){
		String[] subjects = {"English", "Hindi","Math","Science","Social Science"};
		for (String sub : subjects) {
			Subject subject = new Subject();
			subject.setName(sub);
			txPersist(subject);
		}
	}
	
	private void createExams(){
		String[] subjects = {"I Term", "II Term","III Term","Final"};
		int i = 0;
		
		for (String examStr : subjects) {
			
			Exam exam = new Exam();
			exam.setName(examStr);
			if(++i < 4 )
				exam.setWeight(0.2);
			else
				exam.setWeight(0.4);
			txPersist(exam);
		}
	}
	
	private void createFees(){
		String[] subjects = {"I Term", "II Term","III Term","Final"};
		int i = 0;
		
		for (String examStr : subjects) {
			
			Exam exam = new Exam();
			exam.setName(examStr);
			if(++i < 4 )
				exam.setWeight(0.2);
			else
				exam.setWeight(0.4);
			txPersist(exam);
		}
	}

	private void txPersist(BusinessEntity be) {
		if(!em.getTransaction().isActive())
			em.getTransaction().begin();
		em.persist(be);
		em.getTransaction().commit();
	}

	String[] names = { "Jack", "Dan", "Erica", "Mike", "Senereon", "Mika",
			"Sylvia", "Alisha", "Lidia", "Vincent", "Nick", "Barbara" ,"George","Mike", "Justin" };
	String[] lastNames = { "Syborg", "Morrison", "Zhang", "Zhu", "Kirvokis",
			"Bunners", "Suppan", "Bohener" , "Canter", "OBrien"};
	

}