package zrefcom.accenture.lkm.ui.tester.scheduled.thread.pool;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class UITesterFixDelaySchedule2 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		Runnable task = () -> {
			System.out.println(Thread.currentThread().getName()+": Hello from Scheduled Tasks");
			System.out.println(Thread.currentThread().getName()+": Scheduling: " + System.nanoTime()+": "+ new Date());
			System.out.println("****************************");
				try {
					Thread.sleep(5000); //Execution Time Period is more than the task repetition period 
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"----Over---\n");
			};

		int initialDelay = 0;
		int period = 2;
		ScheduledFuture<?> future=executor.scheduleWithFixedDelay(task, initialDelay, period, TimeUnit.SECONDS);
		
	}

}



//it is the time between the finish of current task and start of new task
//here you will find the difference unLike ScheduleAtFixed Rate 
// where it immediately runs the task if the execution of task has crossed the period.
// This schduleAtFixed time still waits for the period of 2 before starting the next task.

//we can easiy say schduleAtFixedTime period is end of one task and 
// start of new task

// where as schduleAtFixed rate period is the time between  the start point of to tasks.
// if task completes before the period it will wait to start new task.
// if task completes after period then immediately starts the tasks



