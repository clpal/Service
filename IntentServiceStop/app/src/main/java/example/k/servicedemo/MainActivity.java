package example.k.servicedemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

//https://stackoverflow.com/questions/11258083/how-to-force-an-intentservice-to-stop-immediately-with-a-cancel-button-from-an-a
public class MainActivity extends AppCompatActivity implements ResultListner {
    public static final String ACTION_STOP = "stop";

    TextView textView;
    Button startService, stopService;
    MyBroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);

        startService = findViewById(R.id.btnstartService);

        stopService = findViewById(R.id.btnstopService);

        startService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, MyIntentService.class);
                startService(intent);
            }
        });
        stopService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyIntentService.shouldContinue = false;


            }
        });


    }

    @Override
    protected void onStart() {
        broadcastReceiver = new MyBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_STOP);

        registerReceiver(broadcastReceiver, intentFilter);
        super.onStart();
    }

    @Override
    protected void onStop() {
        unregisterReceiver(broadcastReceiver);
        super.onStop();
    }

    public void onDestroy() {
        unregisterReceiver(broadcastReceiver);
        super.onDestroy();

    }


    @Override
    public void Result(String result) {
        textView.setText(result);

    }
}
