package com.example.blog

import com.example.blog.client.GitHubClient
import com.example.blog.client.GitHubUser
import kotlinx.coroutines.*
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.system.measureTimeMillis

@RestController
class TestController(
       private val gitHubClient: GitHubClient
) {
    suspend fun getUser(id: String):GitHubUser{
        val user: GitHubUser
        val time = measureTimeMillis {
            println("User with $id requested")
            user = gitHubClient._getUser(id)
            println("User with $id received")
        }

        println("User with $id received in $time ms")

        return user
    }

    @GetMapping("/")
    fun test (): String  {
        runBlocking<Unit> {
            val time = measureTimeMillis {
                val dfList = setOf("21", "16", "iamnoturkitty").map { async { getUser(it) } }.awaitAll()
//                val one =  async {  getUser("21") } //gitHubClient._getUser("15")
//                val two =  async { getUser("16") }  // gitHubClient._getUser("16")
//                val three =  async { getUser("iamnoturkitty") } //gitHubClient._getUser("iamnoturkitty")

                println(dfList)
//                println(two.await())
//                println(three.await())
            }
            println("Completed in $time ms")
        }

        return "123"
    }
}