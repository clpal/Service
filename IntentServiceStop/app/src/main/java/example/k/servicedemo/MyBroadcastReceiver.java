package example.k.servicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyBroadcastReceiver extends BroadcastReceiver {

    ResultListner listner;

    @Override
    public void onReceive(final Context context, Intent intent) {
        listner=(ResultListner)context;

        String result = intent.getStringExtra("result");

        listner.Result(result);
       ;
    }
    }
