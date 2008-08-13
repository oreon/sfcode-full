package tpack;

import bizobjects.Exam;
import bizobjects.ExamInstance;
import bizobjects.ExamStrategyInfo;

public interface IZenith {

	public abstract void processCase(String arg);

	public abstract String doPreprocess(String arg);

	public ExamInstance createExam(Exam exam, ExamStrategyInfo examStrategy);

}