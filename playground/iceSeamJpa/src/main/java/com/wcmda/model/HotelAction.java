package com.wcmda.model;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityHome;

import com.icesoft.faces.component.tree.IceUserObject;

@Name("hotelAction")
public class HotelAction extends EntityHome<Hotel> {

	// tree default model, used as a value for the tree component
	private DefaultTreeModel model;

	public HotelAction() {
		// create root node with its children expanded
		DefaultMutableTreeNode rootTreeNode = new DefaultMutableTreeNode();
		IceUserObject rootObject = new IceUserObject(rootTreeNode);
		rootObject.setText("Root Node");
		rootObject.setExpanded(true);
		rootTreeNode.setUserObject(rootObject);

		// model is accessed by by the ice:tree component
		model = new DefaultTreeModel(rootTreeNode);

		// add some child notes
		for (int i = 0; i < 3; i++) {
			DefaultMutableTreeNode branchNode = new DefaultMutableTreeNode();
			IceUserObject branchObject = new IceUserObject(branchNode);
			branchObject.setText("node-" + i);

			/*if (i == 0) {
				DefaultMutableTreeNode branchNode2 = new DefaultMutableTreeNode();
				IceUserObject branchObject2 = new IceUserObject(branchNode);
				branchObject2.setLeaf(true);
				branchObject.setText("node-1" + i);
				branchNode.add(branchNode2);
			}*/
			branchNode.setUserObject(branchObject);
			//if( i > 0 )
				branchObject.setLeaf(true);
			rootTreeNode.add(branchNode);
		}
	}

	/**
	 * Gets the tree's default model.
	 * 
	 * @return tree model.
	 */
	public DefaultTreeModel getModel() {
		return model;
	}

}
