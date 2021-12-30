package services.localbroadcastupdateuisecond

import android.app.IntentService
import android.content.Intent
import androidx.localbroadcastmanager.content.LocalBroadcastManager

// https://www.tutorialspoint.com/how-to-start-intent-service-for-every-10-sec-s-in-android
//https://www.tutorialspoint.com/how-to-update-continues-ui-from-intent-service-in-android
// How to update continues ui from Intent Service in Android?
class MyIntentService : IntentService("MyIntentService") {
    companion object {
        @Volatile
        var shouldStop = false
    }

    override fun onHandleIntent(intent: Intent?) {
        val intent1 = Intent()
        for (i in 0..10) {
            intent1.setAction("services.localbroadcastupdateuisecond")
            intent1.putExtra("Datapassed", "Hello Chhote Lal Pal")
            LocalBroadcastManager.getInstance(this).sendBroadcast(intent1)

            try {
                Thread.sleep(1000)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
        if (shouldStop) {
            stopSelf()
            return
        }
    }
}