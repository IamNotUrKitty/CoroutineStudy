package com.example.blog.client

import feign.Param
import feign.RequestLine
import feign.Headers
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType

@Headers(
  HttpHeaders.CONTENT_TYPE + ": " + MediaType.APPLICATION_JSON_UTF8_VALUE,
  HttpHeaders.ACCEPT + ": " + MediaType.APPLICATION_JSON_UTF8_VALUE,
  HttpHeaders.AUTHORIZATION + ": " + "token " + "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"
)
interface GitHubApi {
  @RequestLine("GET /users/{id}")
  fun getUser(
    @Param("id") id: String
  ): GitHubUser
}