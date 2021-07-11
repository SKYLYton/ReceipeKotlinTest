package com.receipe.serveces

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder


class MyService : Service() {

    var mBinder: IBinder = ServiceBinder()

    // Для расчёта чисел Фибоначчи
    private var fibonacci1: Long = 0
    private var fibonacci2: Long = 1

    override fun onCreate() {
        // Создание службы
        //startForegroundService()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Служба стартовала
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder {
        // Привязка клиента
        return mBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        // Удаление привязки
        return true
    }

    override fun onRebind(intent: Intent?) {
        // Перепривязка клиента
    }

    override fun onDestroy() {
        // Уничтожение службы
    }

    fun getNextFibonacci(listener: (Long) -> Unit) {
        val result: Long = fibonacci1 + fibonacci2
        fibonacci1 = fibonacci2
        fibonacci2 = result
        listener(result)
    }

    inner class ServiceBinder : Binder() {
        var listener: ((Long) -> Unit)? = null
        val service: MyService
            get() = this@MyService

        fun nextFibonacci() {
            service.getNextFibonacci {
                listener?.invoke(it)
            }
        }
    }
}