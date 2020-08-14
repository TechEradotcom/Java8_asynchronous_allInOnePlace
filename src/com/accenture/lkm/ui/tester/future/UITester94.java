package com.accenture.lkm.ui.tester.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.accenture.lkm.bean.DepartmentBean;
import com.accenture.lkm.bean.EmployeeBean;
import com.accenture.lkm.service.DepartmentServiceIMPL;
import com.accenture.lkm.service.EmployeeServiceIMPL;
import com.accenture.lkm.utility.CustomExecutorServiceUtility;

// Task3 cancels task1.
// Future are redeemed for the non cancelled tasks
// 
public class UITester94 {

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
	
		
		//First task
		Future<Integer> resultEmployeeFuture1= ex.submit(new Callable<Integer>() {
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
		});
		
		
		//Third Task
		Future<Integer> resultEmployeeFuture2= ex.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				System.out.println("resultEmployeeFuture2 is cancelling resultEmployeeFuture1");
				resultEmployeeFuture1.cancel(true);
				return employeeServiceIMPL.createEmployee(listEmpBeans);
			}
		});
		
		
		// if tasks are not cancelled then print the value
		System.out.println("Redeemed Value for :\"resultDepartmentFuture\": "+CustomExecutorServiceUtility.getValueForNonCancelledFuture(resultDepartmentFuture));
		System.out.println("Redeemed Value for :\"resultEmployeeFuture1\": "+CustomExecutorServiceUtility.getValueForNonCancelledFuture(resultEmployeeFuture1));
		System.out.println("Redeemed Value for :\"resultEmployeeFuture2\": "+CustomExecutorServiceUtility.getValueForNonCancelledFuture(resultEmployeeFuture2));
		
		
		
		}catch(Exception exp){
			exp.printStackTrace();
		}finally{
			CustomExecutorServiceUtility.customShutDownExecutor(ex, 60);
		}
	}
}

