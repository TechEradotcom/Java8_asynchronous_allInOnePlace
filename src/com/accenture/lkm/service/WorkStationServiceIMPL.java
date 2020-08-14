package com.accenture.lkm.service;

import java.util.List;

import com.accenture.lkm.bean.WorkStation;
import com.accenture.lkm.dao.WorkStationDAOIMPL;

public class WorkStationServiceIMPL {

	private WorkStationDAOIMPL  workStationDAOIMPL= new WorkStationDAOIMPL();
	
	public Integer createWorkStation(List<WorkStation> listBean) throws InterruptedException{
		return workStationDAOIMPL.createWorkStation(listBean);
	}
}
