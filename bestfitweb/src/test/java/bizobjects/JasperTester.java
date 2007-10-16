package bizobjects;

import java.io.InputStream;
import java.util.HashMap;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class JasperTester {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JasperReport jasperReport;
	    JasperPrint jasperPrint;
	    try
	    { InputStream stream = new JasperTester().getClass().
	    		getResourceAsStream("/reports/demo.jrxml");
	      jasperReport = JasperCompileManager.compileReport(stream);
	      jasperPrint = JasperFillManager.fillReport(
	          jasperReport, new HashMap(), new JREmptyDataSource());
	      JasperExportManager.exportReportToPdfFile(
	          jasperPrint, "simple_report.pdf");
	    }
	    catch (JRException e)
	    {
	      e.printStackTrace();
	    }

	}

}
