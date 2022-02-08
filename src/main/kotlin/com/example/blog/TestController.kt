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
      println("User with id=$id requested")
      user = gitHubClient._getUser(id)
      println("User with id=$id received")
    }

    println("User with id=$id received in $time ms")
    println()

    return user
  }

  @GetMapping("/")
  suspend fun test(): List<GitHubUser> = coroutineScope {
    var returnList: List<GitHubUser> = emptyList()

    val timeBlock = measureTimeMillis {
      returnList = setOf("iamnoturkitty", "elizarov").map { getUser(it) }
    }
    println("Completed blocking in $timeBlock ms")
    println("====================================")

    val time = measureTimeMillis {
      returnList = setOf("iamnoturkitty", "elizarov", "26", "21", "1").map { async { getUser(it) } }.awaitAll()

    }
    println("Completed in $time ms")

    println("Diff time = ${timeBlock - time}")

    returnList
  }
}