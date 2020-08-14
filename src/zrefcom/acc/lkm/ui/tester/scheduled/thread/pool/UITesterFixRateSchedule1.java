package zrefcom.acc.lkm.ui.tester.scheduled.thread.pool;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
//In this demo after every 1 second, execution of the tasks starts 
//its upto the task when it completes, 
public class UITesterFixRateSchedule1 {
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
		ScheduledFuture<?> future=executor.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);
		System.out.println("Execute Rest..");
		System.out.println(">>>>>"+future.get());
		/*a ScheduledFuture representing pending completion of the task, and whose get() method will throw an exception upon cancellation*/
		
	}

}


//if tasks completes late it will wait for task to complete and then start the new task
// so minimum wait is period time from start of the task time is counted, 
//maximum wait is task completion time




// dont read the following
//----------------------------
// // scheduleAtFixedRate() and scheduleWithFixedDelay().
// The first method is capable of executing tasks with a fixed time rate, e.g.
// once every second as demonstrated in this

/*Please keep in mind that scheduleAtFixedRate() doesn't take into account the actual duration of the task. 
 * So if you specify a period of one second but the task needs 2 seconds to be executed 
 * then the thread pool will work out to its full capacity [number of Threads] very soon .*/
