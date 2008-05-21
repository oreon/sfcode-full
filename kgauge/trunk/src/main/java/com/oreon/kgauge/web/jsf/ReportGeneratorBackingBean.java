package com.oreon.kgauge.web.jsf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.witchcraft.model.jsf.BaseBackingBean;
import org.witchcraft.model.support.errorhandling.BusinessException;

/**
 * This class writes the compiled jasper report to a pdf/stream
 * 
 * @author jsingh
 * 
 */
public class ReportGeneratorBackingBean {

	private static final String REPORTS = "/reports/";

	Logger log = Logger.getLogger(ReportGeneratorBackingBean.class);

	private EntityManagerFactory entityManagerFactory;
	
	private static Set<String> compiledReports = new HashSet<String>();

	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}

	public void setEntityManagerFactory(
			EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void generateReport(ActionEvent actionEvent) {

		String reportFile = (String) BaseBackingBean
				.getActionParamValue("reportName"); // CustomerOrders";

		log.info("Running report for " + reportFile);

		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();

		ServletOutputStream servletOutputStream;
		try {
			servletOutputStream = response.getOutputStream();

			Map parameters = new HashMap();
			EntityManager em = entityManagerFactory.createEntityManager();

			parameters.put(
					JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, em);
			parameters.put("ReportTitle", "Customer Demographics");
			
			InputStream compiledReportStream = getCompiledReportFile(reportFile, context);

			JasperRunManager.runReportToPdfStream(compiledReportStream, servletOutputStream, parameters,
					((Session) em.getDelegate()).connection());
			// connection.close();
			response.setContentType("application/pdf");
			context.responseComplete();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JRException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BusinessException be) {
			createErrorMessage(be.getMessage(), be.getMessage(), be.getCause());
		}
	}

	/**
	 * If the report is not already compiled, this function will compile it
	 * 
	 * @param fileName
	 */
	private InputStream getCompiledReportFile(String fileName,
			FacesContext context) {

		String compiledFile = REPORTS + fileName + ".jasper";
		String xmlFile = REPORTS + fileName + ".jrxml";

		JasperReport report = null;

		InputStream reportStream = this.getClass().getResourceAsStream(
				compiledFile);

		// If the compiled file does not exist or we are running it for the first time
		// we need to compile it
		if (reportStream == null || !(compiledReports.contains(compiledFile))) {

			InputStream reportStreamXML = this.getClass().getResourceAsStream(
					xmlFile);

			if (reportStreamXML == null) {
				throw new BusinessException("report_file_does_not_exist "
						+ xmlFile);
			}

			
			String filePath = ((ServletContext) context
					.getExternalContext().getContext())
					.getRealPath("/WEB-INF/classes/" + xmlFile);
			
			File file = new File(filePath);
			if(!file.exists()){
				filePath = System.getProperty("user.dir") +  "/src/main/resources" + xmlFile;
			}
			
			System.out.println("current working dir is " + filePath);

			try {
				JasperCompileManager
						.compileReportToFile(filePath);
				// JasperCompileManager.compileReportToStream(reportStreamXML,
				// );
			} catch (JRException e) {
				throw new BusinessException("error_compiling_report_file", e);
			}

			reportStream = this.getClass().getResourceAsStream(compiledFile);
		}
		
		if(reportStream == null){
			throw new BusinessException("unknown error - report stream is null");
		}

		compiledReports.add(compiledFile);
		return reportStream;

	}

	/**
	 * Creates and add an error message to the faces context
	 * 
	 * @param errorDetail
	 * @param errorTitle
	 */
	protected void createErrorMessage(String errorDetail, String errorTitle,
			Throwable throwable) {
		log.error(errorDetail, throwable);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, errorTitle,
						errorDetail));
	}

	//protected static ServletContext getServletContext(){
		
	//}
}
