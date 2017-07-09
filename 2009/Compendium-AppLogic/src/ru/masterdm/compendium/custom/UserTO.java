package ru.masterdm.compendium.custom;

import java.io.Serializable;

import ru.masterdm.compendium.domain.User;

/**
 * Object-transport "Пользователи системы ПО"
 * 
 * @author IShafigullin
 * 
 */
public class UserTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User vo = null;
	private String depName = null; // имя филиала

	public UserTO(User vo) {
		super();
		setVo(vo);
	}
	
	@Override
	public boolean equals(Object anObject) {
		if (anObject == null) {
			return false;
		}
		if (!(anObject instanceof UserTO)) {
			return false;
		}
		UserTO aUser = (UserTO) anObject;
		return aUser.getVo().equals(getVo());
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("UserTO: ");
		sb.append(getVo() + "(depName=" + getDepName() + ")");
		// sb.append('\n');
		// sb.append(" IsActive: ");
		// sb.append(getIsActive());

		return sb.toString();
	}

	public User getVo() {
		return vo;
	}

	public void setVo(User vo) {
		this.vo = vo;
	}
	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

}
