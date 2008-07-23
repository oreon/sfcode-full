package facades;

import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import com.cc.civlit.service.FirmService;
import com.cc.civlit.service.FirmAdministratorService;
import com.cc.civlit.service.LitigationCaseService;
import com.cc.civlit.service.CaseAdministratorService;
import com.cc.civlit.domain.service.LevelOfCourtService;
import com.cc.civlit.domain.service.JurisdictionService;
import com.cc.civlit.domain.service.FilingOfficeService;
import com.cc.civlit.domain.service.DivsionService;

public class ServiceFacade {
	private static ServiceFacade instance;

	public static ServiceFacade getInstance() {
		if (instance == null)
			instance = (ServiceFacade) BeanHelper.getBean("serviceFacade");
		return instance;
	}

	// Construction is disabled
	private ServiceFacade() {
	}

	private FirmService firmService;

	public FirmService getFirmService() {
		return firmService;
	}

	public void setFirmService(FirmService firmService) {
		this.firmService = firmService;
	}

	private FirmAdministratorService firmAdministratorService;

	public FirmAdministratorService getFirmAdministratorService() {
		return firmAdministratorService;
	}

	public void setFirmAdministratorService(
			FirmAdministratorService firmAdministratorService) {
		this.firmAdministratorService = firmAdministratorService;
	}

	private LitigationCaseService litigationCaseService;

	public LitigationCaseService getLitigationCaseService() {
		return litigationCaseService;
	}

	public void setLitigationCaseService(
			LitigationCaseService litigationCaseService) {
		this.litigationCaseService = litigationCaseService;
	}

	private CaseAdministratorService caseAdministratorService;

	public CaseAdministratorService getCaseAdministratorService() {
		return caseAdministratorService;
	}

	public void setCaseAdministratorService(
			CaseAdministratorService caseAdministratorService) {
		this.caseAdministratorService = caseAdministratorService;
	}

	private LevelOfCourtService levelOfCourtService;

	public LevelOfCourtService getLevelOfCourtService() {
		return levelOfCourtService;
	}

	public void setLevelOfCourtService(LevelOfCourtService levelOfCourtService) {
		this.levelOfCourtService = levelOfCourtService;
	}

	private JurisdictionService jurisdictionService;

	public JurisdictionService getJurisdictionService() {
		return jurisdictionService;
	}

	public void setJurisdictionService(JurisdictionService jurisdictionService) {
		this.jurisdictionService = jurisdictionService;
	}

	private FilingOfficeService filingOfficeService;

	public FilingOfficeService getFilingOfficeService() {
		return filingOfficeService;
	}

	public void setFilingOfficeService(FilingOfficeService filingOfficeService) {
		this.filingOfficeService = filingOfficeService;
	}

	private DivsionService divsionService;

	public DivsionService getDivsionService() {
		return divsionService;
	}

	public void setDivsionService(DivsionService divsionService) {
		this.divsionService = divsionService;
	}

}
