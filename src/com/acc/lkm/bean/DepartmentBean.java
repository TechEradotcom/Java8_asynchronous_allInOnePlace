package com.acc.lkm.bean;

public class DepartmentBean {

	private Integer departmentId;
	private String departmentName;
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public DepartmentBean(Integer departmentId, String departmentName) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}
	@Override
	public String toString() {
		return "DepartmentBean [departmentId=" + departmentId
				+ ", departmentName=" + departmentName + "]";
	}
	
	
}
