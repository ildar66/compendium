package ru.masterdm.compendium.exception;

import java.io.Serializable;

/**
 * @author IShafigullin
 *
 */
public class VtbException extends WrappedException implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor for TimeSheetException
	 */
	public VtbException() {
		super();
	}

	/**
	 * Constructor for TimeSheetException
	 */
	public VtbException(String arg0) {
		super(arg0);
	}

	/**
	 * Constructor TimeSheetException.
	 * @param arg0
	 */
	public VtbException(Exception arg0, String desc) {
		super(arg0, desc);
	}


}

