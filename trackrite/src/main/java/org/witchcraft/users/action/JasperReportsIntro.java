package org.witchcraft.users.action;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;

public class JasperReportsIntro {

	public static void main(String[] args) {

		JasperReport jasperReport;
		JasperPrint jasperPrint;

		Map<String, Object> parameters = new HashMap<String, Object>();
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("appEntityManager");

		parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER,
				emf.createEntityManager());

		try {

		//	Class.forName("com.mysql.jdbc.Driver");
		//	Connection connection = DriverManager
		//			.getConnection("jdbc:mysql://localhost:3306/newrecoverydb?user=root&password=root");

			InputStream reportStreamXML = new JasperReportsIntro().getClass()
					.getResourceAsStream(
							"/reports/" + "ActiveBugsReport" + ".jrxml");

			jasperReport = JasperCompileManager.compileReport(reportStreamXML);
			jasperPrint = JasperFillManager.fillReport(jasperReport,
					parameters);
			JasperExportManager.exportReportToPdfFile(jasperPrint,
					"src/main/resources/reports/simple_report.pdf");

		} catch (JRException e) {
			e.printStackTrace();
	//	} catch (ClassNotFoundException e) {
	//		e.printStackTrace();
	//	} catch (SQLException e) {
	//		e.printStackTrace();
		}
	}

	

}