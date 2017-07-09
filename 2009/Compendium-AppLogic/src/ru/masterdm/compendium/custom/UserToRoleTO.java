package ru.masterdm.compendium.custom;

import java.io.Serializable;

import ru.masterdm.compendium.domain.Role;

/**
 * Object-transport "роли Пользователей системы ПО"
 * @author IShafigullin
 *
 */
public class UserToRoleTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Role vo = null;
	private String status = null;
	private String processName = null;

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String active) {
		this.status = active;
	}

	public UserToRoleTO(Role aVO) {
		super();
		setVo(aVO);
	}

	public Role getVo() {
		return vo;
	}

	private void setVo(Role vo) {
		this.vo = vo;
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj == null)
			return false;
		
		if (!( obj instanceof UserToRoleTO))
			return false;
		
		UserToRoleTO aDocumentGroup = (UserToRoleTO) obj;
		return aDocumentGroup.getVo().getId() == this.getVo().getId();
	}	
}
