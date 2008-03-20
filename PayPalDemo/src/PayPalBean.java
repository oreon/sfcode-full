/*
 * PayPalBean.java
 *
 * Created on March 14, 2006, 7:50 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */


import java.util.ArrayList;
import java.math.BigDecimal;

/**
 *
 * @author basler
 */
public class PayPalBean {
    
    BuyNowPostData postData=null;
    ArrayList<LineItem> arLineItems=new ArrayList();
    
    /** Creates a new instance of PayPalBean */
    public PayPalBean() {
        postData=new BuyNowPostData();
        postData.setShippingCost(new BigDecimal("100"));
        postData.setTax(new BigDecimal("200"));
        postData.setUndefinedQuantity("1");
        postData.setSubmissionMethod("GET");
        
        arLineItems.add(new LineItem("donate@animalfoundation.com", "test item name1", 1, new BigDecimal("10.00"), "Donation", "PayPal"));
        arLineItems.add(new LineItem("donate@animalfoundation.com", "test item name2", 1, new BigDecimal("20.00"), "Donation", "PayPal"));
        arLineItems.add(new LineItem("donate@animalfoundation.com", "test item name3", 1, new BigDecimal("30.00"), "Donation", "PayPal"));
        arLineItems.add(new LineItem("donate@animalfoundation.com", "test item name4", 1, new BigDecimal("40.00"), "Donation", "PayPal"));
        arLineItems.add(new LineItem("donate@animalfoundation.com", "test item name5", 1, new BigDecimal("50.00"), "Donation", "PayPal"));
    }
    
    public BuyNowPostData getPostData() {
        return postData;
    }
    
    public ArrayList getLineItems() {
        return arLineItems;
    }
    
    
}
