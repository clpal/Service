package clpal.services.updatemainthread

import android.app.IntentService
import android.content.Intent

class MyIntentService : IntentService("MyIntentService") {

    override fun onHandleIntent(intent: Intent?) {
        val intent1 = Intent()
        intent1.setAction("clpal.services.updatemainthread")
        intent1.putExtra("Datapassed", "Hello Chhote Lal Pal")
        sendBroadcast(intent1)

    }
}