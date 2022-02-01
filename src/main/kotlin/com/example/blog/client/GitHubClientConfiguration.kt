package com.example.blog.client

import feign.Feign
import feign.gson.GsonDecoder
import feign.gson.GsonEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class GitHubClientConfiguration(
) {
  @Bean
  fun gitHubApi() = Feign.builder().encoder(GsonEncoder()).decoder(GsonDecoder()).target(GitHubApi::class.java, "https://api.github.com")!!
}