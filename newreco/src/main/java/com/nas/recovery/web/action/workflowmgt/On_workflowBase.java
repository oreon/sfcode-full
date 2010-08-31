package com.nas.recovery.web.action.workflowmgt;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.bpm.CreateProcess;
import org.jboss.seam.annotations.bpm.EndTask;
import org.jboss.seam.annotations.bpm.StartTask;
import org.jbpm.JbpmContext;
import org.witchcraft.jbpm.BaseJbpmProcessAction;

public class On_workflowBase extends BaseJbpmProcessAction
		implements
			java.io.Serializable {

	@StartTask
	public void startWritSeizureAndSaleTask() {

	}

	@EndTask(transition = "proceedTowritOfPossesion")
	public void proceedTowritOfPossesionWritSeizureAndSaleTask() {

	}

	@StartTask
	public void startInspectionReportTask() {

	}

	@EndTask(transition = "statusIsSecured")
	public void statusIsSecuredInspectionReportTask() {

	}

	@StartTask
	public void startNewFileTask() {

	}

	@EndTask(transition = "proceedToassignFileFork")
	public void proceedToassignFileForkNewFileTask() {

	}

	@StartTask
	public void startSaleProceedsDepositedTask() {

	}

	@EndTask(transition = "proceedToinsuredLoan")
	public void proceedToinsuredLoanSaleProceedsDepositedTask() {

	}

	@StartTask
	public void startReassignAppraisalTask() {

	}

	@EndTask(transition = "proceedTofullAppraisalOrder")
	public void proceedTofullAppraisalOrderReassignAppraisalTask() {

	}
	@EndTask(transition = "proceedTodesktopAppraisalOrder")
	public void proceedTodesktopAppraisalOrderReassignAppraisalTask() {

	}

	@StartTask
	public void startSecurementReportTask() {

	}

	@EndTask(transition = "proceedToredirectUtilitiesIn")
	public void proceedToredirectUtilitiesInSecurementReportTask() {

	}

	@StartTask
	public void startCMA84Task() {

	}

	@EndTask(transition = "proceedTomortgageInsuranceType")
	public void proceedTomortgageInsuranceTypeCMA84Task() {

	}

	@StartTask
	public void startWritOfPossesionTask() {

	}

	@EndTask(transition = "evictionDate")
	public void evictionDateWritOfPossesionTask() {

	}

	@StartTask
	public void startAcceptTask() {

	}

	@EndTask(transition = "proceedToissueDemandLetter")
	public void proceedToissueDemandLetterAcceptTask() {

	}
	@EndTask(transition = "proceedTotitleSearch")
	public void proceedTotitleSearchAcceptTask() {

	}
	@EndTask(transition = "yes")
	public void yesAcceptTask() {

	}
	@EndTask(transition = "no")
	public void noAcceptTask() {

	}

	@StartTask
	public void startVacantSecurePropertyTask() {

	}

	@EndTask(transition = "proceedToinspectionReport")
	public void proceedToinspectionReportVacantSecurePropertyTask() {

	}

	@StartTask
	public void startIssueDemandLetterTask() {

	}

	@EndTask(transition = "proceedTodemandExpires")
	public void proceedTodemandExpiresIssueDemandLetterTask() {

	}

	@StartTask
	public void startCMA28Task() {

	}

	@EndTask(transition = "proceedToenterOfferDetails")
	public void proceedToenterOfferDetailsCMA28Task() {

	}

	@StartTask
	public void startCMA56Task() {

	}

	@EndTask(transition = "proceedToenterOfferDetails")
	public void proceedToenterOfferDetailsCMA56Task() {

	}

	@StartTask
	public void startIssueRentalAttornmentTask() {

	}

	@EndTask(transition = "proceedToserveRentalAttornment")
	public void proceedToserveRentalAttornmentIssueRentalAttornmentTask() {

	}

	@StartTask
	public void startNOSIssuedTask() {

	}

	@EndTask(transition = "proceedTonOSExpiry")
	public void proceedTonOSExpiryNOSIssuedTask() {

	}

	@StartTask
	public void startAttendEvictionTask() {

	}

	@EndTask(transition = "proceedTovacantSecureProperty")
	public void proceedTovacantSecurePropertyAttendEvictionTask() {

	}

	@StartTask
	public void startMotionForJudgementTask() {

	}

	@EndTask(transition = "proceedTowritSeizureAndSale")
	public void proceedTowritSeizureAndSaleMotionForJudgementTask() {

	}

	@StartTask
	public void startServeSOCTask() {

	}

	@EndTask(transition = "proceedTonOSIssued")
	public void proceedTonOSIssuedServeSOCTask() {

	}

	@StartTask
	public void startReassignTask() {

	}

	@StartTask
	public void startUploadClosingDocumentsTask() {

	}

	@EndTask(transition = "proceedToclosing")
	public void proceedToclosingUploadClosingDocumentsTask() {

	}

	@StartTask
	public void startFullAppraisalOrderTask() {

	}

	@EndTask(transition = "proceedToacceptAppraisalOrder")
	public void proceedToacceptAppraisalOrderFullAppraisalOrderTask() {

	}

	@StartTask
	public void startConfirmSaleClosedTask() {

	}

	@EndTask(transition = "proceedToinsuredLoan")
	public void proceedToinsuredLoanConfirmSaleClosedTask() {

	}

	@StartTask
	public void startListingSummaryTask() {

	}

	@EndTask(transition = "proceedTolistProperty")
	public void proceedTolistPropertyListingSummaryTask() {

	}

	@StartTask
	public void startServeRentalAttornmentTask() {

	}

	@EndTask(transition = "proceedTocollectRents")
	public void proceedTocollectRentsServeRentalAttornmentTask() {

	}

	@StartTask
	public void startDriveByTask() {

	}

	@EndTask(transition = "proceedToloanLossProvisionReport")
	public void proceedToloanLossProvisionReportDriveByTask() {

	}

	@StartTask
	public void startEvictionTask() {

	}

	@EndTask(transition = "proceedToevictionFork")
	public void proceedToevictionForkEvictionTask() {

	}

	@StartTask
	public void startUploadClosingReportTask() {

	}

	@EndTask(transition = "proceedToinsuredLoan")
	public void proceedToinsuredLoanUploadClosingReportTask() {

	}

	@StartTask
	public void startListPropertyTask() {

	}

	@EndTask(transition = "proceedTomonitorListing")
	public void proceedTomonitorListingListPropertyTask() {

	}

	@StartTask
	public void startUploadFinalInvoicesTask() {

	}

	@EndTask(transition = "proceedTopMComplete")
	public void proceedTopMCompleteUploadFinalInvoicesTask() {

	}

	@StartTask
	public void startTransferUtilitiesOutTask() {

	}

	@EndTask(transition = "proceedTouploadFinalInvoices")
	public void proceedTouploadFinalInvoicesTransferUtilitiesOutTask() {

	}

	@StartTask
	public void startIssueSOCTask() {

	}

	@EndTask(transition = "proceedToenterLegalProcessType")
	public void proceedToenterLegalProcessTypeIssueSOCTask() {

	}

	@StartTask
	public void startAppraisalCompleteTask() {

	}

	@EndTask(transition = "proceedTolistingSummary")
	public void proceedTolistingSummaryAppraisalCompleteTask() {

	}
	

	@StartTask
	public void startCheckConditionsWaivedTask() {

	}

	@EndTask(transition = "notWaivedFallThrough")
	public void notWaivedFallThroughCheckConditionsWaivedTask() {

	}
	@EndTask(transition = "proceedTowaiverCheck")
	public void proceedTowaiverCheckCheckConditionsWaivedTask() {

	}
	@EndTask(transition = "waived")
	public void waivedCheckConditionsWaivedTask() {

	}

	@StartTask
	public void startWaiverCheckTask() {

	}

	@StartTask
	public void startNoticeOfEvictionTask() {

	}

	@EndTask(transition = "proceedToevictionDate")
	public void proceedToevictionDateNoticeOfEvictionTask() {

	}

	@StartTask
	public void startQuotesReportTask() {

	}

	@EndTask(transition = "proceedToapproveQuote")
	public void proceedToapproveQuoteQuotesReportTask() {

	}
	@EndTask(transition = "proceedTovacantMonitoring")
	public void proceedTovacantMonitoringQuotesReportTask() {

	}

	@StartTask
	public void startOnHoldTask() {

	}

	@EndTask(transition = "ownerOccupiedOrUnknown")
	public void ownerOccupiedOrUnknownOnHoldTask() {

	}
	@EndTask(transition = "proceedTonOSExpiry")
	public void proceedTonOSExpiryOnHoldTask() {

	}

	@StartTask
	public void startLoanLossProvisionReportTask() {

	}

	@StartTask
	public void startDesktopAppraisalOrderTask() {

	}

	@EndTask(transition = "proceedToacceptAppraisalOrder")
	public void proceedToacceptAppraisalOrderDesktopAppraisalOrderTask() {

	}

	@StartTask
	public void startVacantMonitoringTask() {

	}

	@EndTask(transition = "proceedTosetVacantMonitoringFrequency")
	public void proceedTosetVacantMonitoringFrequencyVacantMonitoringTask() {

	}
	@EndTask(transition = "proceedToclosing")
	public void proceedToclosingVacantMonitoringTask() {

	}

	@StartTask
	public void startRedirectUtilitiesInTask() {

	}

	@EndTask(transition = "proceedToquotesReport")
	public void proceedToquotesReportRedirectUtilitiesInTask() {

	}

	@StartTask
	public void startTenantedMonitorTask() {

	}

	@EndTask(transition = "statusIsTenanted")
	public void statusIsTenantedTenantedMonitorTask() {

	}
	@EndTask(transition = "proceedToclosing")
	public void proceedToclosingTenantedMonitorTask() {

	}

	@StartTask
	public void startNOSExpiryTask() {

	}

	@EndTask(transition = "proceedTonOSExpiryFork")
	public void proceedTonOSExpiryForkNOSExpiryTask() {

	}

	@StartTask
	public void startEvictionDateTask() {

	}

	@EndTask(transition = "proceedToeviction")
	public void proceedToevictionEvictionDateTask() {

	}

	@StartTask
	public void startTitleSearchTask() {

	}

	@EndTask(transition = "proceedTodemandExpires")
	public void proceedTodemandExpiresTitleSearchTask() {

	}

	@StartTask
	public void startClosingTask() {

	}

	@EndTask(transition = "proceedTotransferUtilitiesOut")
	public void proceedTotransferUtilitiesOutClosingTask() {

	}
	@EndTask(transition = "proceedTouploadClosingReport")
	public void proceedTouploadClosingReportClosingTask() {

	}
	@EndTask(transition = "proceedToconfirmSaleClosed")
	public void proceedToconfirmSaleClosedClosingTask() {

	}
	@EndTask(transition = "proceedTosaleProceedsDeposited")
	public void proceedTosaleProceedsDepositedClosingTask() {

	}
	@EndTask(transition = "proceedTopreFinalInvoices")
	public void proceedTopreFinalInvoicesClosingTask() {

	}
	@EndTask(transition = "proceedTorealEstateComplete")
	public void proceedTorealEstateCompleteClosingTask() {

	}

	@StartTask
	public void startOwnerOccupiedMonitorTask() {

	}

	@EndTask(transition = "proceedToevictionDate")
	public void proceedToevictionDateOwnerOccupiedMonitorTask() {

	}

	@StartTask
	public void startPreFinalInvoicesTask() {

	}

	@StartTask
	public void startDeleteTask() {

	}

	@EndTask(transition = "proceedTomonitorListing")
	public void proceedTomonitorListingDeleteTask() {

	}

	@StartTask
	public void startOfferExpiryTask() {

	}

	@EndTask(transition = "soldFirm")
	public void soldFirmOfferExpiryTask() {

	}

	@StartTask
	public void startAssignSubAgentTask() {

	}

	@EndTask(transition = "proceedTolistProperty")
	public void proceedTolistPropertyAssignSubAgentTask() {

	}

	@StartTask
	public void startTransferTitleTask() {

	}

	@EndTask(transition = "yes")
	public void yesTransferTitleTask() {

	}

	@StartTask
	public void startApproveQuoteTask() {

	}

	@EndTask(transition = "no")
	public void noApproveQuoteTask() {

	}
	@EndTask(transition = "yesOrPartial")
	public void yesOrPartialApproveQuoteTask() {

	}

	@StartTask
	public void startQuoteWorkCompleteTask() {

	}

	@EndTask(transition = "proceedTouploadPhotoEvidence")
	public void proceedTouploadPhotoEvidenceQuoteWorkCompleteTask() {

	}

	@StartTask
	public void startSetVacantMonitoringFrequencyTask() {

	}

	@StartTask
	public void startDemandExpiresTask() {

	}

	@EndTask(transition = "proceedToissueSOC")
	public void proceedToissueSOCDemandExpiresTask() {

	}

	@StartTask
	public void startAssignJudgmentAndFinalReportToMITask() {

	}

	@EndTask(transition = "proceedTolawyerComplete")
	public void proceedTolawyerCompleteAssignJudgmentAndFinalReportToMITask() {

	}

	@StartTask
	public void startUploadPhotoEvidenceTask() {

	}

	@EndTask(transition = "proceedTovacantMonitoring")
	public void proceedTovacantMonitoringUploadPhotoEvidenceTask() {

	}

	@StartTask
	public void startFullCMATask() {

	}

	@EndTask(transition = "proceedTolistingSummary")
	public void proceedTolistingSummaryFullCMATask() {

	}
	@EndTask(transition = "proceedTolistProperty")
	public void proceedTolistPropertyFullCMATask() {

	}

	@StartTask
	public void startWOPOccupancyCheckTask() {

	}

	@EndTask(transition = "vacant")
	public void vacantWOPOccupancyCheckTask() {

	}
	@EndTask(transition = "ownerOccupied")
	public void ownerOccupiedWOPOccupancyCheckTask() {

	}

	@StartTask
	public void startFirstOccupancyCheckTask() {

	}

	@EndTask(transition = "ownerOccupied")
	public void ownerOccupiedFirstOccupancyCheckTask() {

	}
	@EndTask(transition = "vacant")
	public void vacantFirstOccupancyCheckTask() {

	}
	@EndTask(transition = "unconfirmed")
	public void unconfirmedFirstOccupancyCheckTask() {

	}
	@EndTask(transition = "tenantorOwnerOrTenant")
	public void tenantorOwnerOrTenantFirstOccupancyCheckTask() {

	}

	@StartTask
	public void startCollectRentsTask() {

	}

	@EndTask(transition = "no")
	public void noCollectRentsTask() {

	}
	@EndTask(transition = "yes")
	public void yesCollectRentsTask() {

	}

	@StartTask
	public void startSecondOccupancyCheckTask() {

	}

	@EndTask(transition = "ownerOccupied")
	public void ownerOccupiedSecondOccupancyCheckTask() {

	}
	@EndTask(transition = "vacant")
	public void vacantSecondOccupancyCheckTask() {

	}
	@EndTask(transition = "unconfirmed")
	public void unconfirmedSecondOccupancyCheckTask() {

	}
	@EndTask(transition = "tenant")
	public void tenantSecondOccupancyCheckTask() {

	}

	@StartTask
	public void startRentalAttornmentTask() {

	}

	@EndTask(transition = "no")
	public void noRentalAttornmentTask() {

	}
	@EndTask(transition = "yes")
	public void yesRentalAttornmentTask() {

	}

	@StartTask
	public void startEnterLegalProcessTypeTask() {

	}

	@EndTask(transition = "foreclosure")
	public void foreclosureEnterLegalProcessTypeTask() {

	}
	@EndTask(transition = "saleForArrears")
	public void saleForArrearsEnterLegalProcessTypeTask() {

	}
	@EndTask(transition = "powerOfSale")
	public void powerOfSaleEnterLegalProcessTypeTask() {

	}
	@EndTask(transition = "judicialSale")
	public void judicialSaleEnterLegalProcessTypeTask() {

	}

	@StartTask
	public void startReviewOfferTask() {

	}

	@EndTask(transition = "reject")
	public void rejectReviewOfferTask() {

	}
	@EndTask(transition = "acceptConditionalSale")
	public void acceptConditionalSaleReviewOfferTask() {

	}

	@StartTask
	public void startMonitorListingTask() {

	}

	@EndTask(transition = "proceedToenterOfferDetails")
	public void proceedToenterOfferDetailsMonitorListingTask() {

	}

	@StartTask
	public void startConflictCheckTask() {

	}

	@EndTask(transition = "yes")
	public void yesConflictCheckTask() {

	}
	@EndTask(transition = "no")
	public void noConflictCheckTask() {

	}

	@StartTask
	public void startREAcceptTask() {

	}

	@EndTask(transition = "no")
	public void noREAcceptTask() {

	}
	@EndTask(transition = "yes")
	public void yesREAcceptTask() {

	}

	@StartTask
	public void startEnterOfferDetailsTask() {

	}

	@EndTask(transition = "yes")
	public void yesEnterOfferDetailsTask() {

	}
	
	@EndTask(transition = "no")
	public void noEnterOfferDetailsTask() {

	}
	

	@StartTask
	public void startLawyerCompleteTask() {

	}

	@EndTask(transition = "proceedTolenderCloseFIle")
	public void proceedTolenderCloseFIleLawyerCompleteTask() {

	}

	@StartTask
	public void startPMCompleteTask() {

	}

	@EndTask(transition = "proceedTolenderCloseFIle")
	public void proceedTolenderCloseFIlePMCompleteTask() {

	}

	@StartTask
	public void startLenderCloseFIleTask() {

	}

	@EndTask(transition = "proceedTocloseFile")
	public void proceedTocloseFileLenderCloseFIleTask() {

	}

	@StartTask
	public void startRealEstateCompleteTask() {

	}

	@EndTask(transition = "proceedTolenderCloseFIle")
	public void proceedTolenderCloseFIleRealEstateCompleteTask() {

	}

}
