package com.employees;

public class Project {
	private final Long id;

	public Project(Long id) {
		super();
		this.id = id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Project) {
			return ((Project) obj).id.equals(this.id);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + "]";
	}

}
