package com.acc.lkm.service;

import java.util.List;

import com.acc.lkm.bean.DepartmentBean;
import com.acc.lkm.dao.DepartmentDAOIMPL;

public class DepartmentServiceIMPL {

	private DepartmentDAOIMPL  deptDaoimpl=new  DepartmentDAOIMPL();
	
	public Integer createDepartment(List<DepartmentBean> listBean) throws InterruptedException{
		return deptDaoimpl.createDepartment(listBean);
	}
}
