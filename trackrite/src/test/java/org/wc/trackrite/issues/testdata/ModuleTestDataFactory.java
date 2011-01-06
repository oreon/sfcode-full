package org.wc.trackrite.issues.testdata;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.ArrayList;
import org.jboss.seam.Component;
import org.witchcraft.action.test.AbstractTestDataFactory;

//import org.witchcraft.model.support.errorhandling.BusinessException;
//import org.witchcraft.model.randomgen.RandomValueGeneratorFactory;

import org.apache.log4j.Logger;

public class ModuleTestDataFactory
		extends
			AbstractTestDataFactory<org.wc.trackrite.issues.Module> {

	private List<org.wc.trackrite.issues.Module> modules = new ArrayList<org.wc.trackrite.issues.Module>();

	private static final Logger logger = Logger
			.getLogger(ModuleTestDataFactory.class);

	private static int RECORDS_TO_CREATE = 30;

	private static SimpleDateFormat dateFormat = new SimpleDateFormat(
			"yyyy.MM.dd HH:mm:ss z");

	com.nas.recovery.web.action.issues.ModuleAction moduleAction;

	public void register(org.wc.trackrite.issues.Module module) {
		modules.add(module);
	}

	public org.wc.trackrite.issues.Module createModuleOne() {
		org.wc.trackrite.issues.Module module = new org.wc.trackrite.issues.Module();

		try {

			module.setName("zeta");

			register(module);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return module;
	}

	public org.wc.trackrite.issues.Module createModuleTwo() {
		org.wc.trackrite.issues.Module module = new org.wc.trackrite.issues.Module();

		try {

			module.setName("gamma");

			register(module);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return module;
	}

	public org.wc.trackrite.issues.Module createModuleThree() {
		org.wc.trackrite.issues.Module module = new org.wc.trackrite.issues.Module();

		try {

			module.setName("gamma");

			register(module);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return module;
	}

	public org.wc.trackrite.issues.Module createModuleFour() {
		org.wc.trackrite.issues.Module module = new org.wc.trackrite.issues.Module();

		try {

			module.setName("Wilson");

			register(module);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return module;
	}

	public org.wc.trackrite.issues.Module createModuleFive() {
		org.wc.trackrite.issues.Module module = new org.wc.trackrite.issues.Module();

		try {

			module.setName("Eric");

			register(module);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return module;
	}

	public List<org.wc.trackrite.issues.Module> createAll() {
		createModuleOne();
		createModuleTwo();
		createModuleThree();
		createModuleFour();
		createModuleFive();

		return modules;
	}

	@Override
	public List<org.wc.trackrite.issues.Module> getListOfRecords() {
		return modules;
	}

	@Override
	public String getQuery() {
		return "Select e from org.wc.trackrite.issues.Module e ";
	}

	public void persistAll() {
		init();
		createAll();

		for (org.wc.trackrite.issues.Module module : modules) {
			persist(module);
		}
	}

	/** Execute this method to manually generate objects
	 * @param args
	 */
	public static void main(String args[]) {
		new ModuleTestDataFactory().persistAll();
	}

}
