package ru.masterdm.compendium.domain;

import java.io.Serializable;

import ru.masterdm.compendium.mapping.Mapper;
import ru.masterdm.compendium.mapping.MapperFactory;

/**
 * Superclass that all system domain classes extend from.
 * Also, delegates persistent request to mapper class.
 * 
 * Creation date: (2/2/09 3:04:35 PM)
 * @author: IShafigullin
 */
public class VtbObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient Mapper mapper = null;
	/**
	 * TsObject constructor.
	 */
	public VtbObject() {
		super();
	}

	public Mapper getMapper() {
		if (mapper == null) {
			mapper = MapperFactory.getSystemMapperFactory().getMapper(this.getClass());
		}
		return mapper;
	}

}