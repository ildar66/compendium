/**
 * 
 */
package ru.masterdm.compendium.domain;

/**
 * VtbObject "тип вопроса для кредитных комитетов"
 * @author IShafigullin
 *
 */
public class QuestionType extends VtbObject {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id = null; //Код
	private String name = null; //Имя
	@Override
	public boolean equals(Object anObject) {
		if (anObject == null) {
			return false;
		}
		if (!(anObject instanceof QuestionType)) {
			return false;
		}
		QuestionType aQuestionType = (QuestionType) anObject;
		return aQuestionType.getId().intValue() == getId().intValue();
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("QuestionType: ");
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

	public QuestionType(Integer aId, String aName) {
		setId(aId);
		setName(aName);
	}
	
	public QuestionType(Integer aId) {
		setId(aId);
		setName("none");
	}	

}
