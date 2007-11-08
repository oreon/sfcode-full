package bizobjects;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;

public class JasperTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JasperReport jasperReport;
		JasperPrint jasperPrint = null;
		try {

			InputStream stream = new JasperTester().getClass()
					.getResourceAsStream("/reports/CustomerOrders.jrxml");

			System.out.println("Compiling report...");

			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/ecomm?user=root&password=root");

			EntityManagerFactory emf = (EntityManagerFactory) BeanHelper.getBean("entityManagerFactory");
			
			Map parameters = new HashMap();
			parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, emf.createEntityManager());
			parameters.put("ReportTitle", "Customer Demographics");
			
			jasperReport = JasperCompileManager.compileReport(stream);
			jasperPrint = JasperFillManager.fillReport(jasperReport,
					parameters, connection);
			JasperExportManager.exportReportToPdfFile(jasperPrint,
					"simple_report.pdf");
			System.out.println("done");
		} catch (JRException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
