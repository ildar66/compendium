package ru.masterdm.compendium.command;

import java.io.Serializable;

import ru.masterdm.compendium.exception.ModelException;

/**
 * @author IShafigullin
 */
public interface Command extends Serializable {
	public void execute() throws ModelException;
}
