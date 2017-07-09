package ru.masterdm.compendium.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.NamedQuery;

@Entity
@Table(name="CC_USER_ROLE")
@NamedQuery(name="findLinkCrCoUserToRole", query = "SELECT c FROM CcUserRoleJPA c WHERE c.idUser = :idUser AND  c.role.idRole = :role_idRole")
public class CcUserRoleJPA implements Serializable {
	@Id
    @Column(name = "ID_USER_ROLE")
    @SequenceGenerator(name = "UserRoleSequenceGenerator",
                       sequenceName = "CC_USER_ROLE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserRoleSequenceGenerator")
	private Long id;

    @Column(name = "ID_USER")
    private Long idUser;

	@ManyToOne
	@JoinColumn(name="ID_ROLE")
	private CcRoleJPA role;

    public CcRoleJPA getRole() {
		return role;
	}

	public void setRole(CcRoleJPA role) {
		this.role = role;
	}

	@Column(name = "STATUS")
	private String status = "N";

	private static final long serialVersionUID = 1L;

	public CcUserRoleJPA() {
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return this.idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
