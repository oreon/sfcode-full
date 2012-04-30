package com.wc.shopper.view;

import java.util.List;

import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.persistence.Query;

import org.primefaces.model.SelectableDataModel;

import com.wc.shopper.domain.Car;

public class CarDataModel extends ListDataModel<Car> implements SelectableDataModel<Car> {  
	
	@Inject
	private CarBean carBean;

    public CarDataModel() {
    }

    public CarDataModel(List<Car> data) {
        super(data);
    }
    
    @Override
    public Car getRowData(String rowKey) {
        //In a real app, a more efficient way like a query by rowKey should be implemented to deal with huge data
        
    	String qry = "Select c from Car c where c.model = ?";
    	
    	Query query = carBean.getEntityManager().createQuery(qry);
    	
    	Car car = (Car) query.setParameter(1, rowKey).getResultList().get(0);
    	
    	carBean.setSelectedCar(car);
    	
    	return car;
    	
    	
    	/*
        List<Car> cars = (List<Car>) getWrappedData();
        
        for(Car car : cars) {
            if(car.getModel().equals(rowKey))
                return car;
        }*/
        
     //   return null;
    }

    @Override
    public Object getRowKey(Car car) {
        return car.getModel();
    }

	public CarBean getCarBean() {
		return carBean;
	}

	public void setCarBean(CarBean carBean) {
		this.carBean = carBean;
	}
}
        