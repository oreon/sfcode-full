package facades;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import com.oreon.cerebrum.service.CategoryService;
import com.oreon.cerebrum.service.DrugService;
import com.oreon.cerebrum.service.DrugCodeService;
import com.oreon.cerebrum.service.PrescriptionService;
import com.oreon.cerebrum.service.ItemService;
import com.oreon.cerebrum.service.PatientService;

public class ServiceFacade {
	private static ServiceFacade instance;

	private static ApplicationContext applicationContext;

	static {
		applicationContext = new ClassPathXmlApplicationContext(
				new String[]{/*"classpath:/persistenceContext.xml",*/"classpath:/applicationContext.xml"});
	}

	public static ServiceFacade getInstance() {
		if (instance == null)
			instance = (ServiceFacade) applicationContext
					.getBean("serviceFacade");
		return instance;
	}

	// Construction is disabled
	private ServiceFacade() {
	}

	private CategoryService categoryService;

	public CategoryService getCategoryService() {
		return categoryService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	private DrugService drugService;

	public DrugService getDrugService() {
		return drugService;
	}

	public void setDrugService(DrugService drugService) {
		this.drugService = drugService;
	}

	private DrugCodeService drugCodeService;

	public DrugCodeService getDrugCodeService() {
		return drugCodeService;
	}

	public void setDrugCodeService(DrugCodeService drugCodeService) {
		this.drugCodeService = drugCodeService;
	}

	private PrescriptionService prescriptionService;

	public PrescriptionService getPrescriptionService() {
		return prescriptionService;
	}

	public void setPrescriptionService(PrescriptionService prescriptionService) {
		this.prescriptionService = prescriptionService;
	}

	private ItemService itemService;

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	private PatientService patientService;

	public PatientService getPatientService() {
		return patientService;
	}

	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

}
