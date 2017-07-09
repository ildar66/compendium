package com.corejsf;

/**
 * @author IShafigullin
 *
 */
public class ActiveBean {

	private String action = null;
	public static final String ACTION_ADD = "Add";
	public static final String ACTION_EDIT = "Edit";
	public static final String ACTION_VIEW = "View";
	public static final String ACTION_DELETE = "Delete";

	public boolean isDelete() {
		return ACTION_DELETE.equalsIgnoreCase(action);
	}

	public boolean isEdit() {
		return ACTION_EDIT.equalsIgnoreCase(action);
	}

	public boolean isAdd() {
		return ACTION_ADD.equalsIgnoreCase(action);
	}

	public boolean isView() {
		return ACTION_VIEW.equalsIgnoreCase(action);
	}

	public String getAction() {
		return this.action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
