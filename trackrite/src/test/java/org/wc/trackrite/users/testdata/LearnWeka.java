package org.wc.trackrite.users.testdata;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.RBFNetwork;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;

enum Degree {
	MASTERS, BATCHELORS, DIPLOMA
}

public class LearnWeka {
	
	static Instances trainingData;

	public static void addInstance(Instances trainingData, int exp,
			Degree degree, int avgHours) {
		
		Instance instance = createInstance( exp, degree, avgHours);
		instance.setDataset(trainingData);
		trainingData.add(instance);
	}

	private static Instance createInstance( int exp,
			Degree degree, int avgHours) {
		Instance instance = new Instance(3);
		instance.setDataset(trainingData);
		instance.setValue(0, exp);
		instance.setValue(1, degree.name());
		instance.setValue(2, avgHours);
		return instance;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Attribute exp = new Attribute("exp");
		FastVector degree = new FastVector(3);
		degree.addElement(Degree.MASTERS.name());
		degree.addElement(Degree.BATCHELORS.name());
		degree.addElement(Degree.DIPLOMA.name());
		Attribute degreeAttribs = new Attribute("degree", degree);
		Attribute avgHours = new Attribute("avgHours");

		FastVector allAttribs = new FastVector();
		allAttribs.addElement(exp);
		allAttribs.addElement(degreeAttribs);
		allAttribs.addElement(avgHours);

		trainingData = new Instances("wt", allAttribs, 6);
		trainingData.setClassIndex(2);
		addInstance(trainingData, 5, Degree.BATCHELORS, 6);
		addInstance(trainingData, 7, Degree.MASTERS, 5);
		addInstance(trainingData, 5, Degree.MASTERS, 4);
		addInstance(trainingData, 2, Degree.DIPLOMA, 9);
		addInstance(trainingData, 1, Degree.DIPLOMA, 8);
		addInstance(trainingData, 7, Degree.DIPLOMA, 6);

		Classifier classifier = new RBFNetwork();
		try {
			classifier.buildClassifier(trainingData);
			Evaluation eval = new Evaluation(trainingData);
			eval.evaluateModel(classifier, trainingData);
			
			System.out.println(eval.toSummaryString());
			
			double prediction = classifier.classifyInstance(createInstance(8, Degree.DIPLOMA, 0));
			double mastersPrediction = classifier.classifyInstance(createInstance(4, Degree.BATCHELORS, 0));
			System.out.println(prediction + " " + mastersPrediction);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
