package com.accenture.lkm.utility;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class CustomExecutorServiceUtility {
	static public ExecutorService submitNewSingleThread(){
		ExecutorService executor =  Executors.newSingleThreadExecutor();
	    return executor;
	}
	
	static public ExecutorService submitNewFixedThread(int threadNumbers){
		ExecutorService executor =  Executors.newFixedThreadPool(threadNumbers);
		return executor;
	}
	
	static public void customShutDownExecutor(ExecutorService executor,int timeSeconds){
		try 
		{
		   System.out.println("Process will be shut down in: "+timeSeconds+", seconds");	    
		    executor.shutdown();
		    executor.awaitTermination(timeSeconds, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
		    System.err.println("tasks interrupted");
		}
		finally 
		{
		    if (!executor.isTerminated()) {
		        System.err.println("***Forced shutdown non-finished tasks***");
		        executor.shutdownNow();
		    }
		    
		   System.out.println("shutdown finished");
		}
	}
	/*An ExecutorService provides two methods for for stopping the service: 
	 * 				shutdown() waits for currently running tasks to finish,
	 * 				to configure the wait  awaitTermination method is used.
	 * 				while shutdownNow() interrupts all running tasks and shut the executor down immediately.
	 * 
	 * 
	 * */
	
	
	static public Integer getValueForNonCancelledFuture(Future<Integer> future) throws InterruptedException, ExecutionException{
		Integer res=0;
		if(!future.isCancelled()){ // if future is not cancelled then return the value of future
			res=future.get();
		}
		else{
			System.out.println("Cancelled Task value is:"+future.get());
		}
		return res;
	}
	
}
