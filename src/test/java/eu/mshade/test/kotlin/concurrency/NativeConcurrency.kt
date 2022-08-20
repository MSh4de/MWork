package eu.mshade.test.kotlin.concurrency

import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun main() {
    val list = mutableListOf(1, 2, 3, 4, 5)

    val service = Executors.newScheduledThreadPool(2)

    service.scheduleAtFixedRate({
        list.add(list.last() + 1)
    }, 0, 50, TimeUnit.MILLISECONDS)

    service.scheduleAtFixedRate({
        println("------------------------------")
        for (i in list) {
            println("$i, ${System.currentTimeMillis()}")
        }
    }, 0, 1, TimeUnit.SECONDS)
}