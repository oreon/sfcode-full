package org.witchcraft.users.action;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.wc.trackrite.domain.Employee;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;

public class JasperReportsIntro {

	public static void main(String[] args) {

		JasperReport jasperReport;
		JasperPrint jasperPrint;

		Map<String, Object> parameters = new HashMap<String, Object>();
		
		
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("appEntityManager");
		
	    EntityManager em = emf.createEntityManager();

		parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER,em);
		
		parameters.put("developer", em.find(Employee.class, 1L));
				

		try {
			InputStream reportStreamXML = new JasperReportsIntro().getClass()
					.getResourceAsStream(
							"/reports/" + "TimeSheetReport" + ".jrxml");

			jasperReport = JasperCompileManager.compileReport(reportStreamXML);
			jasperPrint = JasperFillManager.fillReport(jasperReport,
					parameters);
			JasperExportManager.exportReportToPdfFile(jasperPrint,
					"src/main/resources/reports/simple_report.pdf");

		} catch (JRException e) {
			e.printStackTrace();
		}finally{
			emf.close();
		}
	}

	

}