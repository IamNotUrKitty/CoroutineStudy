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
  suspend fun getUser(id: String): GitHubUser {
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
  fun test(): List<GitHubUser> {
    var returnList: List<GitHubUser> = emptyList()
    runBlocking<Unit> {


//      repeat(10) {
        val timeBlock = measureTimeMillis {
          val list = setOf( "iamnoturkitty", "jongleb", "gaearon").map { getUser(it) }

        }
        println("Completed blocking in $timeBlock ms")

        val time = measureTimeMillis {
           returnList = setOf( "iamnoturkitty", "jongleb", "gaearon").map { async { getUser(it) } }.awaitAll()

        }
        println("Completed in $time ms")

        println("Diff time = ${timeBlock - time}")
//      }
    }

    return returnList
  }
}