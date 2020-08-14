package com.acc.lkm.ui.tester.future.completeable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import com.acc.lkm.bean.DepartmentBean;
import com.acc.lkm.bean.EmployeeBean;
import com.acc.lkm.service.DepartmentServiceIMPL;
import com.acc.lkm.service.EmployeeServiceIMPL;
import com.acc.lkm.utility.CustomExecutorServiceUtility;

//supplyAsync() to supply values asynchronously
// replacement to submit yo executor service 
public class znoreferCopyOfUITester1001_show {

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
				new DepartmentBean(2004,"Testing1"),
				new DepartmentBean(2005,"Testing2"),
				new DepartmentBean(2006,"Testing3"));
	
		
		/*CompletableFuture<Integer> resultEmployeeFuture=  
				CompletableFuture.supplyAsync(new Supplier<Integer>() {
			@Override
			public Integer get() {
				// TODO Auto-generated method stub
				Integer ret= 0;
				try {
					ret= employeeServiceIMPL.createEmployee(listEmpBeans);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return ret;
			}
		},ex);*/
		
		CompletableFuture<Integer> resultDepartmentFuture=  
				CompletableFuture.supplyAsync(new Supplier<Integer>() {
			@Override
			public Integer get() {
				// TODO Auto-generated method stub
				Integer ret= 0;
				try {
					ret= departmentServiceIMPL.createDepartment(listDeptBeans);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return ret;
			}
		},ex);
		
		
		resultDepartmentFuture.thenComposeAsync( new Function<Integer, CompletionStage<Integer>>() {

			@Override
			public CompletionStage<Integer> apply(Integer department) {
				// TODO Auto-generated method stub
				CompletionStage<Integer>  res= null;
				CompletionStage<Integer>ret=CompletableFuture.supplyAsync(()->employeeServiceIMPL.createEmployee(listEmpBeans), ex);
				CompletionStage<Integer> comb=ret.thenApplyAsync(respEmp-> respEmp+department, ex);
				return comb;
			}
		}, ex);
		
		
		
		
	
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
