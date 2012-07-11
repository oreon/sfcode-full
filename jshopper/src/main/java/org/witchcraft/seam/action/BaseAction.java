package org.witchcraft.seam.action;

import java.awt.print.Book;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.SimpleSpanFragmenter;
import org.apache.lucene.util.Version;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.witchcraft.base.entity.BaseEntity;
import org.witchcraft.base.entity.EntityComment;
import org.witchcraft.base.entity.FileAttachment;
import org.witchcraft.base.entity.TextSearchResultHolder;
import org.witchcraft.exceptions.ContractViolationException;
import org.witchcraft.seam.security.hasPermission;

import com.oreon.talent.domain.Exam;

/**
 * @author singj3
 * 
 * @param <T>
 */
public abstract class BaseAction<T extends BaseEntity> {

	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId( Long id ) {
		this.id = id;
	}

	protected T entity;

	@Inject
	protected Conversation conversation;

	@Inject
	//@PersistenceContext
	protected EntityManager entityManager;

	private String searchText;

	private List<TextSearchResultHolder> searchResultsList = new ArrayList<TextSearchResultHolder>();

	public String create() {

		this.conversation.begin();
		return "edit" + getEntityClass().getSimpleName() + "?faces-redirect=true";
	}

	protected abstract Class<T> getEntityClass();

	// Needed for many to many list initializations in dialog
	public void onRowSelect( SelectEvent event ) throws Exception {
		T t = (T) event.getObject();
		setId( t.getId() );
		loadAssociations();
	}

	public void retrieve() {

		if ( FacesContext.getCurrentInstance().isPostback() ) {
			return;
		}

		if ( this.conversation.isTransient() ) {
			this.conversation.begin();
		}

		if ( this.id == null ) {
			this.entity = this.search;
			if ( entity == null )
				entity = createInstance();
		} else {
			this.entity = this.entityManager.find( getEntityClass(), getId() );
		}

		loadAssociations();
	}

	protected void loadAssociations() {
		initLists();
	}

	public String persist() {

		try {

			updateComposedAssociations();
			entity = this.entityManager.merge( this.entity );
			

		} catch ( Exception e ) {
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( e.getMessage() ) );
			return null;
		}

		
		return "success";
	}
	
	
	/**
	 * Method to be called from save of dialogs
	 */
	public void persistAndClear(){
		persist();
		entity = null;
	}

	/*
	 * Support updating and deleting T entities
	 */
	@hasPermission
	public String update() {

		persist();

		long currentId = getEntity().getId();

		if ( !conversation.isTransient() )
			this.conversation.end();

		return "view" + getEntityClass().getSimpleName() + "?faces-redirect=true&id=" + currentId;

	}

	public String delete() {
		this.conversation.end();

		try {
			this.entityManager.remove( this.entityManager.find( getEntityClass(), getId() ) );
			this.entityManager.flush();
			return "search?faces-redirect=true";
		} catch ( Exception e ) {
			FacesContext.getCurrentInstance().addMessage( null, new FacesMessage( e.getMessage() ) );
			return null;
		}
	}

	public void fullTextSearch() {

		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager( entityManager );
		// entityManager.getTransaction().begin();

		// create native Lucene query unsing the query DSL
		// alternatively you can write the Lucene query using the Lucene query parser
		// or the Lucene programmatic API. The Hibernate Search DSL is recommended though
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity( Exam.class ).get();
		org.apache.lucene.search.Query query = qb.keyword().onFields( "title", "subtitle" ).matching( "Java rocks!" ).createQuery();

		// wrap Lucene query in a javax.persistence.Query
		javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery( query, Book.class );

		// execute search
		List result = persistenceQuery.getResultList();

		// entityManager.getTransaction().commit();
		entityManager.close();
	}

	private static final String SEARCH_DATA = "searchData";

	public String textSearch() {

		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager( entityManager );

		QueryParser parser = new QueryParser( Version.LUCENE_35, SEARCH_DATA, fullTextEntityManager.getSearchFactory().getAnalyzer( "entityAnalyzer" ) );

		org.apache.lucene.search.Query query = null;

		if ( searchText == null )
			return null;

		try {
			query = parser.parse( searchText );
		} catch ( ParseException e1 ) {
			// TODO
			// addErrorMessaget(e1.getMessage());
		}

		QueryScorer scorer = new QueryScorer( query, SEARCH_DATA );
		// Highlight using a CSS style
		SimpleHTMLFormatter formatter = new SimpleHTMLFormatter( "<span style='background-color:yellow;'>", "</span>" );
		Highlighter highlighter = new Highlighter( formatter, scorer );
		highlighter.setTextFragmenter( new SimpleSpanFragmenter( scorer, 100 ) );

		FullTextQuery ftq = fullTextEntityManager.createFullTextQuery( query, getEntityClass() );

		List<T> result = ftq.getResultList();

		for ( T e : result ) {
			try {
				String fragment = highlighter.getBestFragment( fullTextEntityManager.getSearchFactory().getAnalyzer( "entityAnalyzer" ), SEARCH_DATA,
								e.getSearchData() );

				TextSearchResultHolder textSearchResultHolder = new TextSearchResultHolder( e, fragment );
				if ( !searchResultsList.contains( textSearchResultHolder ) &&  !StringUtils.isEmpty( fragment ))
					searchResultsList.add( textSearchResultHolder );
			} catch ( Exception ex ) {
				throw new ContractViolationException( ex.getMessage() );
			}
		}

		// setSearchResultsList( result );
		return "textSearch";
	}

	/*
	 * Support searching T entities with pagination
	 */

	private int page;
	private long count;
	private List<T> pageItems;

	protected T search = createInstance();

	public abstract T createInstance();

	public int getPage() {
		return this.page;
	}

	public void setPage( int page ) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public T getSearch() {
		return this.search;
	}

	public void setSearch( T search ) {
		this.search = search;
	}

	public void search() {
		this.page = 0;
	}

	public void paginate() {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

		// Populate this.count

		CriteriaQuery<Long> countCriteria = builder.createQuery( Long.class );
		Root<T> root = countCriteria.from( getEntityClass() );

		countCriteria = countCriteria.select( builder.count( root ) ).where( getSearchPredicates( root ) );

		this.count = this.entityManager.createQuery( countCriteria ).getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<T> criteria = builder.createQuery( getEntityClass() );
		root = criteria.from( getEntityClass() );
		TypedQuery<T> query = this.entityManager.createQuery( criteria.select( root ).where( getSearchPredicates( root ) ) );
		query.setFirstResult( this.page * getPageSize() ).setMaxResults( getPageSize() );
		this.pageItems = query.getResultList();
	}

	abstract protected Predicate[] getSearchPredicates( Root<T> root );

	public List<T> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back T entities (e.g. from inside an HtmlSelectOneMenu)
	 */

	public List<T> getAll() {
		Session session = (Session) entityManager.getDelegate();
		// session.enableFilter("archiveFilterDef").setParameter("aArchived", "0");
		CriteriaQuery<T> criteria = this.entityManager.getCriteriaBuilder().createQuery( getEntityClass() );
		return this.entityManager.createQuery( criteria.select( criteria.from( getEntityClass() ) ) ).getResultList();
	}

	public Converter getConverter() {

		return new Converter() {

			@Override
			public Object getAsObject( FacesContext context, UIComponent component, String value ) {

				T t = entityManager.find( getEntityClass(), Long.valueOf( value ) );

				/*
				 * Hibernate.initialize(t); if (t instanceof HibernateProxy) { t = (T) ((HibernateProxy) t) .getHibernateLazyInitializer().getImplementation();
				 * }
				 */

				return t;
			}

			@Override
			public String getAsString( FacesContext context, UIComponent component, Object value ) {

				if ( value == null ) {
					return "";
				}

				/*
				 * Hibernate.initialize(value); if (value instanceof HibernateProxy) { value = ((HibernateProxy) value)
				 * .getHibernateLazyInitializer().getImplementation(); }
				 */

				return String.valueOf( ( (T) value ).getId() );
			}
		};
	}

	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */

	private T add = createInstance();

	public T getAdd() {
		return this.add;
	}

	public T getAdded() {
		T added = this.add;
		this.add = createInstance();
		return added;
	}

	@PostConstruct
	public void init() {
		model = new EntityLazyDataModel();
		initLists();
	}

	protected void updateComposedAssociations() {

	}

	protected void initLists() {

	}

	public T getEntity() {
		return entity;
	}

	public void setEntity( T entity ) {
		this.entity = entity;
	}
	
	public T getSelectedEntity() {
		return entity;
	}

	public void setSelectedEntity( T entity ) {
		this.entity = entity;
	}

	protected void downloadAttachment( FileAttachment fileAttachment ) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		response.setContentType( fileAttachment.getContentType() );

		response.addHeader( "Content-disposition", "attachment; filename=\"" + fileAttachment.getName() + "\"" );

		try {
			OutputStream os = response.getOutputStream();
			os.write( fileAttachment.getData() );
			os.flush();
			os.close();
			context.responseComplete();
		} catch ( Exception e ) {
			// log.error("\nFailure : " + e.toString() + "\n");
		}
	}

	// /////////////////////////////////// Comments ////////////////////////////////////////////////////////////////////////////////////

	private List<EntityComment> comments;

	public String getCurrentCommentText() {
		return currentCommentText;
	}

	public void setCurrentCommentText( String currentCommentText ) {
		this.currentCommentText = currentCommentText;
	}

	public void setComments( List<EntityComment> comments ) {
		this.comments = comments;
	}

	public List<EntityComment> getComments() {
		loadComments();
		return comments;
	}

	private String currentCommentText;

	public void saveComment() {
		EntityComment currentComment = new EntityComment();
		currentComment.setText( currentCommentText );
		currentComment.setEntityId( getEntity().getId() );
		currentComment.setEntityName( getClassName( getEntity() ) );
		entityManager.persist( currentComment );
		currentCommentText = new String();
		// events.raiseTransactionSuccessEvent("entityCommentsUpdated",
		// getClassName(getInstance()));
	}

	// @Observer("entityCommentsUpdated")
	public void loadComments() {
		comments = executeNamedQuery( "commentsForRecord", getEntity().getId(), getClassName( getEntity() ) );
	}

	protected String getClassName( T t ) {
		String name = t.getClass().getSimpleName();
		if ( name.indexOf( "$$" ) > 0 )
			name = name.substring( 0, name.indexOf( "$$" ) );
		return name;
	}

	protected String getClassName() {
		return getClassName( getEntity() );
	}

	// ///////////////// QUERIES //////////////////////////////////////////////////////////////////////////////////////////////////////////

	@SuppressWarnings( "unchecked" )
	public <S> List<S> executeQuery( String queryString, Object... params ) {
		Query query = entityManager.createQuery( queryString );
		setQueryParams( query, params );
		return query.getResultList();
	}

	@SuppressWarnings( "unchecked" )
	public <S> S executeSingleResultQuery( String queryString, Object... params ) {
		Query query = entityManager.createQuery( queryString );
		setQueryParams( query, params );
		return (S) executeSingleResultQuery( query );
	}

	@SuppressWarnings( "unchecked" )
	private <S> S executeSingleResultQuery( Query query ) {
		try {
			return (S) query.getSingleResult();
		} catch ( NoResultException nre ) {
			// TODO: logging
			// log.info("No " + "record " + " found !");
			return null;
		}
	}

	@SuppressWarnings( "unchecked" )
	public <S> List<S> executeNamedQuery( String queryString, Object... params ) {
		Query query = entityManager.createNamedQuery( queryString );
		setQueryParams( query, params );
		// setEntityList(query.getResultList());
		return query.getResultList();
	}

	@SuppressWarnings( "unchecked" )
	public <S> S executeSingleResultNamedQuery( String queryString, Object... params ) {
		Query query = entityManager.createNamedQuery( queryString );
		setQueryParams( query, params );
		return (S) executeSingleResultQuery( query );
	}

	@SuppressWarnings( "unchecked" )
	public <S> S executeSingleResultNativeQuery( String queryString, Object... params ) {
		Query query = entityManager.createNativeQuery( queryString );
		setQueryParams( query, params );
		return (S) query.getSingleResult();

	}

	@SuppressWarnings( "unchecked" )
	public <S> List<S> executeNativeQuery( String queryString, Object... params ) {
		Query query = entityManager.createNativeQuery( queryString );
		setQueryParams( query, params );
		return query.getResultList();
	}

	private void setQueryParams( Query query, Object... params ) {
		int counter = 1;
		for ( Object param : params ) {
			query.setParameter( counter++, param );
		}
	}

	// /////////////////////////////// Lazy data model ///////////////////////////////////////////////////////////////////////////////////

	private LazyDataModel<T> model;

	public LazyDataModel<T> getModel() {
		return model;
	}

	protected class EntityLazyDataModel extends LazyDataModel<T> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public List<T> load( int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters ) {

			setPageSize( pageSize );

			Session s = (Session) entityManager.getDelegate();

			Criteria crit = s.createCriteria( getEntityClass() );

			if ( sortField != null && !sortField.isEmpty() ) {
				if ( sortOrder == SortOrder.ASCENDING ) {
					crit = crit.addOrder( Order.asc( sortField ) );
				} else {
					crit = crit.addOrder( Order.desc( sortField ) );
				}
			}

			if ( !filters.isEmpty() ) {
				Iterator<Entry<String,String>> iterator = filters.entrySet().iterator();
				while ( iterator.hasNext() ) {
					Entry<String,String> entry = iterator.next();
					crit = crit.add( Restrictions.like( entry.getKey(), entry.getValue(), MatchMode.START ) );
				}
			}

			crit = crit.setFirstResult( first ).setMaxResults( pageSize );

			model.setRowCount( safeLongToInt( getCount() ) );

			return crit.list();
		}

		public Long getCount() {
			Session s = (Session) entityManager.getDelegate();

			Criteria crit = s.createCriteria( getEntityClass() ).setProjection( Projections.rowCount() );

			return (Long) crit.list().get( 0 );
		}

		@Override
		public Object getRowKey( T t ) {
			return t.getId();
		}

		@Override
		public T getRowData( String rowKey ) {
			if ( rowKey == null || rowKey.equals( "null" ) )
				return null;

			T t = entityManager.find( getEntityClass(), Long.valueOf( rowKey ) );
			return t;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.primefaces.model.LazyDataModel#setRowIndex(int) Override created to address the issue -
		 * http://code.google.com/p/primefaces/issues/detail?id=1544
		 */
		@Override
		public void setRowIndex( int rowIndex ) {
			/*
			 * The following is in ancestor (LazyDataModel): this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
			 */
			if ( rowIndex == -1 || getPageSize() == 0 ) {
				super.setRowIndex( -1 );
			} else
				super.setRowIndex( rowIndex % getPageSize() );
		}

		// TODO: should be moved to a utils class
		public int safeLongToInt( long l ) {
			if ( l < Integer.MIN_VALUE || l > Integer.MAX_VALUE ) {
				throw new IllegalArgumentException( l + " cannot be cast to int without changing its value." );
			}
			return (int) l;
		}

	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText( String searchText ) {
		this.searchText = searchText;
	}

	public List<TextSearchResultHolder> getSearchResultsList() {
		if ( searchResultsList.isEmpty() )
			textSearch();

		return searchResultsList;
	}

	public void setSearchResultsList( List<TextSearchResultHolder> searchResultsList ) {
		this.searchResultsList = searchResultsList;
	}

}
