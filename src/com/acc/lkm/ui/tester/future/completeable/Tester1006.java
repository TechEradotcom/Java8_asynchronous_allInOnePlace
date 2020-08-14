package com.acc.lkm.ui.tester.future.completeable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

import com.acc.lkm.bean.EmployeeBean;
import com.acc.lkm.bean.WorkStation;
import com.acc.lkm.service.EmployeeServiceIMPL;
import com.acc.lkm.service.WorkStationServiceIMPL;
import com.acc.lkm.utility.CustomExecutorServiceUtility;

public class Tester1006 {
	static WorkStationServiceIMPL serviceIMPL = new WorkStationServiceIMPL();
	private static EmployeeServiceIMPL employeeServiceIMPL= new EmployeeServiceIMPL();
	
	public static void main(String[] args) throws InterruptedException, ExecutionException{
		
		
		List<EmployeeBean> listEmpBeans =  Arrays.asList(new EmployeeBean(1001,"Jack"),
											new EmployeeBean(1002,"Justin"),
											new EmployeeBean(1003,"Mario"),
											new EmployeeBean(1004,"Tim"));
		List<WorkStation> list= Arrays.asList(	new WorkStation(1001,"W1"),
						new WorkStation(1002,"W2"),
						new WorkStation(1003,"W3"),
						new WorkStation(1004,"W4"),
						new WorkStation(1005,"W4"));
		
		ExecutorService executors =  CustomExecutorServiceUtility.submitNewFixedThread(5);
		
		
		
		
		

		
		CustomExecutorServiceUtility.customShutDownExecutor(executors, 50);
	}

}
// create all the work stattion and employees in parallel and later show the merged result

// create all the works stations and after employees are created..
