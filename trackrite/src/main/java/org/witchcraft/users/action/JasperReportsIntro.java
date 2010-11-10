package org.witchcraft.users.action;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;

import org.wc.trackrite.domain.Employee;

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
		Date from;
		try {
			from = new SimpleDateFormat("yy-MM-dd").parse("10-09-01");
			parameters.put("from", from);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		parameters.put("to", new Date());

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