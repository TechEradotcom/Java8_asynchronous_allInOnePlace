package com.accenture.lkm.bean;

public class EmployeeBean {

	private Integer employeeId;
	private String employeeName;

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public EmployeeBean(Integer employeeId, String employeeName) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
	}

	@Override
	public String toString() {
		return "EmployeeBean [employeeId=" + employeeId + ", employeeName="
				+ employeeName + "]";
	}
	
	
}
