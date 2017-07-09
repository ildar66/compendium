package ru.masterdm.compendium.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQuery;

@Entity
@Table(name="TYPE_PROCESS")
@NamedQuery(name="findProcessTypeList", query = "SELECT p FROM ProcessTypeJPA p WHERE p.descriptionProcess LIKE :descriptionProcess")
public class ProcessTypeJPA implements Serializable {
	@Id
	@Column(name="ID_TYPE_PROCESS")
	private long idTypeProcess;

	@Column(name="DESCRIPTION_PROCESS")
	private String descriptionProcess;

	private static final long serialVersionUID = 1L;

	public ProcessTypeJPA() {
		super();
	}

	public long getIdTypeProcess() {
		return this.idTypeProcess;
	}

	public void setIdTypeProcess(long idTypeProcess) {
		this.idTypeProcess = idTypeProcess;
	}

	public String getDescriptionProcess() {
		return this.descriptionProcess;
	}

	public void setDescriptionProcess(String descriptionProcess) {
		this.descriptionProcess = descriptionProcess;
	}

}
