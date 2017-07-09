package ru.masterdm.compendium.custom;

import java.io.Serializable;

import ru.masterdm.compendium.domain.Department;

/**
 * Object-transport "связь Пользователей системы ПО с департаментами"
 * @author IShafigullin
 *
 */
public class UserToDepartmentTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Department vo = null;
	private String status = null;
	public String getStatus() {
		return status;
	}

	public void setStatus(String active) {
		this.status = active;
	}

	public UserToDepartmentTO(Department aVO) {
		super();
		setVo(aVO);
	}

	public Department getVo() {
		return vo;
	}

	private void setVo(Department vo) {
		this.vo = vo;
	}

}
