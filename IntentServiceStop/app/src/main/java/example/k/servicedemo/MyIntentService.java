package example.k.servicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;


public class MyIntentService extends IntentService {
    public static volatile boolean shouldContinue = true;
    public static  String TAG = "MyIntentService";
    //I've used a BroadcastReceiver inside the service that simply puts a stop boolean to true

    public MyIntentService() {
        super("MyIntentService");

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
       int maxCountValue= intent.getIntExtra("maxCountValue",-1);
        doWork(maxCountValue);
    }

    private void doWork(  int value ) {
        for (int i = 0; i <= value; i++) {
            try {
                Log.d(TAG, "onHandleWork: The number is: " + i);
                Thread.sleep(2000);

                Intent result = new Intent();

                result.setAction(MainActivity.ACTION_STOP);

                result.putExtra("result", "CountDown Time: " + i);
                sendBroadcast(result);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // check the condition
            if (shouldContinue == false) {
                stopSelf();
                shouldContinue = true;

                return;
            }
        }
    }


}