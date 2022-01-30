package com.example.blog

import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.system.measureTimeMillis

suspend fun doSomethingUsefulOne(): Int {
    delay(1000L) // pretend we are doing something useful here
    return 13
}

suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L) // pretend we are doing something useful here, too
    return 29
}

@RestController
class TestController {
    @GetMapping("/")
    fun test (): String  {
        runBlocking() {
            val time = measureTimeMillis {
                val one = async {  doSomethingUsefulOne() }
                val two = async {  doSomethingUsefulTwo() }
                println("The answer is ${one.await() + two.await()}")
            }
            println("Completed in $time ms")
        }

        return "123"
    }
}