package initialsetup;

import org.witchcraft.model.support.springbeanhelpers.BeanHelper;
import org.witchcraft.model.support.errorhandling.BusinessException;
import org.witchcraft.model.support.testing.TestDataFactory;

import com.oreon.kgauge.domain.CandidateTestDataFactory;

import com.oreon.kgauge.domain.QuestionTestDataFactory;

import com.oreon.kgauge.domain.AnswerChoiceTestDataFactory;

import com.oreon.kgauge.domain.ExamTestDataFactory;

import com.oreon.kgauge.domain.ExamInstanceTestDataFactory;

import com.oreon.kgauge.domain.ExamCreatorTestDataFactory;

import com.oreon.kgauge.domain.CategoryTestDataFactory;

import com.oreon.kgauge.domain.UserTestDataFactory;

import com.oreon.kgauge.domain.GrantedRoleTestDataFactory;

import com.oreon.kgauge.domain.AnsweredQuestionTestDataFactory;

import com.oreon.kgauge.domain.SectionTestDataFactory;

/** 
 * This class populates the database with some initial data
 *
 */
public class InitialDataSetup {

	public static void main(String args[]) {

		TestDataFactory candidateTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("candidateTestDataFactory");

		candidateTestDataFactory.persistAll();

		TestDataFactory questionTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("questionTestDataFactory");

		questionTestDataFactory.persistAll();

		TestDataFactory answerChoiceTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("answerChoiceTestDataFactory");

		answerChoiceTestDataFactory.persistAll();

		TestDataFactory examTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("examTestDataFactory");

		examTestDataFactory.persistAll();

		TestDataFactory examInstanceTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("examInstanceTestDataFactory");

		examInstanceTestDataFactory.persistAll();

		TestDataFactory examCreatorTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("examCreatorTestDataFactory");

		examCreatorTestDataFactory.persistAll();

		TestDataFactory categoryTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("categoryTestDataFactory");

		categoryTestDataFactory.persistAll();

		TestDataFactory grantedRoleTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("grantedRoleTestDataFactory");

		grantedRoleTestDataFactory.persistAll();

		TestDataFactory answeredQuestionTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("answeredQuestionTestDataFactory");

		answeredQuestionTestDataFactory.persistAll();

		TestDataFactory sectionTestDataFactory = (TestDataFactory) BeanHelper
				.getBean("sectionTestDataFactory");

		sectionTestDataFactory.persistAll();

	}
}
