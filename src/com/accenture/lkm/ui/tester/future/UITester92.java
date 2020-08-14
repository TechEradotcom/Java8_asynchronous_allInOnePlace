package com.accenture.lkm.ui.tester.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import com.accenture.lkm.bean.DepartmentBean;
import com.accenture.lkm.bean.EmployeeBean;
import com.accenture.lkm.service.DepartmentServiceIMPL;
import com.accenture.lkm.service.EmployeeServiceIMPL;
import com.accenture.lkm.utility.CustomExecutorServiceUtility;
// 1 Result from the calls is not obtained (implementing)
// Correct place to invoke get() for UnBlocked execution and Future retrieval

// NEW=> Get with time out option
// resultDepartmentFuture.get(6,TimeUnit.SECONDS)  throws the TimeOutException
public class UITester92 {

	private static EmployeeServiceIMPL employeeServiceIMPL= new EmployeeServiceIMPL();
	private static DepartmentServiceIMPL departmentServiceIMPL= new DepartmentServiceIMPL();
	
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
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
		
		//Second task
		Future<Integer> resultDepartmentFuture=ex.submit(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return departmentServiceIMPL.createDepartment(listDeptBeans);
			}
		});
		System.out.println("[[[Future Result of Department: "+resultDepartmentFuture.get(6,TimeUnit.SECONDS)+"]]]");
		System.out.println("[[[Future Result of Employee: "+resultEmployeeFuture.get()+"]]]");
		
	
		CustomExecutorServiceUtility.customShutDownExecutor(ex, 60);
	}
}

// resultDepartmentFuture.get(6,TimeUnit.SECONDS)  throws the TimeOutException
// if future is not redeemed with  with in 6 seconds then TimeOutException will be thrown
// exception is thrown and current main thread is hindered due to which customShutDownExecutor()
// is not executed, others threads continue with there job and complete.
// but main thread keep running as shutdown was hindered
// so it is good to close the executors in finally block