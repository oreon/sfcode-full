/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.prime.view;

import com.prime.domain.Customer;
import com.prime.session.CustomerFacade;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.log4j.Logger;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author goutham
 */
@ManagedBean(name="dataModelBean")
@ViewScoped
public class DataModelController {

    private final static Logger logger = Logger.getLogger(DataModelController.class.getName());
    private LazyDataModel<Customer> customerModel;
      @EJB private CustomerFacade ejbFacade;
      private Customer selectedCustomer;

        private CustomerFacade getFacade() {
        return ejbFacade;
    }

    public DataModelController() {
 
	}
    @PostConstruct
    private void init()
    {
        logger.info("Start");
        customerModel= new LazyDataModel<Customer>() {

			/**
			 * Dummy implementation of loading a certain segment of data.
			 * In a real application, this method should load data from a datasource
			 */
            @Override
            public List<Customer> load(int first, int pageSize, String sortField, boolean sortOrder, Map<String, String> filters) {
                logger.info("Loading the lazy car data between" + first + " and " + (first + pageSize));

                //Sorting and Filtering information are not used for demo purposes just random dummy data is returned

                //	List<Customer> lazyCustomerList = new ArrayList<Customer>();


                List<Customer> lazyCustomerList = getFacade().findRange(new int[]{first, first + pageSize});
                if (lazyCustomerList != null) {
                    logger.info("Lazycustomer found " + lazyCustomerList.size());
                }else{logger.info("Lazycustomer is null");}
                if(lazyCustomerList==null)
                {
                   lazyCustomerList= getFacade().findRange(new int[]{1,6 });
                }
                return lazyCustomerList;
            }
        };

        /**
         * In a real application, this number should be resolved by a projection query
         */

        customerModel.setRowCount(getFacade().findAll().size());
    }

    public LazyDataModel<Customer> getCustomerModel() {
        return customerModel;
    }

    public void setCustomerModel(LazyDataModel<Customer> customerModel) {
        this.customerModel = customerModel;
    }

    public Customer getSelectedCustomer() {
        return selectedCustomer;
    }

    public void setSelectedCustomer(Customer selectedCustomer) {
        this.selectedCustomer = selectedCustomer;
    }
    
    }


