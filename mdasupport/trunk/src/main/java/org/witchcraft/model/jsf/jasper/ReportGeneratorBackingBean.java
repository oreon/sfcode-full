package org.witchcraft.model.jsf.jasper;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;

/**
 * This class writes the compiled jasper report to a pdf/stream
 * 
 * @author jsingh
 * 
 */
public class ReportGeneratorBackingBean {
	
	private EntityManagerFactory entityManagerFactory;
	
	
	public EntityManagerFactory getEntityManagerFactory() {
		return entityManagerFactory;
	}


	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory = entityManagerFactory;
	}

	public void generateReport(ActionEvent actionEvent)
			throws ClassNotFoundException, SQLException, IOException,
			JRException {
		
		String reportFile = "CustomerOrders";
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		
		JasperCompileManager.compileReportToFile(
			"reports/" + reportFile + ".jrxml");
		
		InputStream reportStream = context.getExternalContext()
				.getResourceAsStream("/reports/" + reportFile + ".jasper");
		ServletOutputStream servletOutputStream = response.getOutputStream();
		
		Map parameters = new HashMap();
		EntityManager em = entityManagerFactory.createEntityManager();
		
		parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, 
				em);
		parameters.put("ReportTitle", "Customer Demographics");
		
		
		JasperRunManager.runReportToPdfStream(reportStream,
				servletOutputStream, parameters, ((Session)em.getDelegate()).connection() );
		//connection.close();
		response.setContentType("application/pdf");
		servletOutputStream.flush();
		servletOutputStream.close();
	}

}
