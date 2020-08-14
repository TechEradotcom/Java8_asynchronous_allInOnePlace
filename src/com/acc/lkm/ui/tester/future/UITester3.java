package com.acc.lkm.ui.tester.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

import com.acc.lkm.bean.DepartmentBean;
import com.acc.lkm.bean.EmployeeBean;
import com.acc.lkm.service.DepartmentServiceIMPL;
import com.acc.lkm.service.EmployeeServiceIMPL;
import com.acc.lkm.utility.CustomExecutorServiceUtility;

public class UITester3 {

	private static EmployeeServiceIMPL employeeServiceIMPL= new EmployeeServiceIMPL();
	private static DepartmentServiceIMPL departmentServiceIMPL= new DepartmentServiceIMPL();
	
	public static void main(String[] args) throws InterruptedException {
		List<EmployeeBean> listEmpBeans =  Arrays.asList(new EmployeeBean(1001,"Jack"),
											new EmployeeBean(1002,"Justin"),
											new EmployeeBean(1003,"Mario"),
											new EmployeeBean(1004,"Tim"));
		List<DepartmentBean> listDeptBeans =  Arrays.asList(new DepartmentBean(2001,"Java"),
				new DepartmentBean(2002,"Dotnet"),
				new DepartmentBean(2003,"Digital"),
				new DepartmentBean(2004,"Testing"));
	
		ExecutorService ex =CustomExecutorServiceUtility.submitNewFixedThread(5);
		ex.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return employeeServiceIMPL.createEmployee(listEmpBeans);
			}
		});
		
		ex.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return departmentServiceIMPL.createDepartment(listDeptBeans);
			}
		});
		
	}
}

//non blocked as 1 executor/pool is present
//this pool has 5 threads 
//and the tasks are executed concurrently by the executor service 
// by taking the available thread from the pool


/*Following Requirements are Still incomplete
 * 1 Result from the calls is not obtained
 * 2 Executor service is not shutting down
 * 
 * */
