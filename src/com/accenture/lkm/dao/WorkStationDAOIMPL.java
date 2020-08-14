package com.accenture.lkm.dao;

import java.util.List;

import com.accenture.lkm.bean.WorkStation;

public class WorkStationDAOIMPL {
	
	public Integer createWorkStation(List<WorkStation> listBean) throws InterruptedException {
		System.out.println("["+Thread.currentThread().getName()+"]: ***Started Work Station Batch Job***");
		int res=0;
		for(WorkStation  b: listBean){
			Thread.sleep(2000);
			System.out.println("["+Thread.currentThread().getName()+"]: Created Work Station: "+b);
			res++;
		}

		System.out.println("["+Thread.currentThread().getName()+"]: ***Completed Work Station Batch Job***");
		return res;
	}

}
