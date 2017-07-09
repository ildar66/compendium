package ru.masterdm.compendium.custom;

import java.io.Serializable;

/**
 * Object-transport для описания "дерева Департаментов"
 * @author IShafigullin
 *
 */
public class DepartmentPar  implements Serializable {
	
	/**
	 *  Object-transport для описания "дерева Департаментов"
	 */
	private static final long serialVersionUID = 1L;

	public DepartmentPar(Integer parentID, Integer childID) {
		super();
		this.parentID = parentID;
		this.childID = childID;
	}

	private Integer parentID = null;
	private Integer childID = null;
	
	public Integer getParentID() {
		return parentID;
	}
	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}
	public Integer getChildID() {
		return childID;
	}
	public void setChildID(Integer childID) {
		this.childID = childID;
	}
	
	@Override
	public boolean equals(Object anObject) {
		if (anObject == null) {
			return false;
		}
		if (!(anObject instanceof DepartmentPar)) {
			return false;
		}
		DepartmentPar aDepartmentsPar = (DepartmentPar) anObject;
		return aDepartmentsPar.getChildID().equals(getChildID()) && aDepartmentsPar.getParentID().equals(getParentID());
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("DepartmentPar: childID=" + getChildID());
		sb.append(" parentID=" + getParentID());
		
		return sb.toString();
	}	
}
