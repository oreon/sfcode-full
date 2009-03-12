package com.wcmda.model;

import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.component.UIParameter;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.swing.tree.DefaultMutableTreeNode;

import java.util.*;
import com.icesoft.faces.component.selectinputtext.*;

import com.icesoft.faces.component.tree.IceUserObject;


public class MyIceUserObject extends IceUserObject {

    private boolean selected;
    private String inputText;
    private Date nodeDate = new Date();
    private boolean showDate = false;

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

    /**
     * Available options for the various selection components.
     */
    private static final SelectItem[] drinkItems = new SelectItem[] {
        new SelectItem("Coke"),
        new SelectItem("Pepsi"),
        new SelectItem("Sprite"),
        new SelectItem("7Up"),
    };

    private static final SelectItem[] componentTypeItems = new SelectItem[] {
        new SelectItem("I/O"),
        new SelectItem("Command"),
        new SelectItem("Selection"),
    };

    private static final SelectItem[] cityItems = new SelectItem[] {
            new SelectItem("Calgary"),
            new SelectItem("Vancouver"),
            new SelectItem("Toronto"),
            new SelectItem("Montreal"),
            new SelectItem("Ottawa"),
    };

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

    public void deleteMe(ActionEvent event) {
        ((DefaultMutableTreeNode)getWrapper().getParent()).remove(getWrapper());
    }

    // clones the clicked node and adds the clone as a child to the genetic original
    public void addMe(ActionEvent event) {
        DefaultMutableTreeNode clonedWrapper = clone(getWrapper());
        MyIceUserObject clonedUserObject = new MyIceUserObject(clonedWrapper);
        MyIceUserObject originalUserObject = (MyIceUserObject) getWrapper().getUserObject();
        clonedUserObject.setAction(originalUserObject.getAction());
        clonedUserObject.setBranchContractedIcon(originalUserObject.getBranchContractedIcon());
        clonedUserObject.setBranchExpandedIcon(originalUserObject.getBranchExpandedIcon());
        clonedUserObject.setExpanded(originalUserObject.isExpanded());
        clonedUserObject.setLeafIcon(originalUserObject.getLeafIcon());
        clonedUserObject.setText(originalUserObject.getText() + ".clone");
        clonedUserObject.setTooltip(originalUserObject.getTooltip());
        clonedWrapper.setUserObject(clonedUserObject);
        getWrapper().insert(clonedWrapper, 0);
    }

    private DefaultMutableTreeNode clone(DefaultMutableTreeNode wrapper) {
        DefaultMutableTreeNode clone = new DefaultMutableTreeNode();
        return clone;
    }
    public void clickMe(ActionEvent event) {

        String currentText = getText();
        String currentCount = null;
        if (currentText.indexOf("clicked") != -1) {
            currentCount = currentText.substring(currentText.indexOf("-") + 1);
        } else {
            currentCount = "0";
        }

        setText("clicked-" + new Integer(Integer.parseInt(currentCount) + 1));

        // toggle showDate
        setShowDate(!isShowDate());
    }

    public void testMe(ActionEvent event) {
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
    }

    public String testMe() {
        String currentText = getText();
        System.out.println("currentText::" + currentText);

        nodeDate = new Date();

        return "success";
    }

    public void buttonClick(ActionEvent event) {

        String currentText = getText();
        String currentCount = null;
        if (currentText.indexOf("clicked") != -1) {
            currentCount = currentText.substring(currentText.indexOf("-") + 1);
        } else {
            currentCount = "0";
        }

        setText("clicked-" + new Integer(Integer.parseInt(currentCount) + 1));
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

    public void nodeClick(ActionEvent event) {
        if (!this.isLeaf()) {
            // are we expanding or collapsing this node
            this.setExpanded(!this.isExpanded());
            boolean nodeState = isExpanded();
            System.out.println("nodeClick::expanded=" + nodeState);
        }
    }

    public void navigator(ActionEvent e) {
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
    }


    public Date getNodeDate() {
        return nodeDate;
    }

    public void setNodeDate(Date nodeDate) {
        this.nodeDate = nodeDate;
    }

    public SelectItem[] getDrinkItems() {
        return drinkItems;
    }

    public String getComponentType() {
        return componentType;
    }

    public void setComponentType(String componentType) {
        this.componentType = componentType;
    }

    public SelectItem[] getComponentTypeItems() {
        return componentTypeItems;
    }

    public SelectItem[] getCityItems() {
        return cityItems;
    }

    public String[] getCities() {
        return cities;
    }

    public void setCities(String[] cities) {
        this.cities = cities;
    }

    public String getCitiesString() {
        if (cities == null) return "";
        StringBuffer b = new StringBuffer();
        try {
            for (int i = 0; i < cities.length; i++) {
                b.append(cities[i]);
                if (i < cities.length - 1) b.append(',');
            }
            return b.toString();
        }
        catch (ArrayIndexOutOfBoundsException e) {
            return "";
        }
    }

    public String getComments() {
        return comments;
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

    // list of cities, used for auto complete list.
    private static List dictionary;

    static{
        // make sure we only load one copy of this dictionary as it is quite large.
        dictionary =  new AutoCompleteDictionary().getDictionary();
    }

    // default city, no value.
    private City currentCity = new City("","","","","","",0);

    // list of possible matches.
    private List matchesList = new ArrayList();

    /**
     * Called when a user has modifed the SelectInputText value.  This method
     * call causes the match list to be updates.
     * @param event
     */
    public void updateList(ValueChangeEvent event)  {

        // get a new list of matches.
        setMatches(event);

        // Get the auto complete component from teh event and assing
        if (event.getComponent() instanceof SelectInputText ) {
            SelectInputText autoComplete = (SelectInputText)event.getComponent();
            // if no seleted item then return the previously selected itm.
            if (autoComplete.getSelectedItem()!= null){
                currentCity = (City) autoComplete.getSelectedItem().getValue() ;
            }
            // otherwise if there is a selected item get the value from the match list
            else{
                City tempCity = getMatch(autoComplete.getValue().toString());
                if(tempCity != null){
                    currentCity = tempCity;
                }
            }
        }
    }

    /**
     * Gets teh currently slected city.
     * @return selected city.
     */
    public City getCurrentCity() {
        return currentCity;
    }

    /**
     * The the list of possible matches for the gien SelectInputText value
     * @return list of possible matches.
     */
    public List getList() {
        return matchesList;
    }

    private City getMatch(String value){
        City result = null;
        if (matchesList != null){
            SelectItem si;
            Iterator iter = matchesList.iterator();
            while(iter.hasNext()){
                si = (SelectItem)iter.next();
                if(value.equals(si.getLabel())){
                    result = (City)si.getValue();
                }
            }
        }
        return result;
    }

    /**
     * Utility method for building the match list given the current value
     * of the SelectInputText component.
     * @param event
     */
    private void setMatches(ValueChangeEvent event) {

        Object searchWord = event.getNewValue();
        int maxMatches = ((SelectInputText)event.getComponent()).getRows();
        List matchList = new ArrayList(maxMatches);

        try {

            int insert = Collections.binarySearch(dictionary, searchWord,
                    AutoCompleteDictionary.LABEL_COMPARATOR);

            // less then zero if wer have a partial match
            if (insert < 0){
                insert = Math.abs(insert) -1;
            }

            for (int i=0; i < maxMatches; i++)  {
                // quit the match list creation if the index is larger then
                // max entries in the dictionary of we have added maxMatches.
                if ((insert + i) >= dictionary.size() ||
                        i >= maxMatches){
                    break;
                }
                matchList.add( dictionary.get(insert + i));
            }
        } catch (Throwable e)  {

        }
        // assign new matchList
        if (this.matchesList != null){
            this.matchesList.clear();
            this.matchesList = null;
        }
        this.matchesList = matchList;
    }

}
