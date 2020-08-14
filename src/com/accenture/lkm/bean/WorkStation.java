package com.accenture.lkm.bean;

public class WorkStation {

	private Integer workStationId;
	private String workStationName;
	
	public WorkStation(Integer workStationId, String workStationName) {
		super();
		this.workStationId = workStationId;
		this.workStationName = workStationName;
	}
	public Integer getWorkStationId() {
		return workStationId;
	}
	public void setWorkStationId(Integer workStationId) {
		this.workStationId = workStationId;
	}
	public String getWorkStationName() {
		return workStationName;
	}
	public void setWorkStationName(String workStationName) {
		this.workStationName = workStationName;
	}
	@Override
	public String toString() {
		return "WorkStation [workStationId=" + workStationId
				+ ", workStationName=" + workStationName + "]";
	}
	
	

	
	
}
