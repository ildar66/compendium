/**
 * 
 */
package ru.masterdm.compendium.domain;

/**
 * VtbObject "���� ������������� �������"
 * @author IShafigullin
 *
 */
public class Role extends VtbObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id = null; //��� "���� ���������(������������)"
	private String name = null; //��� "���� ���������(������������)"
	private Integer processTypeID = null; //id �������� 

	@Override
	public boolean equals(Object anObject) {
		if (anObject == null) {
			return false;
		}
		if (!(anObject instanceof Role)) {
			return false;
		}
		Role aRole = (Role) anObject;
		return aRole.getId().intValue() == getId().intValue();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("Role: ");
		sb.append(getId() + "(" + getName() + ")");
		//sb.append('\n');
		//sb.append(" IsActive: ");
		//sb.append(getIsActive());

		return sb.toString();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Role(Integer aId, String aName) {
		setId(aId);
		setName(aName);
	}
	
	public Role(Integer aId) {
		setId(aId);
		setName("none");
	}

	public Integer getProcessTypeID() {
		return processTypeID;
	}

	public void setProcessTypeID(Integer processTypeID) {
		this.processTypeID = processTypeID;
	}	

}
