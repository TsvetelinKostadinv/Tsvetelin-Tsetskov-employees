package com.employees.csvDataReader;

import java.util.HashMap;
import java.util.Map;

import com.employees.models.employee.Employee;

/**
 * Represents the output of the CSV data reader
 * 
 * @author Tsvetelin
 *
 */
public class DataReadResult {
	private final Map<Long, Employee> employees = new HashMap<>();

	public Map<Long, Employee> getEmployees() {
		return employees;
	}
}
