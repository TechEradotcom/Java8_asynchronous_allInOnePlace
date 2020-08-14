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
//invoke all
public class UITester91 {

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
				new DepartmentBean(2004,"Testing"),
				new DepartmentBean(2005,"SFDC"),
				new DepartmentBean(2006,"Operations"));
	
		Callable<Integer> empCallable= new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return employeeServiceIMPL.createEmployee(listEmpBeans);
			}
		};
		
		Callable<Integer> deptCallable=new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return departmentServiceIMPL.createDepartment(listDeptBeans);
			}
		};
		
		List<Callable<Integer>> listCallables =  Arrays.asList(empCallable,deptCallable);
		
		ExecutorService ex =CustomExecutorServiceUtility.submitNewFixedThread(5);
		
		//InvokeAll to invoke all the tasks 
		List<Future<Integer>> listFutures=ex.invokeAll(listCallables);
		
		
		// results will be printed when all the futures in the list are redeemed.
		listFutures.stream().forEach(fut->{
			if(fut.isDone()){
				try {
					System.out.println("Values: "+fut.get());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else{
				System.out.println("Future is incomplete");
			}
		});
		
	
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

