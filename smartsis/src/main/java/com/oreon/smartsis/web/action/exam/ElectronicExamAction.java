package com.oreon.smartsis.web.action.exam;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.jboss.seam.annotations.Name;

import com.oreon.smartsis.exam.Choice;
import com.oreon.smartsis.exam.ChoiceIndex;
import com.oreon.smartsis.exam.Question;

//@Scope(ScopeType.CONVERSATION)
@Name("electronicExamAction")
public class ElectronicExamAction extends ElectronicExamActionBase implements
		java.io.Serializable {

	@Override
	public void prePopulateListQuestions() {

		if (getInstance().getId() != null || !listQuestions.isEmpty())
			return;

		for (int i = 0; i < 5; i++) {
			
			Question question = new Question();
			for (int j = 0; j < 4; j++) {
				question.addChoices(new Choice());
			}
			question.setElectronicExam(getInstance());
			listQuestions.add(question);
		}
	}

	@Override
	public String save() {
		// TODO Auto-generated method stub
		return super.save();
	}

	

	public static void main(String[] args) {

		DescriptiveStatistics stats = new DescriptiveStatistics();
		String qry = "select e.score from ElectronicExamInstance e where e.electronicExamEvent = :event ";
		for (int i = 0; i < 10; i++)
			stats.addValue(i);

		// Frequency frequency;
		// frequency.get

		System.out.println(stats.getMax());
		System.out.println(stats.getMin());
		System.out.println(stats.getKurtosis());
		System.out.println(stats.getGeometricMean());
		System.out.println(stats.getMean());
		System.out.println(stats.getPercentile(50)); // median
		System.out.println(stats.getVariance());
		System.out.println(stats.getStandardDeviation());
	}
	
	
	public String getEnumLiteral(int ordinal){
		//ChoiceIndex.
		return ChoiceIndex.values()[ordinal].toString();
	}

}
