package com.castprogramms.newinvestgame.tools

import android.content.Context
import android.content.ContextWrapper
import android.os.Handler
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

// класс для обновления состояния всех объектов
class Updater(var handler: Handler, var UPDATE_TIME: Long = 5000) : Runnable {
    var play: Boolean = true
    var objectsToUpdate: MutableList<Up> = mutableListOf()
    var timePoint: Long // нужна для расчета врмени которое прошло с предыдущего обновления
    var runFlag = true // проверка на состояние MainActivity

    init {
        timePoint = System.currentTimeMillis()
    }

    override fun run() {
        if (play) {
            MainScope().launch(Dispatchers.IO) {
                var nowTime = System.currentTimeMillis()
                if (nowTime - timePoint > UPDATE_TIME) {
                    objectsToUpdate.forEach { it.update() } // овызывает у всего массива метод update
                    timePoint = nowTime
                }
            }
            if (runFlag)
                handler.postDelayed(this, UPDATE_TIME) // Зацикливание функции
        }
    }
}

// данный интерфейс реализуют классы, которые хотят получать обновления
interface Up {
    fun update()
}

fun Context.lifecycleOwner(): LifecycleOwner? {
    var curContext = this
    var maxDepth = 20
    while (maxDepth-- > 0 && curContext !is LifecycleOwner) {
        curContext = (curContext as ContextWrapper).baseContext
    }
    return if (curContext is LifecycleOwner) {
        curContext as LifecycleOwner
    } else {
        null
    }
}