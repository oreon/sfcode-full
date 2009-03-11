package com.wcmda.model;

import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.framework.EntityHome;

import com.icesoft.faces.component.tree.IceUserObject;
import com.icesoft.faces.component.tree.Tree;
import com.icesoft.faces.component.tree.TreeNode;

@Name("hotelAction")
@Scope(ScopeType.SESSION)
public class HotelAction extends EntityHome<Hotel> {

	// tree default model, used as a value for the tree component
//	private DefaultTreeModel model;
	private Tree tree;
	private DefaultTreeModel model;
	
	
	public HotelAction() {
		 DefaultMutableTreeNode rootTreeNode = new DefaultMutableTreeNode();
		    MyIceUserObject rootObject = new MyIceUserObject(rootTreeNode);
		    rootObject.setText("root");
			rootObject.setTooltip("root node tooltip");
			rootObject.setExpanded(true);
			rootTreeNode.setUserObject(rootObject);
			
			model =  new DefaultTreeModel(rootTreeNode);
			
			boolean branchExpanded = true;
			
			for (int i = 0; i < 3; i++) {
			  //  branchExpanded = !branchExpanded ;
			    DefaultMutableTreeNode branchNode = new DefaultMutableTreeNode();
			    MyIceUserObject branchObject = new MyIceUserObject(branchNode);
			    branchNode.setUserObject(branchObject);
			    branchObject.setBranchContractedIcon("./img/tfc.gif");
			    branchObject.setBranchExpandedIcon("./img/tfo.gif");
			    branchObject.setExpanded(false);
			    branchObject.setTooltip("b-tip " + i);
			    branchObject.setText("b-" + i);
			    rootTreeNode.add(branchNode);		
		    }
		
		}
		

		
		public DefaultTreeModel getModel() {
		    return model;
		}
		public void setModel(DefaultTreeModel treeModel) {
		    this.model = treeModel;
		}
		public Tree getTree() {
		    return tree;
		}
		public void setTree(Tree treeComponent) {
		    this.tree = treeComponent;
		}
		    
		public void navigator(ActionEvent e) {    	
			String EXPAND = "expand";
		
			String eventType = getTree().getNavigationEventType();
			DefaultMutableTreeNode navNode = getTree().getNavigatedNode();
		
			MyIceUserObject userObject = (MyIceUserObject)navNode.getUserObject();
		
			System.out.println("navigating [" + navNode.toString() + " : " + eventType + "]" );
		
			if (eventType.equalsIgnoreCase(EXPAND)) { // then load the next level of nodes
				// if the navigated node does not have children
				// we will load the navigated nodes children now.
				if (!(navNode.getChildCount() > 0)) {		                
		
					for (int i=0;i<2;i++) {
			            DefaultMutableTreeNode childNode = new DefaultMutableTreeNode();
			            MyIceUserObject childObject = new MyIceUserObject(childNode);
			            childNode.setUserObject(childObject);
			            childObject.setLeafIcon("./img/td.gif");
			            childObject.setBranchContractedIcon("./img/tfc.gif");
			            childObject.setBranchExpandedIcon("./img/tfo.gif");
			            childObject.setExpanded(false);
			            childObject.setTooltip("sbbtip " + i);
			            childObject.setText(userObject.getText() +"." + i);
			            navNode.add(childNode);	                    
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
		            childObject.setText(userObject.getText() +".childleaf");
		            navNode.add(childNode);	                    				
		        }
		    }
		}
	
		
}
