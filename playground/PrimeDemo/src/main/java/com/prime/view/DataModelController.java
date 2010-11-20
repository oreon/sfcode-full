/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.prime.view;

import com.prime.domain.Customer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author goutham
 */
@ManagedBean
@ViewScoped
public class DataModelController {

    private final static Logger logger = Logger.getLogger(DataModelController.class.getName());
    private LazyDataModel<Customer> customerModel;
    
    public DataModelController() {
    
        customerModel= new LazyDataModel<Customer>() {

			/**
			 * Dummy implementation of loading a certain segment of data.
			 * In a real application, this method should load data from a datasource
			 */
			@Override
			public List<Customer> load(int first, int pageSize, String sortField, boolean sortOrder, Map<String,String> filters) {
				logger.info("Loading the lazy car data between"+ first+" and "+ (first+pageSize));

                //Sorting and Filtering information are not used for demo purposes just random dummy data is returned
				
				List<Customer> lazyCars = new ArrayList<Customer>();

				return lazyCars;
			}
		};

        /**
         * In a real application, this number should be resolved by a projection query
         */
        customerModel.setRowCount(100000000);
	}
    }


