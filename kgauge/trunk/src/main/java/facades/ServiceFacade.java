package facades;

import com.oreon.kgauge.service.CandidateService;

import com.oreon.kgauge.service.QuestionService;

import com.oreon.kgauge.service.AnswerChoiceService;

import com.oreon.kgauge.service.ExamService;

import com.oreon.kgauge.service.ExamInstanceService;

import com.oreon.kgauge.service.ExamCreatorService;

import com.oreon.kgauge.service.CategoryService;

import com.oreon.kgauge.service.UserService;

import com.oreon.kgauge.service.GrantedRoleService;

import com.oreon.kgauge.service.AnsweredQuestionService;

import com.oreon.kgauge.service.SectionService;

public class ServiceFacade {

	private CandidateService candidateService;

	public CandidateService getCandidateService() {
		return candidateService;
	}

	public void setCandidateService(CandidateService candidateService) {
		this.candidateService = candidateService;
	}

	private QuestionService questionService;

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	private AnswerChoiceService answerChoiceService;

	public AnswerChoiceService getAnswerChoiceService() {
		return answerChoiceService;
	}

	public void setAnswerChoiceService(AnswerChoiceService answerChoiceService) {
		this.answerChoiceService = answerChoiceService;
	}

	private ExamService examService;

	public ExamService getExamService() {
		return examService;
	}

	public void setExamService(ExamService examService) {
		this.examService = examService;
	}

	private ExamInstanceService examInstanceService;

	public ExamInstanceService getExamInstanceService() {
		return examInstanceService;
	}

	public void setExamInstanceService(ExamInstanceService examInstanceService) {
		this.examInstanceService = examInstanceService;
	}

	private ExamCreatorService examCreatorService;

	public ExamCreatorService getExamCreatorService() {
		return examCreatorService;
	}

	public void setExamCreatorService(ExamCreatorService examCreatorService) {
		this.examCreatorService = examCreatorService;
	}

	private CategoryService categoryService;

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private GrantedRoleService grantedRoleService;

	public GrantedRoleService getGrantedRoleService() {
		return grantedRoleService;
	}

	public void setGrantedRoleService(GrantedRoleService grantedRoleService) {
		this.grantedRoleService = grantedRoleService;
	}

	private AnsweredQuestionService answeredQuestionService;

	public AnsweredQuestionService getAnsweredQuestionService() {
		return answeredQuestionService;
	}

	public void setAnsweredQuestionService(
			AnsweredQuestionService answeredQuestionService) {
		this.answeredQuestionService = answeredQuestionService;
	}

	private SectionService sectionService;

	public SectionService getSectionService() {
		return sectionService;
	}

	public void setSectionService(SectionService sectionService) {
		this.sectionService = sectionService;
	}

}
