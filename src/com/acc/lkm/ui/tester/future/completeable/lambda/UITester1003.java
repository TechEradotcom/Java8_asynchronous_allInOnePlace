package com.acc.lkm.ui.tester.future.completeable.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import com.acc.lkm.bean.DepartmentBean;
import com.acc.lkm.bean.EmployeeBean;
import com.acc.lkm.service.DepartmentServiceIMPL;
import com.acc.lkm.service.EmployeeServiceIMPL;
import com.acc.lkm.utility.CustomExecutorServiceUtility;

/*
 * 
 * thenCombineAsync is used to combine the current future  "resultDepartmentFuture"
 * with the future passed in as parameter resultEmployeeFuture
 * combinedRespFuture.getNow("Not Ready")
 * */
public class UITester1003 {

	private static EmployeeServiceIMPL employeeServiceIMPL= new EmployeeServiceIMPL();
	private static DepartmentServiceIMPL departmentServiceIMPL= new DepartmentServiceIMPL();
	
	public static void main(String[] args) throws Exception, ExecutionException{
		
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
	
		

		//First Task
		CompletableFuture<Integer> resultEmployeeFuture =CompletableFuture.supplyAsync(
				()-> 
				{
					Integer res=0;
					try {
						res=employeeServiceIMPL.createEmployee(listEmpBeans);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					};
					return res;
				},
				
				ex);
		
		//Second Task
		CompletableFuture<Integer> resultDepartmentFuture =CompletableFuture.supplyAsync(
				()-> 
				{
					Integer res=0;
					try {
						res=departmentServiceIMPL.createDepartment(listDeptBeans);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					return res;
				},
				
				ex);
		
		
		//Combining the responses
		CompletionStage<String> combinedResp =	resultDepartmentFuture.thenCombineAsync(resultEmployeeFuture,
				( t,u)-> "Department Result: "+t+", Employee REsp: "+u,ex).
				exceptionally(r->"Exception Happend"+r.getMessage());
		
		System.out.println("**Last Line**");
	
		CompletableFuture<String> combinedRespFuture = (CompletableFuture<String>) combinedResp;
	
		System.out.println("[[[Future Result of Department: "+resultDepartmentFuture.getNow(-100)+"]]]");
		System.out.println("[[[Future Result of Employee: "+resultEmployeeFuture.getNow(-100)+"]]]");	
		System.out.println("[[[Combined Resp: "+combinedRespFuture.getNow("Not Ready")+"]]]");	
	
		
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
 * 
 * */
