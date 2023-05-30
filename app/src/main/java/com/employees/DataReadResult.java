package com.employees;

import java.util.HashMap;
import java.util.Map;

public class DataReadResult {
	private final Map<Long, Employee> employees = new HashMap<>();

	public Map<Long, Employee> getEmployees() {
		return employees;
	}
}
