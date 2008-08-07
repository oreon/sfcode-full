package oaw4.demo.classic.uml.meta;

import oaw4.demo.classic.uml.extend.ClassUtil;
import oaw4.demo.classic.uml.extend.StereoTypeManager;

import org.apache.log4j.Logger;
import org.openarchitectureware.core.meta.core.ElementSet;
import org.openarchitectureware.meta.uml.Type;
import org.openarchitectureware.meta.uml.classifier.Association;
import org.openarchitectureware.meta.uml.classifier.AssociationEnd;
import org.openarchitectureware.meta.uml.classifier.Attribute;
import org.openarchitectureware.meta.uml.classifier.Class;

public abstract class AbstractEntity extends
		org.openarchitectureware.meta.uml.classifier.Class {

	private String displayName;
	
	private static final Logger logger = Logger.getLogger(AbstractEntity.class);
	
	private boolean generateSampleData = true;
	
	private boolean fullTextSearchEnabled = true;

	/**
	 * @return All elements which have Column stereotype applied
	 */
	public ElementSet Column() {
		ElementSet columns = new ElementSet();
		ElementSet attributes = getAllAttributes();

		for (Object object : attributes) {
			Attribute attribute = (Attribute) object;

			// TODO hardcoding find a better way to determine this
			if (isAttributeColumn(attribute))
				columns.add(attribute);
		}

		return columns;
	}

	private boolean isAttributeColumn(Attribute attribute) {
		return attribute.getMetaClass().getSimpleName().equals("Column");
	}

	/**
	 * @return - All columns which have searchable set to true
	 */
	public ElementSet Findable() {
		ElementSet columns = Column();
		ElementSet findables = new ElementSet();

		for (Object object : columns) {
			Column col = (Column) object;

			if (col.isSearchable() || col.isUnique())
				findables.add(col);
		}

		return findables;
	}

	/**
	 * @return - All columns which have a unique constraint
	 */
	public ElementSet Unique() {
		ElementSet columns = Column();
		ElementSet findables = new ElementSet();

		for (Object object : columns) {
			Column col = (Column) object;
			System.out.println("checking if " + col.NameS() + " is unique");

			if (col.isUnique())
				findables.add(col);
		}

		return findables;
	}

	/**
	 * This method returns all attributes , of this class and of all
	 * superclasses
	 * 
	 * @return
	 */
	public ElementSet getAllAttributes() {

		ElementSet attributes = getAttributesForThisClassAndSuperClasses();

		// clear existing type modifiers
		for (Object obj : attributes) {
			Attribute attribute = (Attribute) obj;
			attribute.setTypeModifier(null);
		}

		ElementSet embeddables = getAllContianedAssociations();

		for (Object object : embeddables) {
			AssociationEnd ae = (AssociationEnd) object;

			ElementSet assocAttributes = ae.Class().Attribute();
			for (Object obj : assocAttributes) {
				Attribute attribute = (Attribute) obj;
				attribute.setTypeModifier(ClassUtil.getAssocName(ae));
				logger.info("Applying typeModifier " + ClassUtil.getAssocName(ae)  + " to " + attribute.NameS()  );
			}

			attributes.addAll(ae.Class().Attribute());
		}
		return attributes;
	}
	
	public Attribute getFirstAttribute(){
		return (Attribute) getAllAttributes().get(0);
	}

	public ElementSet getAttributesForThisClassAndSuperClasses() {
		ElementSet attributes = new ElementSet();

		ElementSet superClasses = SuperClasss();

		for (Object object : superClasses) {
			Class clazz = (Class) object;
			attributes.addAll(clazz.Attribute());
		}

		attributes.addAll(Attribute());

		return attributes;
	}

	/**
	 * This method returns all attributes which are not Columns superclasses
	 * 
	 * @return
	 */
	public ElementSet NonColumnAttribute() {
		ElementSet attributes = getAttributesForThisClassAndSuperClasses();
		ElementSet nonColAttribs = new ElementSet();

		for (Object object : attributes) {
			Attribute attribute = (Attribute) object;

			if (!isAttributeColumn(attribute))
				nonColAttribs.add(attribute);
		}

		return nonColAttribs;
	}
	
	
	///////////////////////// Assocaitons ////////////////////////////////////////
	
	
	/** Will return all compositions of this entity e.g. For an order it'd return 
	 * all OrderItems 
	 * @return
	 */
	public ElementSet getAllComposedAssociations(){
		ElementSet composedAssociatons = new ElementSet();
	
		for (Object object : AssociationEnd()) {
			AssociationEnd ae = (AssociationEnd)object;

			if(ae.Opposite().isComposition() && ae.Opposite().isNavigable())
				composedAssociatons.add(ae);
		}

		return composedAssociatons;
	}
	
	/** Will return all compositions of this entity e.g. For an order it'd return 
	 * all OrderItems 
	 * @return
	 */
	public ElementSet getAllComposedAssociationsExceptOneOnOne(){
		ElementSet composedAssociatons = new ElementSet();
	
		for (Object object : AssociationEnd()) {
			AssociationEnd ae = (AssociationEnd)object;

			if(ae.Opposite().isComposition() && ae.Opposite().isNavigable() && ae.Opposite().isMultiple())
				composedAssociatons.add(ae);
		}

		return composedAssociatons;
	}
	
	public ElementSet getAllAggregatedManyToManyAssociations(){
		
		ElementSet composedAssociatons = new ElementSet();
		
		for (Object object : AssociationEnd()) {
			AssociationEnd ae = (AssociationEnd)object;
			logger.info("Calling getAll aggregate association " + ClassUtil.getAssocName(ae) );
			//if(StereoTypeManager.is)
			if(ae.isMultiple() && ae.Opposite().isNavigable() && ae.Opposite().isMultiple() ){
				logger.info("Found aggregate associateion " + ClassUtil.getAssocName(ae) );
				composedAssociatons.add(ae);
			}
		}

		return composedAssociatons;
	}
	
	
	public boolean isAssociationManyToMany(AssociationEnd ae){
		logger.info("Looking for in aggregates " + ClassUtil.getAssocName(ae) );
		
		ElementSet aggregateAssociations = getAllAggregatedManyToManyAssociations();
		
		logger.info("size of many to many assocs " + aggregateAssociations.size() );
		
		System.out.println( "<<<>>>" + ClassUtil.getAssocName(ae) + " " +ae.isMultiple() + " " + ae.isNavigable() + ae.Opposite().isMultiple());
		if(ae.isMultiple() && ae.isNavigable() && ae.Opposite().isMultiple() ){
			return true;
		}
		
		return false;
	}
	
	/**
	 * @return all outgoing associations other than one on one 
	 */
	public ElementSet getOutgoingAssociationsExceptOneOnOne() {
		ElementSet outgoingAssoc = new ElementSet();
		ElementSet allAssoc = getAllOutgoingAssociations();

		for (Object object : allAssoc) {
			AssociationEnd ae = (AssociationEnd)object;
			if(!ClassUtil.isAssociationOneOnOne(ae))
				outgoingAssoc.add(ae);
		}

		return outgoingAssoc;
	}

	/**
	 * Get all outgoing associations for this class and its superclasses, i.e.
	 * for an order it would get customer and other references
	 * 
	 * @return
	 */
	public ElementSet getAllOutgoingAssociations() {
		ElementSet outgoingAssoc = new ElementSet();
		outgoingAssoc.addAll(getOutgoingAssociations(this));

		ElementSet superclasses = SuperClasss();
		for (Object object : superclasses) {
			outgoingAssoc.addAll(getOutgoingAssociations((Class) object));
		}

		return outgoingAssoc;
	}

	private ElementSet getOutgoingAssociations(Class cls) {
		ElementSet associations = cls.AssociationEnd();
		ElementSet embeddables = new ElementSet();
		for (Object object : associations) {
			AssociationEnd ae = (AssociationEnd) object;
			System.out.println(cls.NameS() + " -->" + ae.Opposite().Name());
			AssociationEnd opposite = ae.Opposite();

			if (!opposite.isMultiple() && opposite.isNavigable()) {
				//System.out.println(opposite.Class().getMetaClass().getName());
				if (!opposite.Class().getMetaClass().getName().equalsIgnoreCase(
						"oaw4.demo.classic.uml.meta.Embeddable"))
					embeddables.add(ae.Opposite());
				else
					logger.info("Ignoring embeddable :context outgoingassociaitons "
							+ opposite.Class().Name());

			}
		}
		return embeddables;
	}

	/**
	 * Get all contained associations of this class and all superclasses
	 * 
	 * @return
	 */
	public ElementSet getAllContianedAssociations() {
		ElementSet embeddables = new ElementSet();
		embeddables.addAll(getContainedAssociations(this));

		ElementSet superclasses = SuperClasss();
		for (Object object : superclasses) {
			//logger.debug("adding " + )
			embeddables.addAll(getContainedAssociations((Class) object));
		}

		return embeddables;
	}

	/**
	 * We look for all embedded components and One on One associations
	 * 
	 * @param cls
	 * @return
	 */
	private ElementSet getContainedAssociations(Class cls) {
		ElementSet associations = cls.AssociationEnd();
		ElementSet embeddables = new ElementSet();
		for (Object object : associations) {
			AssociationEnd ae = (AssociationEnd) object;

			if (ClassUtil.isAssociationOneOnOne(ae)
					&& ae.Opposite().isNavigable()) {
				embeddables.add(ae.Opposite());
			}else if (StereoTypeManager.isEmbeddable(ae.Opposite().Class()) ){
				embeddables.add(ae.Opposite());
			}
		}
		return embeddables;
	}
	


	/**
	 * @param ae
	 * @return
	 */
	private boolean isAssociationEmbeddableContainment(AssociationEnd ae) {

		boolean result = ClassUtil.isAssociationOneOnOne(ae)
				&& ae.Opposite().isNavigable() && ae.isComposition();

		return result;
	}

	/**
	 * This value indicates how entitie's should be shown in associations for
	 * instance for a person it can be "lastName, firstName" for product it can
	 * be the product name
	 * 
	 * @return
	 */
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/** Whether to generate sample data for this entity 
	 * @return
	 */
	public boolean isGenerateSampleData() {
		return generateSampleData;
	}

	public void setGenerateSampleData(boolean generateSampleData) {
		this.generateSampleData = generateSampleData;
	}

	public boolean isFullTextSearchEnabled() {
		return fullTextSearchEnabled;
	}

	public void setFullTextSearchEnabled(boolean fullTextSearchEnabled) {
		this.fullTextSearchEnabled = fullTextSearchEnabled;
	}
}
