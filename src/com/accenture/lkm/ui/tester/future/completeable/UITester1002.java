package com.accenture.lkm.ui.tester.future.completeable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import com.accenture.lkm.bean.DepartmentBean;
import com.accenture.lkm.bean.EmployeeBean;
import com.accenture.lkm.service.DepartmentServiceIMPL;
import com.accenture.lkm.service.EmployeeServiceIMPL;
import com.accenture.lkm.utility.CustomExecutorServiceUtility;

/*
 * 
 * thenCombineAsync is used to combine the current future  "resultDepartmentFuture"
 * with the future passed in as parameter resultEmployeeFuture.
 * But combining will take place by invoking the call back ,
 * when the current and passed in future is redeemed 
 * */
public class UITester1002 {

	private static EmployeeServiceIMPL employeeServiceIMPL= new EmployeeServiceIMPL();
	private static DepartmentServiceIMPL departmentServiceIMPL= new DepartmentServiceIMPL();
	
	public static void main(String[] args) throws Exception, ExecutionException{
		
		ExecutorService ex =CustomExecutorServiceUtility.submitNewFixedThread(5);
		try{
		
		List<EmployeeBean> listEmpBeans =  Arrays.asList(new EmployeeBean(1001,"Jack"),
											new EmployeeBean(1002,"Justin"),
											new EmployeeBean(1003,"Mario"),
											new EmployeeBean(1004,"Tim"),
											new EmployeeBean(1005,"James"),
											new EmployeeBean(1006,"Nick"));
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
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return res;
			}
		},ex);
		
		//Combining the tasks
		CompletionStage<String> combinedResp =	resultDepartmentFuture.thenCombineAsync(resultEmployeeFuture, new BiFunction<Integer, Integer, String>() {
			@Override
			public String apply(Integer t, Integer u) {
				System.out.println(Thread.currentThread().getName()+": ***Invoked the combine***");
				return "Department Result(Current): "+t+", Employee REsp(Parameter): "+u;
			}	
		},ex).exceptionally(new Function<Throwable, String>() {
			@Override
			public String apply(Throwable t) {
				return "Exception Happend"+t.getMessage();
			}
		});
		System.out.println("**Last Line**");
		
			CompletableFuture<String> combinedRespFuture = (CompletableFuture<String>) combinedResp;
			
			System.out.println("[[[Future Result of Department: "+resultDepartmentFuture.get()+"]]]");
			System.out.println("[[[Future Result of Employee: "+resultEmployeeFuture.get()+"]]]");	
			System.out.println("[[[Combined Resp: "+combinedRespFuture.get()+"]]]");	
		
			
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
 * thenCombineAsync is used to combine the current future  "resultDepartmentFuture"
 * with the future passed in as parameter resultEmployeeFuture
 * */
