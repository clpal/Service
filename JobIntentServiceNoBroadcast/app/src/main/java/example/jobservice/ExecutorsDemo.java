package example.jobservice;


import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorsDemo {
    protected void newSingleThreadScheduledExecutor() {
        System.out.println("Thread main started");
       ScheduledExecutorService scheduledExecutorService= Executors.newSingleThreadScheduledExecutor();
   // Create Task

        Runnable task = new Runnable() {
            public void run() {
                // Do something
                System.out.println("Executing the task1 at: " + new Date());
            }
        };


       // scheduledExecutorService.scheduleAtFixedRate(runnable,0,2, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(task, 2, TimeUnit.SECONDS);
        System.out.println("Thread main finished");
    }
/* Executors.newSingleThreadScheduledExecutor() Method
    This method creates a single-threaded executor that can schedule commands to run after a given delay or to execute periodically. (Note however that if this single thread terminates due to a failure during execution prior to the shutdown, a new one will take its place if needed to execute subsequent tasks.) Tasks are guaranteed to execute sequentially, and no more than one task will be active at any given time. Unlike the otherwise equivalent newScheduledThreadPool(1) the returned executor is guaranteed not to be reconfigurable to use additional threads.*/
    }
