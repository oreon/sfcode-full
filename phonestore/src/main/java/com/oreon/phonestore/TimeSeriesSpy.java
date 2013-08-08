package com.oreon.phonestore;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import weka.classifiers.evaluation.NumericPrediction;
import weka.classifiers.functions.GaussianProcesses;
import weka.classifiers.timeseries.WekaForecaster;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

/**
 * Example of using the time series forecasting API. To compile and
 * run the CLASSPATH will need to contain:
 *
 * weka.jar (from your weka distribution)
 * pdm-timeseriesforecasting-ce-TRUNK-SNAPSHOT.jar (from the time series package)
 * jcommon-1.0.14.jar (from the time series package lib directory)
 * jfreechart-1.0.13.jar (from the time series package lib directory)
 */
public class TimeSeriesSpy {
	
	public static void saveToArff(String name) throws IOException{
		 CSVLoader loader = new CSVLoader();
		    loader.setSource(new File(name));
		    Instances data = loader.getDataSet();
		 
		    // save ARFF
		    ArffSaver saver = new ArffSaver();
		    saver.setInstances(data);
		    saver.setFile(new File(name + ".arff"));
		    //saver.setDestination(new File(name + ".arff"));
		    saver.writeBatch();
	}

  public static void main(String[] args) {
    try {
      // path to the Australian wine data included with the time series forecasting
      // package
    	
     saveToArff(weka.core.WekaPackageManager.PACKAGES_DIR.toString()
        + File.separator + "spy2.csv");
    	
      String pathToWineData = weka.core.WekaPackageManager.PACKAGES_DIR.toString()
        + File.separator + "spy2.csv.arff";

      // load the wine data
      Instances wine = new Instances(new BufferedReader(new FileReader(pathToWineData)));

      // new forecaster
      WekaForecaster forecaster = new WekaForecaster();

      // set the targets we want to forecast. This method calls
      // setFieldsToLag() on the lag maker object for us
      forecaster.setFieldsToForecast("Adj Close");

      // default underlying classifier is SMOreg (SVM) - we'll use
      // gaussian processes for regression instead
      forecaster.setBaseForecaster(new GaussianProcesses());

     // forecaster.getTSLagMaker().setTimeStampField("Date"); // date time stamp
      forecaster.getTSLagMaker().setMinLag(1);
      forecaster.getTSLagMaker().setMaxLag(64); // monthly data

      // add a month of the year indicator field
      //forecaster.getTSLagMaker().setAddMonthOfYear(true);

      // add a quarter of the year indicator field
      //forecaster.getTSLagMaker().setAddQuarterOfYear(true);

      // build the model
      forecaster.buildForecaster(wine, System.out);

      // prime the forecaster with enough recent historical data
      // to cover up to the maximum lag. In our case, we could just supply
      // the 12 most recent historical instances, as this covers our maximum
      // lag period
      forecaster.primeForecaster(wine);

      // forecast for 12 units (months) beyond the end of the
      // training data
      List<List<NumericPrediction>> forecast = forecaster.forecast(4, System.out);

      // output the predictions. Outer list is over the steps; inner list is over
      // the targets
      for (int i = 0; i < 4; i++) {
        List<NumericPrediction> predsAtStep = forecast.get(i); 
        for (int j = 0; j < 1; j++) {
          NumericPrediction predForTarget = predsAtStep.get(j);
          System.out.print("" + predForTarget.predicted() + " ");
        }
        System.out.println();
      }

      // we can continue to use the trained forecaster for further forecasting
      // by priming with the most recent historical data (as it becomes available).
      // At some stage it becomes prudent to re-build the model using current
      // historical data.

    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}