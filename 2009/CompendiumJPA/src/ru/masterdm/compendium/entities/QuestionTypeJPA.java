package ru.masterdm.compendium.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;

@Entity
@Table(name = "CC_QUESTION_TYPE")
@NamedQueries( {
		@NamedQuery(name = "getQuestionTypeLike", query = "SELECT c FROM QuestionTypeJPA c WHERE c.questionType LIKE :questionType ORDER BY c.questionType"),
		@NamedQuery(name = "getQuestionTypes", query = "SELECT c FROM QuestionTypeJPA c") })
public class QuestionTypeJPA implements Serializable {
	
	/**
	 * @param idQuestionType
	 * @param questionType
	 */
	public QuestionTypeJPA(long idQuestionType, String questionType) {
		super();
		this.idQuestionType = idQuestionType;
		this.questionType = questionType;
	}

	@Override
	public String toString() {
		return " QuestionType ID: " + getIdQuestionType() + " QuestionType name="  + getQuestionType();
	}

	@Id
	@Column(name = "ID_QUESTION_TYPE")
	private long idQuestionType;

	@Column(name = "QUESTION_TYPE")
	private String questionType;

	private static final long serialVersionUID = 1L;

	public QuestionTypeJPA() {
		super();
	}

	public long getIdQuestionType() {
		return this.idQuestionType;
	}

	public void setIdQuestionType(long idQuestionType) {
		this.idQuestionType = idQuestionType;
	}

	public String getQuestionType() {
		return this.questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

}
