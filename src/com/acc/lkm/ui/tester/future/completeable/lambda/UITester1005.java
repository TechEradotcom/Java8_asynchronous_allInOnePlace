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

//Chaining using thenComposeAsych
public class UITester1005 {

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
		
		
		
	  CompletionStage<Integer> composedCompletionStage=	resultEmployeeFuture.thenComposeAsync(
			  (Integer resultOutter)-> {
					// outter can be given as input to the next one 
					CompletableFuture<Integer> resultDepartmentFuture =CompletableFuture.supplyAsync(
							()-> {
									Integer res=0;
									try {
										res=departmentServiceIMPL.createDepartment(listDeptBeans);
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									return res+resultOutter; // mixing outter result with inner and giving as input
								}
					,ex);
				return resultDepartmentFuture;
			},
			
			ex);
		
		  //type casting the CompletionStage to CompletableFuture
		  CompletableFuture<Integer> convertedComposedFuture = (CompletableFuture<Integer>) composedCompletionStage;
		
		  System.out.println("[[[Future Result of Employee: "+resultEmployeeFuture.get()+"]]]");
		  System.out.println("[[[Future Result of \"convertedComposedFuture\": "+convertedComposedFuture.get()+"]]]");
		}catch(Exception exp){
			exp.printStackTrace();
		}finally{
			CustomExecutorServiceUtility.customShutDownExecutor(ex, 60);
		}
	}
}

