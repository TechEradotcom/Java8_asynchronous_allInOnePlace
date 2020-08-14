package com.acc.lkm.dao;

import java.util.List;

import com.acc.lkm.bean.DepartmentBean;

public class DepartmentDAOIMPL {

	public Integer createDepartment(List<DepartmentBean> listBean) throws InterruptedException {
		System.out.println("["+Thread.currentThread().getName()+"]: ***Started Dept Batch Job***");
		int res=0;
		for(DepartmentBean  b: listBean){
			Thread.sleep(3000);
			System.out.println("["+Thread.currentThread().getName()+"]: Created Department: "+b);
		res++;
		}

		System.out.println("["+Thread.currentThread().getName()+"]: ***Completed Dept Batch Job***");
		return res;
	}
	

}
