package com.wc.shopper.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.primefaces.model.LazyDataModel;

import com.wc.shopper.domain.Car;

/**
 * Backing bean for Car entities.
 * <p>
 * This class provides CRUD functionality for all Car entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD
 * framework or custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class CarBean implements Serializable   {

	private static final long serialVersionUID = 1L;

	private static String[] colors;

	private static String[] manufacturers;

	
	private CarDataModel carDataModel;

	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Car Car;

	public Car getCar() {
		return this.Car;
	}

	@Inject
	private Conversation conversation;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public String create() {

		this.conversation.begin();
		return "create?faces-redirect=true";
	}

	public void retrieve() {

		if (FacesContext.getCurrentInstance().isPostback()) {
			return;
		}

		if (this.conversation.isTransient()) {
			this.conversation.begin();
		}

		if (this.id == null) {
			this.Car = this.search;
		} else {
			this.Car = this.entityManager.find(Car.class, getId());
		}
	}

	/*
	 * Support updating and deleting Car entities
	 */

	public String update() {
		if(!conversation.isTransient())
			this.conversation.end();
		
		if(this.Car == null)
			this.Car = selectedCar;

		try {
			if (this.id == null) {
				
				this.entityManager.persist(this.Car);
				return "search?faces-redirect=true";
			} else {
				this.entityManager.merge(this.Car);
				return "view?faces-redirect=true&id=" + this.Car.getId();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			return null;
		}
	}

	public String delete() {
		this.conversation.end();

		try {
			this.entityManager.remove(this.entityManager.find(Car.class,
					getId()));
			this.entityManager.flush();
			return "search?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			return null;
		}
	}

	/*
	 * Support searching Car entities with pagination
	 */

	private int page;
	private long count;
	private List<Car> pageItems;

	private Car search = new Car();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public Car getSearch() {
		return this.search;
	}

	public void setSearch(Car search) {
		this.search = search;
	}

	public void search() {
		this.page = 0;
	}

	public void paginate() {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

		// Populate this.count

		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		Root<Car> root = countCriteria.from(Car.class);
		countCriteria = countCriteria.select(builder.count(root)).where(
				getSearchPredicates(root));
		this.count = this.entityManager.createQuery(countCriteria)
				.getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<Car> criteria = builder.createQuery(Car.class);
		root = criteria.from(Car.class);
		TypedQuery<Car> query = this.entityManager.createQuery(criteria.select(
				root).where(getSearchPredicates(root)));
		query.setFirstResult(this.page * getPageSize()).setMaxResults(
				getPageSize());
		this.pageItems = query.getResultList();
		
		//lazyModel = null;
		
		if(lazyModel == null ){
			LazyCarDataModel lazyCarDataModel =   new LazyCarDataModel(cars);
			lazyCarDataModel.setEntityManager(entityManager);
			lazyCarDataModel.initLoad();
			lazyModel = lazyCarDataModel;
		}

	}

	private void createCars() {

	}

	private Predicate[] getSearchPredicates(Root<Car> root) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		// String firstModel = this.search.getModel();
		// if (firstModel != null && !"".equals(firstModel)) {
		// predicatesList.add(builder.like(root.<String> get("firstModel"),
		// '%' + firstModel + '%'));
		// }
		// String lastModel = this.search.getLastModel();
		// if (lastModel != null && !"".equals(lastModel)) {
		// predicatesList.add(builder.like(root.<String> get("lastModel"),
		// '%' + lastModel + '%'));
		// }

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	public List<Car> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back Car entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

	public List<Car> getAll() {

		CriteriaQuery<Car> criteria = this.entityManager.getCriteriaBuilder()
				.createQuery(Car.class);
		return this.entityManager.createQuery(
				criteria.select(criteria.from(Car.class))).getResultList();
	}

	public Converter getConverter() {

		return new Converter() {

			@Override
			public Object getAsObject(FacesContext context,
					UIComponent component, String value) {

				return CarBean.this.entityManager.find(Car.class,
						Long.valueOf(value));
			}

			@Override
			public String getAsString(FacesContext context,
					UIComponent component, Object value) {

				if (value == null) {
					return "";
				}

				return String.valueOf(((Car) value).getId());
			}
		};
	}

	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */

	private Car add = new Car();

	public Car getAdd() {
		return this.add;
	}

	public Car getAdded() {
		Car added = this.add;
		this.add = new Car();
		return added;
	}

	public CarBean() {
		//populateRandomCars(cars, 50);
		
		//lazyModel.set
	}

	public Car getSelectedCar() {
		return selectedCar;
	}

	public void setSelectedCar(Car selectedCar) {
		this.selectedCar = selectedCar;
	}

	public LazyDataModel<Car> getLazyModel() {
		
		return lazyModel;
	}

	private LazyDataModel<Car> lazyModel;

	private Car selectedCar;

	private List<Car> cars;

	static {
		colors = new String[10];
		colors[0] = "Black";
		colors[1] = "White";
		colors[2] = "Green";
		colors[3] = "Red";
		colors[4] = "Blue";
		colors[5] = "Orange";
		colors[6] = "Silver";
		colors[7] = "Yellow";
		colors[8] = "Brown";
		colors[9] = "Maroon";

		manufacturers = new String[10];
		manufacturers[0] = "Mercedes";
		manufacturers[1] = "BMW";
		manufacturers[2] = "Volvo";
		manufacturers[3] = "Audi";
		manufacturers[4] = "Renault";
		manufacturers[5] = "Opel";
		manufacturers[6] = "Volkswagen";
		manufacturers[7] = "Chrysler";
		manufacturers[8] = "Ferrari";
		manufacturers[9] = "Ford";
	}

	public void populateRandomCars(int size) {
		for (int i = 0; i < size; i++) {
			Car car = new Car(getRandomModel(), getRandomYear(),
					getRandomManufacturer(), getRandomColor());
			// list.add();
			entityManager.persist(car);
		}
	}

	private String getRandomModel() {
		// TODO Auto-generated method stub
		return "USAS" + (int) (Math.random() * 50 + 1960);
	}

	private String getRandomColor() {
		return colors[(int) (Math.random() * 10)];
	}

	private String getRandomManufacturer() {
		return manufacturers[(int) (Math.random() * 10)];
	}

	private int getRandomYear() {
		return (int) (Math.random() * 50 + 1960);
	}

	public CarDataModel getCarDataModel() {
		if(carDataModel == null){
			carDataModel = new CarDataModel(pageItems);
			carDataModel.setCarBean(this);
		}
		return carDataModel;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void setCarDataModel(CarDataModel carDataModel) {
		this.carDataModel = carDataModel;
	}

}