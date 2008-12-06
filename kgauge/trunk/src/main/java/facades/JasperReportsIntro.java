package facades;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.query.JRJpaQueryExecuterFactory;

import org.witchcraft.model.support.springbeanhelpers.BeanHelper;

public class JasperReportsIntro {
	
	public static void main(String[] args) {
		JasperReport jasperReport;
		JasperPrint jasperPrint;
		
		Map<String, Object> parameters = new HashMap<String, Object>();
		EntityManagerFactory emf = (EntityManagerFactory)BeanHelper.getBean("entityManagerFactory");
        parameters.put(JRJpaQueryExecuterFactory.PARAMETER_JPA_ENTITY_MANAGER, emf.createEntityManager());        
 
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager
					.getConnection("jdbc:mysql://localhost:3306/kgaugedb?user=root&password=root");

			InputStream reportStreamXML = new JasperReportsIntro().getClass()
			.getResourceAsStream("/reports/" + "CandidateRegisterationByCountryStateReport" + ".jrxml");
			
			jasperReport = JasperCompileManager.compileReport(reportStreamXML);
			jasperPrint = JasperFillManager.fillReport(jasperReport,
					parameters, connection);	
			JasperExportManager.exportReportToPdfFile(jasperPrint,
					"src/main/resources/reports/simple_report.pdf");
		} catch (JRException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}