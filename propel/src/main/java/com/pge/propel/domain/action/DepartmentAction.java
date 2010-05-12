package com.pge.propel.domain.action;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.jpa.FullTextQuery;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Scope;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;

import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;
import org.jboss.seam.annotations.Observer;

import com.pge.propel.domain.Department;

@Scope(ScopeType.CONVERSATION)
@Name("departmentAction")
public class DepartmentAction extends DepartmentActionBase implements
		java.io.Serializable {

	public List<Department> autoComplete(Object o) {

		QueryParser parser = new QueryParser("name", new StopAnalyzer());
		Query luceneQuery = null;
		try {
			luceneQuery = parser.parse("name:" + o.toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FullTextQuery query = this.getEntityManager().createFullTextQuery(
				luceneQuery, Department.class);
		// String sql =
		// "select from com.pge.propel.domain.Employee e where e.firstName like '%"+o.toString()+"%')";
		return query.getResultList();// this.getEntityManager().createQuery("select emp from com.pge.propel.domain.Employee emp").getResultList();
	}

}
