/*
 * LineItem.java
 *
 * Created on March 14, 2006, 12:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */


import java.math.BigDecimal;

/**
 *
 * @author basler
 */
public class LineItem {
    
    private String target="", business="", itemName="", type="Donation";
    private BigDecimal amount=new BigDecimal(0);
    private int quantity=1;
    
    /** Creates a new instance of LineItem */
    public LineItem() {
    }
    
    public LineItem(String business, String itemName, int quantity, BigDecimal amount, String type, String target) {
        this.business=business;
        this.target=target;
        this.itemName=itemName;
        this.type=type;
        this.amount=amount;
        this.quantity=quantity;
    }
    
    
    public void setTarget(String target) {
        this.target=target;
    }
    public String getTarget() {
        return target;
    }
    
    public void setBusiness(String business) {
        this.business=business;
    }
    public String getBusiness() {
        return business;
    }

    public void setItemName(String itemName) {
        this.itemName=itemName;
    }
    public String getItemName() {
        return itemName;
    }

    public void setType(String type) {
        this.type=type;
    }
    public String getType() {
        return type;
    }
    
    public void setAmount(BigDecimal amount) {
        this.amount=amount;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    
    public void setQuantity(int quantity) {
        this.quantity=quantity;
    }
    public int getQuantity() {
        return quantity;
    }
    
}
