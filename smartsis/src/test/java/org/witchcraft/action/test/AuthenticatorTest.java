package org.witchcraft.action.test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.jboss.seam.security.Identity;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.witchcraft.base.entity.BusinessEntity;
import org.witchcraft.seam.action.BaseAction;

import com.oreon.smartsis.Gender;
import com.oreon.smartsis.QuestionCorrectScoreBean;
import com.oreon.smartsis.domain.Employee;
import com.oreon.smartsis.domain.Exam;
import com.oreon.smartsis.domain.FeeMonth;
import com.oreon.smartsis.domain.Grade;
import com.oreon.smartsis.domain.GradeSubject;
import com.oreon.smartsis.domain.Student;
import com.oreon.smartsis.domain.Subject;
import com.oreon.smartsis.exam.Choice;
import com.oreon.smartsis.exam.ChoiceIndex;
import com.oreon.smartsis.exam.ElectronicExam;
import com.oreon.smartsis.exam.ElectronicExamEvent;
import com.oreon.smartsis.exam.ElectronicExamInstance;
import com.oreon.smartsis.exam.Question;
import com.oreon.smartsis.exam.QuestionInstance;
import com.oreon.smartsis.fees.FeeItem;
import com.oreon.smartsis.fees.GradeFeeItem;
import com.oreon.smartsis.fees.MonthlyFee;
import com.oreon.smartsis.users.AppRole;
import com.oreon.smartsis.users.AppUser;
import com.oreon.smartsis.web.action.domain.ExamInstanceAction;
import com.oreon.smartsis.web.action.exam.ElectronicExamInstanceAction;
import com.oreon.smartsis.web.action.users.AppUserAction;

public class AuthenticatorTest extends BaseTest<AppUser> {

	AppUserAction action = new AppUserAction();

	@BeforeClass
	public void init() {
		super.init();
	}

	@Override
	public BaseAction<AppUser> getAction() {
		return action;
	}

	@Test(dependsOnMethods = { "testRegisterAction" })
	public void validateAuthenticationBadUser() throws Exception {
		new ComponentTest() {

			protected void testComponents() throws Exception {
				Identity.instance().getCredentials().setUsername("admin");
				Identity.instance().getCredentials().setPassword("admin");
				Identity.instance().addRole("admin");

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
				.createQuery("Select u From AppUser u where u.userName = ?1 ");
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
				
				createFees();
				createExams();
				createSubjects();
				
				createGrades();
				
				createElectronicExams();
				createElectronicExamInstance();
			}

		}.run();

		// em.getTransaction().commit();
		em.close();
	}

	int userCounter = 0;
	
	private AppUser createUserAndRole(String username, String password, String role) {
		EntityManager em = getEntityManagerFactory().createEntityManager();
		em.getTransaction().begin();

		AppUser user = new AppUser();
		user.setUserName(username);
		user.setPassword(password);

		AppRole adminRole = new AppRole();
		adminRole.setName(role);
		user.getAppRoles().add(adminRole);
		user.setEmail(username + "@gmail.com");
		user.setEnabled(true);
		
		Employee employee = new Employee();
		employee.setFirstName(username);
		employee.setLastName(userCounter++ % 2 == 0 ? "Somerson":"Kirvokian");
		employee.setAppUser(user);

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
				grade.addStudent(createStudent(i));
			}
			List<Subject> subjects = em.createQuery("Select s from Subject s ").getResultList();
			for (Subject subject : subjects) {
				GradeSubject gradeSubject = new GradeSubject();
				gradeSubject.setGrade(grade);
				gradeSubject.setSubject(subject);
				grade.addGradeSubject(gradeSubject);
			}
			
			createGradeFees(grade, i);
			txPersist(grade);
			
		}
	}

	private Student createStudent(int i) {
		String firstName = names[new Random().nextInt(names.length )];
		String lastName = lastNames[new Random().nextInt(lastNames.length )];
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		if(firstName.endsWith("a"))
			student.setGender(Gender.F);
		else
			student.setGender(Gender.M);
		Date date = new Date();
		date.setYear(107 - i);
		student.setDateOfBirth(date);
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
			
			if(++i < 4 ){
				exam.setMaxMarks(40);
				exam.setPassMarks(16);
				exam.setWeight(0.2);
				exam.setOrdinal(i);
			}
			else {
				exam.setMaxMarks(100);
				exam.setPassMarks(40);
				exam.setWeight(0.4);
				exam.setOrdinal(i);
			}
			txPersist(exam);
		}
	}
	
	private void createElectronicExams(){
		String[] subjects = {"I Term", "II Term","III Term","Final"};
		int i = 0;
		
		ElectronicExam exam = new ElectronicExam();
		exam.setGradeSubject( em.find(GradeSubject.class, 1L));
		exam.setName("Arithmetic-Basic");
		
		exam.addQuestion(createQuestion("What is 5+3?", new String[]{"8","9","7","6"}, ChoiceIndex.A));
		
		exam.addQuestion(createQuestion("What is 9 * 3?", new String[]{"8","27","70","17"}, ChoiceIndex.B));
	
		exam.addQuestion(createQuestion("What is 15+3?", new String[]{"8","9","7","18"}, ChoiceIndex.D));
		
		exam.addQuestion(createQuestion("What is 5 * 3?", new String[]{"8","9","15","6"}, ChoiceIndex.C));
		
		exam.addQuestion(createQuestion("What is 3+3?", new String[]{"6","9","7","16"}, ChoiceIndex.A));
		
		txPersist(exam);
	}
	
	private void createElectronicExamInstance(){
		
		ElectronicExamInstance examInstance = new ElectronicExamInstance();
		ElectronicExamEvent event = new ElectronicExamEvent();
		String qry = "select e from ElectronicExam e where e.name='Arithmetic-Basic'" ;
		ElectronicExam exam = (ElectronicExam) em.createQuery(qry).getSingleResult();
		event.setElectronicExam(exam);
		
		ExamInstanceAction action = new ExamInstanceAction();
		examInstance.setStudent(em.find(Student.class, 1L));
		int i = 0;
		
		Set<Question> questions = exam.getQuestions();
		for (Question question : questions) {
			QuestionInstance questionInstance = new QuestionInstance();
			questionInstance.setQuestion(question);
			questionInstance.setSelectedChoice(ChoiceIndex.values()[i++%4]);
			examInstance.addQuestionInstance(questionInstance);
		}
		
		examInstance.setElectronicExamEvent(event);
		txPersist(event);
		txPersist(examInstance);
		
		ElectronicExamInstanceAction examInstanceAction = new ElectronicExamInstanceAction();
		System.out.println("Score was " + examInstanceAction.calculateScore(examInstance));
		//assert(examInstanceAction.calculateScore(examInstance) == 3);
		
	}
	
	private Question createQuestion(String text, String[] choices, ChoiceIndex correctChoice){
		Question question = new Question();
		question.setQuestion(text);
		for (String chstr : choices) {
			Choice choice = new Choice();
			choice.setChoice(chstr);
			question.addChoice(choice);
		}
		question.setCorrectChoice(correctChoice);
		return question;
	}
	
	private void createFees(){
		String[] names = { "Tution", "Admission", "Lab", "Cocurricular"};
		int i = 0 ;
		for (String name : names) {
			i++;
			FeeItem fee = new FeeItem();
			fee.setName(name);
			fee.setDefaultAmount(300.0 + i * 100.0);
			txPersist(fee);
		}
		
	}
	
	private void createGradeFees(Grade grade, int gradeOrdinal){
		FeeMonth[] feeMonths = FeeMonth.values();
		for (FeeMonth feeMonth : feeMonths) {
			MonthlyFee fee = new MonthlyFee();
			fee.setMonth(feeMonth);
			fee.setGrade(grade);
			
			if(feeMonth.ordinal() == 6){
				GradeFeeItem admission = new GradeFeeItem();
				admission.setFeeItem(em.find(FeeItem.class, 2L));
				admission.setAmount(admission.getFeeItem().getDefaultAmount() );
				fee.addGradeFeeItem(admission);
			}
			GradeFeeItem item = new GradeFeeItem();
			item.setFeeItem(em.find(FeeItem.class, 1L));
			item.setAmount(item.getFeeItem().getDefaultAmount() );
			fee.addGradeFeeItem(item);
			
			if(gradeOrdinal > 10 ){
				GradeFeeItem lab = new GradeFeeItem();
				lab.setFeeItem(em.find(FeeItem.class, 3L));
				lab.setAmount(lab.getFeeItem().getDefaultAmount() );
				fee.addGradeFeeItem(lab);
			}
			
			txPersist(fee);
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