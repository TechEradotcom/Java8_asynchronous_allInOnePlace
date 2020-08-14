package com.accenture.lkm.service;

import java.util.List;

import com.accenture.lkm.bean.EmployeeBean;
import com.accenture.lkm.dao.EmployeeDAOIMPL;

public class EmployeeServiceIMPL {

	private EmployeeDAOIMPL  employeeDaoimpl= new EmployeeDAOIMPL();
	
	public Integer createEmployee(List<EmployeeBean> listBean){
		Integer e1=0;
		try {
			e1= employeeDaoimpl.createEmployee(listBean);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return e1;
	}
}
