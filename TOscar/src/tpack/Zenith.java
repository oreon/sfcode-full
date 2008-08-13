package tpack;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bizobjects.Exam;
import bizobjects.ExamInstance;
import bizobjects.ExamStrategyInfo;

public class Zenith implements IZenith {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"classpath:tpack/ApplicationContext.xml");

		IZenith zenith = (IZenith) context.getBean("zenith");
		zenith.processCase("case for insolvency ");
		zenith.createExam(new Exam("java"), null);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tpack.IZenith#processCase(java.lang.String)
	 */
	public void processCase(String arg) {
		arg = doPreprocess(arg);
		System.out.println("--" + arg + "--");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tpack.IZenith#doPreprocess(java.lang.String)
	 */
	public String doPreprocess(String arg) {
		try {
			System.out.println(" trimming -->" + arg + "--");
			return arg.trim();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return "<<>>";
		}
	}

	@PreCondition(value="exam != null && ! exam.getStatus().equals(\"INC\")" )
	public ExamInstance createExam(Exam exam, ExamStrategyInfo examStrategy) {
		return new ExamInstance();
	}

}
