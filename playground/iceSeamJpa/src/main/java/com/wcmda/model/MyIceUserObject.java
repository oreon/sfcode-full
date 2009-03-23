package com.wcmda.model;

import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.swing.tree.DefaultMutableTreeNode;

import org.apache.log4j.Logger;



import java.util.*;
import com.icesoft.faces.component.selectinputtext.*;

import com.icesoft.faces.component.tree.IceUserObject;


public class MyIceUserObject extends IceUserObject {

    private boolean selected;
    private String inputText;
    private Date nodeDate = new Date();
    private boolean showDate = false;

private static Logger logger = Log4JUtils.getLogger();
	
    public String getDebugContext( String debugContext )
    {
        return this.getClass().getName() + "." + debugContext + " - ";
    }
    
    public boolean isShowDate() {
        return showDate;
    }

    public void setShowDate(boolean showDate) {
        this.showDate = showDate;
    }

    private String componentType = "I/O";

    private String[] cities;

    private String comments;

    private String password;
   private String drink;

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public MyIceUserObject(DefaultMutableTreeNode wrapper) {
        super(wrapper);
    }


    private DefaultMutableTreeNode clone(DefaultMutableTreeNode wrapper) {
        DefaultMutableTreeNode clone = new DefaultMutableTreeNode();
        return clone;
    }
    public void clickMe(ActionEvent event) {
    	String debugContext = getDebugContext("clickMe");
		logger.info( debugContext + "start"); 
    	
        String currentText = getText();
        
		logger.info( debugContext + " - text- " + currentText);
        String currentCount = null;
        if (currentText.indexOf("clicked") != -1) {
            currentCount = currentText.substring(currentText.indexOf("-") + 1);
        } else {
            currentCount = "0";
        }

        setText("clicked-" + new Integer(Integer.parseInt(currentCount) + 1));

        // toggle showDate
        setShowDate(!isShowDate());
        
        logger.info( debugContext + "END"); 
    }

    public void testMe(ActionEvent event) {
     
    	String debugContext = getDebugContext("testMe-ActionEvent");
		logger.info( debugContext + "start"); 

    	String currentText = getText();
        System.out.println("currentText::" + currentText);

        List kids = event.getComponent().getChildren();
        for (int i =0; i < kids.size();i++) {
            UIComponent kid = (UIComponent)kids.get(i);
            System.out.println("kid::" + i + "::" + kid.toString());
            if (kid instanceof UIParameter) {
                Object param = ((UIParameter)kid).getValue();
                System.out.println("param::" + param.getClass());
                System.out.println("param::value::" + param.toString());
            }
        }

        nodeDate = new Date();
        
        logger.info( debugContext + "END"); 
    }

       public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

    public void checkChange( ValueChangeEvent event ){
        // this event is tied to a checkbox
        // the checkbox is contained in the content facet of a treeNode
        System.out.println( "checkChange::nodeId=" + getText()
                + " [selected=" + event.getNewValue() + "]");
    }

        public void navigator(ActionEvent e) {
    	
    	String debugContext = getDebugContext("Navigator");
		logger.info( debugContext + "start"); 
        String EXPAND = "expand";

        DefaultMutableTreeNode node = this.wrapper;

        if (!this.isExpanded()) {
            String eventType = EXPAND;

            System.out.println("navigating [" + node.toString() + " : " + eventType + "]" );

            // if the navigated node does not have children
            // we will load the navigated nodes children now.
            if (!(node.getChildCount() > 0)) {

                for (int i=0;i<8;i++) {
                    DefaultMutableTreeNode childNode = new DefaultMutableTreeNode();
                    MyIceUserObject childObject = new MyIceUserObject(childNode);
                    childNode.setUserObject(childObject);
                    childObject.setLeafIcon("./img/td.gif");
                    childObject.setBranchContractedIcon("./img/tfc.gif");
                    childObject.setBranchExpandedIcon("./img/tfo.gif");
                    childObject.setExpanded(false);
                    childObject.setTooltip("sbbtip " + i);
                    childObject.setText(this.getText() +"." + i);
                    node.add(childNode);
                }
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode();
                MyIceUserObject childObject = new MyIceUserObject(childNode);
                childNode.setUserObject(childObject);
                childObject.setLeafIcon("./img/td.gif");
                childObject.setBranchContractedIcon("./img/tfc.gif");
                childObject.setBranchExpandedIcon("./img/tfo.gif");
                childObject.setExpanded(false);
                childObject.setLeaf(true);
                childObject.setTooltip("child leaf");
                childObject.setText(this.getText() +".childleaf");
                node.add(childNode);
            }
        }
        // toggle expaned attribute
        this.expanded = (!this.expanded);
        
        logger.info( debugContext + "END"); 
    }


    public Date getNodeDate() {
        return nodeDate;
    }

    public void setNodeDate(Date nodeDate) {
        this.nodeDate = nodeDate;
    }

    

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }


    

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

   
}
