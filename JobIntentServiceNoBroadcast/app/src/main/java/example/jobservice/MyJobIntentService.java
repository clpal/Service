package example.jobservice;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.JobIntentService;

// https://androidwave.com/working-with-jobintentservice/
public class MyJobIntentService extends JobIntentService {
    private static final int JobID = 200;
//Handler mHandler1=new Handler();
    private static final String TAG = "MyJobIntentService";
    /**
     * Unique job ID for this service.
     */
    Handler mHandler = new Handler(Looper.getMainLooper());

    public MyJobIntentService() {
        super();
    }

    static void enqueueWork(Context context, Intent intent) {
        enqueueWork(context, MyJobIntentService.class, JobID, intent);

    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        /**
         * Write code here.. Perform Long operation here such as Download/Upload of
         * file, Sync Some data
         * The system or framework is already holding a wake lock for us at this point
         */
        int maxCount = intent.getIntExtra("maxCountValue", -1);
        for (int i = 0; i <= maxCount; i++) {
            Log.d(TAG, "onHandleWork: The number is: " + i);
            showToast(String.valueOf(i));
            try {
                Thread.sleep(1000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        showToast("Job Execution Started");
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(@NonNull Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        showToast("Job Execution Finished");
    }

    private void showToast(final CharSequence text) {
/*       ExecutorsDemo executorsDemo= new ExecutorsDemo();
       executorsDemo.newSingleThreadScheduledExecutor();*/
        System.out.println("Thread main started");
        ScheduledExecutorService worker = Executors.newSingleThreadScheduledExecutor();
        Runnable task = new Runnable() {
            public void run() {
                // Do something
                //System.out.println("Executing the task1 at: " + new Date());
                System.out.println("Executing the task1 at: " + text);
                Toast.makeText(MyJobIntentService.this, text, Toast.LENGTH_LONG).show();
            }
        };
        // scheduledExecutorService.scheduleAtFixedRate(runnable,0,2, TimeUnit.SECONDS);
        worker.schedule(task, 2, TimeUnit.SECONDS);
      //  worker.shutdown();
        System.out.println("Thread main finished");
    }
    // Helper for showing tests
   /* void showToast1(final CharSequence text) {
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MyJobIntentService.this, text, Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}
