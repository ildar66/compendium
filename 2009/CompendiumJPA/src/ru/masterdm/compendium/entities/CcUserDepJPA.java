package ru.masterdm.compendium.entities;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.NamedQuery;

@Entity
@Table(name="CC_USER_DEP")
@NamedQuery(name="findCcDepByUser", query = "SELECT c FROM CcUserDepJPA c WHERE c.pk.idUser = :pk_idUser")
public class CcUserDepJPA implements Serializable {
	@EmbeddedId
	private CcUserDepJPAPK pk;

	private static final long serialVersionUID = 1L;

	public CcUserDepJPA() {
		super();
	}

	public CcUserDepJPAPK getPk() {
		return this.pk;
	}

	public void setPk(CcUserDepJPAPK pk) {
		this.pk = pk;
	}

}
