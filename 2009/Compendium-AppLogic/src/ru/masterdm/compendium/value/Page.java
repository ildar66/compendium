package ru.masterdm.compendium.value;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Page в шаблоне обработка списка значений.
 * @author IShafigullin
 * 
 */
public class Page implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final Page EMPTY_PAGE = new Page(Collections.EMPTY_LIST, 0,
			false);

	private List<?> objects;

	int start;

	boolean hasNext;

	@SuppressWarnings("unchecked")
	public Page(List l, int s, boolean hasNext) {
		objects = new ArrayList(l);
		start = s;
		this.hasNext = hasNext;
	}

	public List<?> getList() {
		return objects;
	}

	public boolean isNextPageAvailable() {
		return hasNext;
	}

	public boolean isPreviousPageAvailable() {
		return start > 0;
	}

	public int getStartOfNextPage() {
		return start + objects.size();
	}

	public int getStartOfPreviousPage() {
		return Math.max(start - objects.size(), 0);
	}

	public int getSize() {
		return objects.size();
	}

}
