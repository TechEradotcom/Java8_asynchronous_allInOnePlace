package com.accenture.lkm.ui.tester.future.completeable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;
import java.util.function.Supplier;

import com.accenture.lkm.bean.EmployeeBean;
import com.accenture.lkm.service.EmployeeServiceIMPL;
import com.accenture.lkm.utility.CustomExecutorServiceUtility;

/*converting CompletionStage<Integer> to CompletionStage<String> thenApplyAsync
 * */
public class UITester1004 {

	private static EmployeeServiceIMPL employeeServiceIMPL= new EmployeeServiceIMPL();
	
	public static void main(String[] args) throws Exception, ExecutionException{
		
		ExecutorService ex =CustomExecutorServiceUtility.submitNewFixedThread(5);
		try{
		
		List<EmployeeBean> listEmpBeans =  Arrays.asList(new EmployeeBean(1001,"Jack"),
											new EmployeeBean(1002,"Justin"),
											new EmployeeBean(1003,"Mario"),
											new EmployeeBean(1004,"Tim"));
	
		
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
		
		// to convert the Future of one type into Future of other type
		// this method will be invoked when the resultEmployeeFuture is redeemed
		CompletionStage<String> convertedStage=resultEmployeeFuture.thenApplyAsync(new Function<Integer, String>() {

			@Override
			public String apply(Integer t) {
				System.out.println(Thread.currentThread().getName()+": ***Invoked the call back***");
				String ret="odd";
				if(t%2==0){
					ret="even";
				}
				return ret;
			}
			
		},ex);
		
		CompletableFuture<String> convertedFuture= (CompletableFuture<String>) convertedStage;
		
		System.out.println("**Last Line**");
	
		
		System.out.println("[[[Future Result of Employee: "+resultEmployeeFuture.getNow(-100)+"]]]");	
		System.out.println("[[[Future \"convertedFuture\" Result of Employee: "+convertedFuture.getNow("Not Complete")+"]]]");	
		
		System.out.println("[[[Future Result of Employee: "+resultEmployeeFuture.get()+"]]]");	
		System.out.println("[[[Future \"convertedFuture\" Result of Employee: "+convertedFuture.get()+"]]]");	
			
		
			
		}catch(Exception exp){
			exp.printStackTrace();
		}finally{
			CustomExecutorServiceUtility.customShutDownExecutor(ex, 60);
		}
	}
}

/*
 *converting CompletionStage<Integer> to CompletionStage<String> thenApplyAsync
 * 
 * */