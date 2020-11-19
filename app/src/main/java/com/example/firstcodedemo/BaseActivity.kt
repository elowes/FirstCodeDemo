package com.example.firstcodedemo

import android.content.*
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    private var receiver: ForceOfflineReceiver? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityController.addActivity(this)
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter("com.example.broadcastbestpractice.FORCE_OFFLINE")
        receiver = ForceOfflineReceiver()
        registerReceiver(receiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        if (receiver != null) {
            unregisterReceiver(receiver)
            receiver = null
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityController.removeActivity(this)
    }

    inner class ForceOfflineReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent?) {
            Log.d("TAG", "onReceive: ")
            val builder: AlertDialog.Builder = AlertDialog.Builder(context)
            builder.setTitle("Warning")
            builder.setMessage("You are forced to be offline. Please try to login again.")
            builder.setCancelable(false)
            builder.setPositiveButton("OK") { _: DialogInterface, _: Int ->
                ActivityController.finishAll()
                val i = Intent(context, LoginActivity::class.java)
                context.startActivity(i)
            }
            builder.show()
        }
    }
}