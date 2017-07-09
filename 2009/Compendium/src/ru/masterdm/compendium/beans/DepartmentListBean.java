package ru.masterdm.compendium.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;

import org.apache.myfaces.custom.tree2.TreeModel;
import org.apache.myfaces.custom.tree2.TreeNode;
import org.apache.myfaces.custom.tree2.TreeNodeBase;

import ru.masterdm.compendium.custom.DepartmentPar;
import ru.masterdm.compendium.custom.DepartmentTO;
import ru.masterdm.compendium.domain.Department;
import ru.masterdm.compendium.exception.ModelException;
import ru.masterdm.compendium.model.ActionProcessorFactory;
import ru.masterdm.compendium.model.CompendiumActionProcessor;

import com.corejsf.tree.ActiveTreeListBean;

/**
 * 
 * @author IShafigullin
 */
public class DepartmentListBean extends ActiveTreeListBean implements
		Serializable {

	public DepartmentListBean() {
		super();
		clearAction();
	}
	
	// Get actionProcess (model) and delegate
	private CompendiumActionProcessor getProcessor() {
		CompendiumActionProcessor processor = (CompendiumActionProcessor) ActionProcessorFactory
				.getActionProcessor("Compendium");
		return processor;
	}	

	/**
	 * serial id for serialisation versioning
	 */
	private static final long serialVersionUID = 1L;
	private SelectItem[] _allDepItems = null;
	private static final int ROOT_KEY = -1;
	private static final String ROOT_NAME = "Список департаментов.";
	private int _curDepParent = ROOT_KEY;
	private String _curDepParentName = ROOT_NAME;
	private Department _curDocument = null;
	private DepartmentPar[] _listDepPar = null;
	private static final Logger LOGGER = Logger
			.getLogger(DepartmentListBean.class.getName());

	public int getROOT_KEY() {
		return ROOT_KEY;
	}
	
	public String getROOT_NAME() {
		return ROOT_NAME;
	}	

	public long getCurDepParent() {
		return _curDepParent;
	}

	public void setCurDepParent(int depParent) {
		_curDepParent = depParent;
	}
	
	public String getCurDepParentName() {
		return _curDepParentName;
	}

	public void setCurDepParentName(String depParentName) {
		_curDepParentName = depParentName;
	}	

	public SelectItem[] getAllDepItems() {
		return _allDepItems;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.corejsf.tree.TreeListBean#getTreeData()
	 */
	@SuppressWarnings("unchecked")
	public TreeNode getTreeData() {
		TreeNode treeData = new TreeNodeBase("general-folder",
				ROOT_NAME, String.valueOf(ROOT_KEY) ,false);
		try {
			// список департаментов:
			Department[] listDep = getProcessor().getDepartmentListAll();
			HashMap<String, TreeNodeBase> mapDep = new HashMap<String, TreeNodeBase>(
					listDep.length);
			_allDepItems = new SelectItem[listDep.length];
			for (int i = 0; i < listDep.length; i++) {
				mapDep.put("" + listDep[i].getId(), new TreeNodeBase(
						"centralDepartment", listDep[i].getFullName(), ""
								+ listDep[i].getId(), false));
				_allDepItems[i] = new SelectItem(listDep[i].getId(),
						listDep[i].getFullName());
			}
			// список связей департаментов:
			_listDepPar = getProcessor().getDepartmentParAll();

			// логика построения иерархии:
			for (int i = 0; i < _listDepPar.length; i++) {
				DepartmentPar curPar = _listDepPar[i];
				TreeNodeBase curParent = mapDep.get(""
						+ curPar.getParentID());
				TreeNodeBase curChild = mapDep.get(""
						+ curPar.getChildID());
				curChild.setType("childDepartmentNode");
				if (curParent == null || curChild == null)
					continue;
				curParent.getChildren().add(curChild);
			}
			// включаем в корень дерева только головные организации:
			Set<String> setDepKeys = mapDep.keySet();
			Iterator<String> iter = setDepKeys.iterator();
			while (iter.hasNext()) {
				String key = iter.next();
				TreeNodeBase curNode = mapDep.get(key);
				if (curNode.getType().equals("centralDepartment")) {
					treeData.getChildren().add(curNode);
				}
			}
		
		} catch (ModelException e) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "ModelException:" + e, e.getMessage()), e);
		}
		return treeData;
	}

	public Department getCurDocument() {
		return _curDocument;
	}

	public void setCurDocument(Department curDocument) {
		LOGGER.info("setCurDocument:" + curDocument);
		_curDocument = curDocument;
	}

	public String setDocNum() {
		clearAction();
		String docNum = (String) request().getParameter("docNum");
		try {
			_curDocument = getProcessor().getDepartment(new Department(Integer.valueOf(docNum)));
			LOGGER.info("set curDocument:" + getCurDocument());
			// находим родителя:
			for (int i = 0; i < _listDepPar.length; i++) {
				DepartmentPar curPar = _listDepPar[i];
				if (curPar.getChildID().intValue() == Integer.valueOf(docNum).intValue()) {
					setCurDepParent(curPar.getParentID());
				}
			}
			//находим имя родителя:
			for (int j = 0; j < _allDepItems.length; j++) {
				SelectItem item = _allDepItems[j];
				if(getCurDepParent() == ((Integer)item.getValue()).longValue()){
					setCurDepParentName(item.getLabel());
					LOGGER.info("set curDepParent:" + getCurDepParent() + ", " + getCurDepParentName());
					break;
				}
			}
			setAction(ACTION_EDIT);
		} catch (NumberFormatException e) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "NumberFormatException:" + e, e.getMessage()), e);
		} catch (ModelException e) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "ModelException:" + e, e.getMessage()), e);
		}
		return null;
	}

	private HttpServletRequest request() {
		return (HttpServletRequest) FacesContext.getCurrentInstance()
				.getExternalContext().getRequest();
	}

	private String getElementById(String name) {
		String prefix = "compendium:section:content:departmentListForm:";
		return request().getParameter(prefix + name);
	}

	public String saveAction() {
		Integer curDepParent = populateCurDocument();
		LOGGER.info("saveAction row:" + getCurDocument() + "; parentID="
				+ getElementById("curDepParent"));
		try {
			getProcessor().updateDepartment(new DepartmentTO(curDepParent, getCurDocument()));
		} catch (ModelException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
		clearAction();
		return null;

	}

	/**
	 * @return
	 */
	private Integer populateCurDocument() {
		String fullName = getElementById("fullName");
		String shortName = getElementById("shortName");
		//String isCc = getElementById("isCc");
		String cbCdStr = getElementById("cbCd");
		Integer cbCd = null;
		if (cbCdStr != null && !"".equals(cbCdStr)) {
			cbCd = new Integer(cbCdStr);
		}
		String idCommitteeTypeStr = getElementById("idCommitteeType");
		BigDecimal idCommitteeType = null;
		if (idCommitteeTypeStr != null && !"".equals(idCommitteeTypeStr)) {
			idCommitteeType = new BigDecimal(idCommitteeTypeStr);
		}		
		Integer curDepParent = Integer.valueOf(getElementById("curDepParent"));
		getCurDocument().setFullName(fullName);
		getCurDocument().setShortName(shortName);
		getCurDocument().setCode(cbCd);
		getCurDocument().setCrCoType(idCommitteeType);
		return curDepParent;
	}

	public String deleteAction() {
		LOGGER.info("deleteAction row:" + getCurDocument());
		try {
			getProcessor().removeDepartment(getCurDocument());
		} catch (ModelException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
		clearAction();
		clearSelectedNode();
		return null;
	}

	public String canselAction() {
		clearAction();
		clearSelectedNode();
		return null;
	}

	public String createAction() {
		Integer curDepParent = populateCurDocument();
		LOGGER.info("createAction row:" + getCurDocument() + "; parentID="
				+ curDepParent);
		Integer idDepartment = Integer.valueOf(getElementById("idDepartment"));
		getCurDocument().setId(idDepartment);
		try {
			getProcessor().addDepartment(new DepartmentTO(curDepParent, getCurDocument()));
		} catch (ModelException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			return null;
		}
		clearAction();
		clearSelectedNode();
		return null;
	}

	private void clearAction() {
		setCurDocument(new Department(null));
		setAction(ACTION_ADD);
		setCurDepParent(ROOT_KEY);
		setCurDepParentName(ROOT_NAME);
	}

	private void clearSelectedNode() {
		setSelectedNode("0");
	}

	private void setSelectedNode(String pathSelectedNode) {
		if (getTree() != null) {
			getTree().setNodeId(pathSelectedNode);
			getTree().getDataModel().getTreeState().setSelected(
					pathSelectedNode);
		}
	}

	public String searchByNodeName() {
		clearSelectedNode();
		clearAction();		
		String searchName = getNodePath();
		TreeModel model = getTree().getDataModel();
		String aRootNodeId = model.getTreeWalker().getRootNodeId();
		TreeNode rootNode = model.getNodeById(aRootNodeId);
		searchInNode(searchName, aRootNodeId, rootNode);
		return null;
	}

	/**
	 * @param searchName
	 * @param aRootNodeId
	 * @param node
	 */
	@SuppressWarnings("unchecked")
	private void searchInNode(String searchName, String pathToRootNodeId,
			TreeNode node) {
		List<TreeNode> listChildNodes = node.getChildren();
		int i = 0;
		for (Iterator<TreeNode> iterator = listChildNodes.iterator(); iterator.hasNext();) {
			TreeNode childNode = (TreeNode) iterator.next();
			String pathSelectedNode = pathToRootNodeId + ":" + i;
			if (childNode.getDescription().startsWith(searchName)) {
				getTree().expandPath(getTree().getPathInformation(pathToRootNodeId));
				setSelectedNode(pathSelectedNode);
				break;
			}
			searchInNode(searchName, pathSelectedNode, childNode);
			i++;
		}
	}
}
