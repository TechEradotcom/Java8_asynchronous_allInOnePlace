package com.accenture.lkm.service;

import java.util.List;

import com.accenture.lkm.bean.DepartmentBean;
import com.accenture.lkm.dao.DepartmentDAOIMPL;

public class DepartmentServiceIMPL {

	private DepartmentDAOIMPL  deptDaoimpl=new  DepartmentDAOIMPL();
	
	public Integer createDepartment(List<DepartmentBean> listBean) throws InterruptedException{
		return deptDaoimpl.createDepartment(listBean);
	}
}
