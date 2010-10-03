package com.wc.jshopper.domain.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import org.apache.commons.lang.StringUtils;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.Component;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

//@Scope(ScopeType.CONVERSATION)
@Name("departmentAction")
public class DepartmentAction extends DepartmentActionBase implements
		java.io.Serializable {
	
	public void executeReport(String reportName, Map<String, String> parameters, JRBeanCollectionDataSource dataSource){
		try{
			byte[] bytes = JasperRunManager.runReportToPdf(this.getClass()
					.getResourceAsStream("departmentReport.jasper"),
					parameters, dataSource);
			HttpServletResponse response = (HttpServletResponse) javax.faces.context.FacesContext
					.getCurrentInstance().getExternalContext().getResponse();
			response.setContentType("application/pdf");
			response.addHeader("Content-Disposition",
					"attachment;filename=Report.pdf");
			response.setContentLength(bytes.length);
			ServletOutputStream servletOutputStream = response
					.getOutputStream();
			servletOutputStream.write(bytes, 0, bytes.length);
			servletOutputStream.flush();
			servletOutputStream.close();
			javax.faces.context.FacesContext.getCurrentInstance()
					.responseComplete();
		} catch (JRException jre) {
			jre.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void runReport() {		
		try {
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("Title", "Department Report");
			executeReport("departmentReport.jasper", parameters, new JRBeanCollectionDataSource(
					getEntityList()));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
