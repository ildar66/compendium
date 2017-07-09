package ru.masterdm.compendium.exception;
/**
 * Исключение при мапировке доменных(VtbObject) и обьектов-транспорт.
 * @author IShafigullin
 *
 */
public class MappingException extends VtbException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for MappingException
	 */
	public MappingException() {
		super();
	}

	/**
	 * Constructor for MappingException
	 */
	public MappingException(String arg0) {
		super(arg0);
	}

	/**
	 * Constructor MappingException.
	 * @param arg0
	 */
	public MappingException(Exception arg0,String desc) {
		super(arg0, desc);
	}


}

