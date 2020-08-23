package example.k.servicedemo;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;


public class MyIntentService extends IntentService {
    public static volatile boolean shouldContinue = true;
    //I've used a BroadcastReceiver inside the service that simply puts a stop boolean to true

    public MyIntentService() {
        super("MyIntentService");

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        doWork();
    }

    private void doWork() {
        for (int i = 0; i <= 10; i++) {
            try {
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