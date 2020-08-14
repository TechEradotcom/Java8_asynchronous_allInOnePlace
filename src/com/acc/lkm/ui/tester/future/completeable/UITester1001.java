package com.acc.lkm.ui.tester.future.completeable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

import com.acc.lkm.bean.DepartmentBean;
import com.acc.lkm.bean.EmployeeBean;
import com.acc.lkm.service.DepartmentServiceIMPL;
import com.acc.lkm.service.EmployeeServiceIMPL;
import com.acc.lkm.utility.CustomExecutorServiceUtility;

//supplyAsync() to supply values asynchronously
// replacement to submit yo executor service 
public class UITester1001 {

	private static EmployeeServiceIMPL employeeServiceIMPL= new EmployeeServiceIMPL();
	private static DepartmentServiceIMPL departmentServiceIMPL= new DepartmentServiceIMPL();
	
	public static void main(String[] args) throws InterruptedException, ExecutionException{
		
		ExecutorService ex =CustomExecutorServiceUtility.submitNewFixedThread(5);
		try{
		
		List<EmployeeBean> listEmpBeans =  Arrays.asList(new EmployeeBean(1001,"Jack"),
											new EmployeeBean(1002,"Justin"),
											new EmployeeBean(1003,"Mario"),
											new EmployeeBean(1004,"Tim"));
		List<DepartmentBean> listDeptBeans =  Arrays.asList(new DepartmentBean(2001,"Java"),
				new DepartmentBean(2002,"Dotnet"),
				new DepartmentBean(2003,"Digital"),
				new DepartmentBean(2004,"Testing"));
	
		
	
	/*	//First task
		Future<Integer> resultEmployeeFuture= ex.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return employeeServiceIMPL.createEmployee(listEmpBeans);
			}
		});
		
		//Second task
		Future<Integer> resultDepartmentFuture=ex.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return departmentServiceIMPL.createDepartment(listDeptBeans);
			}
		});*/
		
		//First Task
		CompletableFuture<Integer> resultEmployeeFuture =CompletableFuture.supplyAsync(new Supplier<Integer>() {
			@Override
			public Integer get() {
				Integer res=0;
				try {
					res=employeeServiceIMPL.createEmployee(listEmpBeans);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
				return res;
			}
		},ex);
		
		//Second Task
		CompletableFuture<Integer> resultDepartmentFuture =CompletableFuture.supplyAsync(new Supplier<Integer>() {
			@Override
			public Integer get() {
				Integer res=0;
				try {
					res=departmentServiceIMPL.createDepartment(listDeptBeans);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return res;
			}
		},ex);
		
		
		System.out.println("[[[Future Result of Department: "+resultDepartmentFuture.get()+"]]]");
		System.out.println("[[[Future Result of Employee: "+resultEmployeeFuture.get()+"]]]");	
		}catch(Exception exp){
			exp.printStackTrace();
		}finally{
			CustomExecutorServiceUtility.customShutDownExecutor(ex, 60);
		}
	}
}

/*
 * introduction to CompleteAble Future, refer document 
 * Running a task asynchronously and return the result using supplyAsync() 
 *  Running asynchronous computation using runAsync() which will not return any value
 * 
 * */
