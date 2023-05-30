package com.employees.models.employee;

import java.util.HashMap;
import java.util.Map;

import com.employees.models.project.Project;

/**
 * Represents an employee in the system The model owns an association between
 * projects and the number of days the employee has worked on that project for
 * 
 * @author Tsvetelin
 *
 */
public class Employee {
	private final Long id;
	private final Map<Project, Long> projectDays;

	public Employee(Long id) {
		super();
		this.id = id;
		this.projectDays = new HashMap<>();
	}

	public Long getId() {
		return id;
	}

	public Map<Project, Long> getProjectDays() {
		return projectDays;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Employee) {
			return ((Employee) obj).id.equals(this.id);
		}
		return false;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", projectDays=" + projectDays + "]";
	}

}
