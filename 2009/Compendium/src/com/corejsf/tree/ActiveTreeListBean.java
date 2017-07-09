package com.corejsf.tree;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;

import org.apache.myfaces.custom.tree2.HtmlTree;
import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeModelBase;
import org.apache.myfaces.custom.tree2.TreeNode;

import com.corejsf.ActiveBean;

/**
 * @author IShafigullin
 *
 */
public abstract class ActiveTreeListBean extends ActiveBean{

	private HtmlTree _tree;
	private String _nodePath;

	/**
	 * NOTE: This is just to show an alternative way of supplying tree data. You
	 * can supply either a TreeModel or TreeNode.
	 * 
	 * @return TreeModel
	 */
	public TreeModel getExpandedTreeData() {
		return new TreeModelBase(getTreeData());
	}
	
	public abstract TreeNode getTreeData();

	public void setTree(HtmlTree tree) {
		_tree = tree;
	}

	public HtmlTree getTree() {
		return _tree;
	}

	public String expandAll() {
		_tree.expandAll();
		return null;
	}

	public void setNodePath(String nodePath) {
		_nodePath = nodePath;
	}

	public String getNodePath() {
		return _nodePath;
	}

	public void checkPath(FacesContext context, UIComponent component, java.lang.Object value) {
		// make sure path is valid (leaves cannot be expanded or renderer will
		// complain)
		FacesMessage message = null;
	
		String[] path = _tree.getPathInformation(value.toString());
	
		for (int i = 0; i < path.length; i++) {
			String nodeId = path[i];
			try {
				_tree.setNodeId(nodeId);
			} catch (Exception e) {
				throw new ValidatorException(message, e);
			}
	
			if (_tree.getNode().isLeaf()) {
				message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						"Invalid node path (cannot expand a leaf): " + nodeId,
						"Invalid node path (cannot expand a leaf): " + nodeId);
				throw new ValidatorException(message);
			}
		}
	}

	public void expandPath(ActionEvent event) {
		_tree.expandPath(_tree.getPathInformation(_nodePath));
	}

}
