package com.receipe

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.net.Uri
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.receipe.databinding.ActivityServiceBinding
import com.receipe.serveces.MyService

class ServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityServiceBinding

    private var isBound = false
    private var boundService: MyService.ServiceBinder? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = Intent(this, MyService::class.java)
        bindService(intent, boundServiceConnection, BIND_AUTO_CREATE)

        initTextView()
    }

    private fun initTextView() {
        binding.textView.setOnClickListener {tV ->
            boundService?.nextFibonacci()
        }

        binding.textView.setOnLongClickListener {
            val address: Uri = Uri.parse("http://developer.alexanderklimov.ru")
            val openLinkIntent = Intent(Intent.ACTION_VIEW, address)

            if (openLinkIntent.resolveActivity(packageManager) != null) {
                startActivity(openLinkIntent)
            }
            false
        }
    }

    private fun setText(string: String){
        binding.textView.text = string
    }

    private val boundServiceConnection: ServiceConnection = object : ServiceConnection {
        // При соединении с сервисом
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            boundService = service as MyService.ServiceBinder
            isBound = boundService != null

            boundService?.listener = {
                setText(it.toString())
            }
        }

        // При разрыве соединения с сервисом
        override fun onServiceDisconnected(name: ComponentName) {
            isBound = false
            boundService = null
        }
    }

    override fun onStop() {
        super.onStop()
        if (isBound) {
            unbindService(boundServiceConnection)
        }
    }

}