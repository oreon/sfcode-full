package org.witchcraft.base.entity;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.ParseException;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.web.RequestParameter;
import org.jboss.seam.framework.EntityQuery;
import org.jboss.seam.persistence.PersistenceProvider;

import com.oreon.callosum.drugs.Drug;



@SuppressWarnings("serial")
public abstract class SimpleBaseQuery<E extends BusinessEntity> extends EntityQuery<E> {

	private Class<E> entityClass = null;

	protected E instance;
	
	private Range<java.util.Date> dateCreatedRange = new Range<Date>();

	private List<E> entityList;
	
	@RequestParameter
	protected String searchText;
	
	public static final int DEFAULT_PAGES_FOR_PAGINATION = 25;
	
	@In
	// @PersistenceContext(type=EXTENDED)
	protected FullTextEntityManager entityManager;

	
	/*
	public E getObjectByID(PK id) {
		return (E) getEntityManager().find(getEntityClass(), id);
	}*/

	

	public Range<java.util.Date> getDateCreatedRange() {
		return dateCreatedRange;
	}

	public void setDateCreatedRange(Range<java.util.Date> dateCreatedRange) {
		this.dateCreatedRange = dateCreatedRange;
	}
	
	/*
	@SuppressWarnings("unchecked")
	public String textSearch() {

		BusinessEntity businessEntity = null;
		try {
			businessEntity = getEntityClass().newInstance();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		}
		
		List<String> listSearchableFields = businessEntity.listSearchableFields();
 
		if ( listSearchableFields == null) {
			throw new RuntimeException(
					businessEntity.getClass().getSimpleName()
							+ " needs to override retrieveSearchableFieldsArray method ");
		}

		String[] arrFields = new String[listSearchableFields.size()];
		listSearchableFields.toArray(arrFields);
		
		MultiFieldQueryParser parser = new MultiFieldQueryParser(arrFields, new StandardAnalyzer());

		org.apache.lucene.search.Query query = null;

		try {
			if(searchText == null)
				searchText = "this";
			query = parser.parse(searchText);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}

		FullTextQuery ftq = entityManager.createFullTextQuery(query, getEntityClass());

		List<E> result = ftq.getResultList();
		
		//setEntityList(result);
		return "success";
	}*/
	
	
	
	/** do autocomplete based on lucene/hibernate text search
	 * @param suggest
	 * @return
	 */
	public List<E> autocomplete(Object suggest) {

        String input = (String)suggest;

        ArrayList<E> result = new ArrayList<E>();
        //departmentListQuery.getDepartment().setName(input);
       
        Iterator<E> iterator = getResultList().iterator();

        while (iterator.hasNext()) {
            E elem =  iterator.next();
            String elemProperty = elem.getDisplayName();
            if ((elemProperty != null && elemProperty.toLowerCase().indexOf(input.toLowerCase()) == 0) || "".equals(input)){
                result.add(elem);
            }
        }

        return result;
    }
	
	/** Do autocomplete based on database fields 
	 * @param suggest
	 * @return
	 */
	public List<E> autocompletedb(Object suggest) {
		String input = (String) suggest;
		setupForAutoComplete(input);
		super.setRestrictionLogicOperator("or");
		return getResultList();
	}

	/** This method should be overridden by derived classes to provide fileds that will be used for autocomplete
	 * @param input
	 */
	protected void setupForAutoComplete(String input) {
		
	}
	
	
}