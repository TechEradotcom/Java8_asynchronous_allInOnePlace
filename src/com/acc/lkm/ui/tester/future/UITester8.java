package com.acc.lkm.ui.tester.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import com.acc.lkm.bean.DepartmentBean;
import com.acc.lkm.bean.EmployeeBean;
import com.acc.lkm.service.DepartmentServiceIMPL;
import com.acc.lkm.service.EmployeeServiceIMPL;
import com.acc.lkm.utility.CustomExecutorServiceUtility;
// 1 Result from the calls is not obtained (implementing)
// Blocked Call due to get() for future retrieval removed by using isdone
// but still rdeeemded result will not be printed properly  
public class UITester8 {

	private static EmployeeServiceIMPL employeeServiceIMPL= new EmployeeServiceIMPL();
	private static DepartmentServiceIMPL departmentServiceIMPL= new DepartmentServiceIMPL();
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		List<EmployeeBean> listEmpBeans =  Arrays.asList(new EmployeeBean(1001,"Jack"),
											new EmployeeBean(1002,"Justin"),
											new EmployeeBean(1003,"Mario"),
											new EmployeeBean(1004,"Tim"));
		List<DepartmentBean> listDeptBeans =  Arrays.asList(new DepartmentBean(2001,"Java"),
				new DepartmentBean(2002,"Dotnet"),
				new DepartmentBean(2003,"Digital"),
				new DepartmentBean(2004,"Testing"));
	
		ExecutorService ex =CustomExecutorServiceUtility.submitNewFixedThread(5);
		
		//First task
		Future<Integer> resultEmployeeFuture= ex.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return employeeServiceIMPL.createEmployee(listEmpBeans);
			}
		});
		if(resultEmployeeFuture.isDone()){
			System.out.println("[[[Future Result of Employee: "+resultEmployeeFuture.get()+"]]]");
		}
		else{
			System.out.println("Employee Future yet to be redeemded");
		}
		
		
		//Second task
		Future<Integer> resultDepartmentFuture=ex.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return departmentServiceIMPL.createDepartment(listDeptBeans);
			}
		});
		
		if(resultEmployeeFuture.isDone()){
			System.out.println("[[[Future Result of Department: "+resultDepartmentFuture.get()+"]]]");
		}
		else{
			System.out.println("Department Future yet to be redeemded");
		}
		CustomExecutorServiceUtility.customShutDownExecutor(ex, 60);
	}
}

// 1 Result from the calls is not obtained (implementing)
// on trying to print the future results, non redeemed futures are getting printed
// how to print redeemed future???
// to print redeemed result we can use get() method of future.
// this method throws the checked ExecutionException

// But using the get method program will at the point where get() is invoked till 
// result is redeemed hence blocking the program again.

// wise choice to invoke() the get method gives controlled asynchronous behavior
// unwise choice can make the program again blocking.

