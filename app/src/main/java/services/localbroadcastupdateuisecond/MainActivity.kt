package services.localbroadcastupdateuisecond

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var textview: TextView
    private lateinit var startService: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textview = findViewById(R.id.textview)
        //startService = findViewById(R.id.startService)
        val handler = Handler()
        var timer=Timer()
    var count =0

        val timerTask = object : TimerTask() {
            override fun run() {
                handler.post(object : Runnable {
                    override fun run() {
                        val intent = Intent(applicationContext, MyIntentService::class.java)
                        startService(intent)
                        Toast.makeText(applicationContext,"call"+ count++,Toast.LENGTH_LONG).show()
                    }

                })

            }

        }
        timer.schedule(timerTask, 0, 10000)
      /*  startService.setOnClickListener {
            val intentService = Intent(this, MyIntentService::class.java)
            startService(intentService)
        }*/
    }

    override fun onStart() {
        super.onStart()
        val intentFilter = IntentFilter()
        intentFilter.addAction("services.localbroadcastupdateuisecond")
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter)
    }

    override fun onStop() {
        super.onStop()
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver)
    }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val data = intent?.getStringExtra("Datapassed")
            textview.text = data.toString()
        }

    }
}