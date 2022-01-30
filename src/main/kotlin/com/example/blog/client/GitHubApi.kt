package com.example.blog.client

import feign.Param
import feign.RequestLine

interface GitHubApi  {
    @RequestLine("GET /users/{id}")
    fun getUser(
        @Param("id") id : Long
    ): GitHubUser
}