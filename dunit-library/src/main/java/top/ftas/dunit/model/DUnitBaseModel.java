package top.ftas.dunit.model;

import top.ftas.dunit.group.DUnitGroupInterface;

/**
 * Created by tik on 17/6/29.
 */

public abstract class DUnitBaseModel {
	private boolean isDirectlyAnnotated;
	private boolean isHidden;
	private int priority;
	private String name;

	private Class original;
	private String originalClassName;

	private String groupClassName;
	private Class<? extends DUnitGroupInterface> group;

	public boolean isDirectlyAnnotated() {
		return isDirectlyAnnotated;
	}

	public void setDirectlyAnnotated(boolean directlyAnnotated) {
		isDirectlyAnnotated = directlyAnnotated;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOriginalClassName() {
		return originalClassName;
	}

	public void setOriginalClassName(String originalClassName) {
		this.originalClassName = originalClassName;
	}

	public Class<? extends DUnitGroupInterface> getGroup() {
		return group;
	}

	public void setGroup(Class<? extends DUnitGroupInterface> group) {
		this.group = group;
	}

	public String getGroupClassName() {
		return groupClassName;
	}

	public void setGroupClassName(String groupClassName) {
		this.groupClassName = groupClassName;
	}

	public Class getOriginal() {
		return original;
	}

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean hidden) {
		isHidden = hidden;
	}

	public void setOriginal(Class original) {
		this.original = original;
	}

	@Override
	public String toString() {
		return "DUnitBaseModel{" +
				"isDirectlyAnnotated=" + isDirectlyAnnotated +
				", priority=" + priority +
				", name='" + name + '\'' +
				", original=" + original +
				", originalClassName='" + originalClassName + '\'' +
				", groupClassName='" + groupClassName + '\'' +
				", group=" + group +
				'}';
	}

	public abstract ModelType getModelType();


}
