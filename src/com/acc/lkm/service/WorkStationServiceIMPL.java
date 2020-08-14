package com.acc.lkm.service;

import java.util.List;

import com.acc.lkm.bean.WorkStation;
import com.acc.lkm.dao.WorkStationDAOIMPL;

public class WorkStationServiceIMPL {

	private WorkStationDAOIMPL  workStationDAOIMPL= new WorkStationDAOIMPL();
	
	public Integer createWorkStation(List<WorkStation> listBean) throws InterruptedException{
		return workStationDAOIMPL.createWorkStation(listBean);
	}
}
