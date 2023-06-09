package com.employees.models.project;

/**
 * Simple POD class that encapsulates what a project is
 * @author Tsvetelin
 *
 */
public class Project {
	private final Long id;

	public Project(Long id) {
		super();
		this.id = id;
	}
	
	/**
	 * Very important in the case of projects as they are used as keys in maps 
	 * @see Employee#projectDays
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + "]";
	}

}
