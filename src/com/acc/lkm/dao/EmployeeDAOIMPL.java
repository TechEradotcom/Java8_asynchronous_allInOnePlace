package com.acc.lkm.dao;

import java.util.List;

import com.acc.lkm.bean.EmployeeBean;

public class EmployeeDAOIMPL {
	
	public Integer createEmployee(List<EmployeeBean> listBean) throws InterruptedException {
		System.out.println("["+Thread.currentThread().getName()+"]: ***Started Employee Batch Job***");
		int res=0;
		for(EmployeeBean  b: listBean){
			Thread.sleep(8000);
			System.out.println("["+Thread.currentThread().getName()+"]: Created Employee: "+b);
			res++;
		}

		System.out.println("["+Thread.currentThread().getName()+"]: ***Completed Employee Batch Job***");
		return res;
	}

}
