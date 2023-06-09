package com.employees.models.employee;

public class EmployeePair {
	private final Employee employeeA;
	private final Employee employeeB;

	public EmployeePair(Employee employeeA, Employee employeeB) {
		super();
		this.employeeA = employeeA;
		this.employeeB = employeeB;
	}

	public Employee getEmployeeA() {
		return employeeA;
	}

	public Employee getEmployeeB() {
		return employeeB;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EmployeePair) {
			EmployeePair other = (EmployeePair) obj;
			return other.employeeA.equals(this.employeeA) && other.employeeB.equals(this.employeeB);
		}
		return false;
	}

	@Override
	public String toString() {
		return "EmployeePair [employeeA=" + employeeA + ", employeeB=" + employeeB + "]";
	}

}
