package example.k.demoservice;
import android.app.IntentService;
import android.content.Intent;
import android.os.IBinder;

import static example.k.demoservice.MainActivity.ActionCheck;

public class Service extends IntentService {
    public Service() {
        super(Service.class.getSimpleName());
    }
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        Intent intent1 = new Intent();
        intent1.setAction(ActionCheck);
        intent1.putExtra("message", "Example of Intent Service and Broadcast Reciver");
        sendBroadcast(intent1);
    }
}