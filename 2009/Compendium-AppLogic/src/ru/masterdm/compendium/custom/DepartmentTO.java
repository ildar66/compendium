/**
 * 
 */
package ru.masterdm.compendium.custom;

import java.io.Serializable;

import ru.masterdm.compendium.domain.Department;
import ru.masterdm.compendium.domain.VtbObject;

/**
 * Object-transport "Департаменты"
 * @author IShafigullin
 * 
 */
public class DepartmentTO  extends VtbObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer parentID = null;//код родительского филиала.	
	
	/**
	 * @param parentID
	 * @param vo
	 */
	public DepartmentTO(Integer parentID, Department vo) {
		super();
		this.parentID = parentID;
		this.vo = vo;
	}

	@Override
	public boolean equals(Object anObject) {
		if (anObject == null) {
			return false;
		}
		if (!(anObject instanceof DepartmentTO)) {
			return false;
		}
		DepartmentTO aDepartmentTO = (DepartmentTO) anObject;
		return aDepartmentTO.getVo().equals(getVo()) && aDepartmentTO.getParentID().equals(getParentID());
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("DepartmentTO: " + getVo());
		sb.append(" parentID=" + getParentID());
		
		return sb.toString();
	}

	private Department vo = null;

	public Department getVo() {
		return vo;
	}

	public void setVo(Department vo) {
		this.vo = vo;
	}

	public Integer getParentID() {
		return parentID;
	}

	public void setParentID(Integer parentID) {
		this.parentID = parentID;
	}

}
