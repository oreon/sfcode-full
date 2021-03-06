package org.witchcraft.base.entity;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.mapping.Property;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.validator.PropertyConstraint;
import org.hibernate.validator.Validator;
import org.jboss.seam.Component;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Transactional;

@Name("UniqueValidator")
public class UniqueValidator implements Validator<Unique>, PropertyConstraint {

	private static final long serialVersionUID = -1458203631809206211L;

	@In
	protected FullTextEntityManager entityManager;
	
	//Entity for which validation is to be fired
    private String targetEntity;
   //Field for which validation is to be fired.
    private String field;

	public void initialize(Unique parameters) {
		targetEntity = ((Unique)parameters).entityName();
		field = ((Unique)parameters).fieldName();
	}

	//@Transactional
	public boolean isValid(Object value) {
		return true;
		
		/*
		EntityManager entityManager = (EntityManager) Component.getInstance("entityManager");
		Query query = entityManager.createQuery("select t from " + targetEntity
				+ " t where t." + field + "  = :value and t.id is not null ");
		query.setParameter("value", value);

		try {
			query.getSingleResult();
			return false;
		} catch (final NoResultException e) {

			return true;
		}
		*/
		
	}

	public void apply(Property arg0) {
		// TODO Auto-generated method stub
		
	}
}