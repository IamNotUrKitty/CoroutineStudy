package com.example.blog.client

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.stereotype.Service

@Service
class GitHubClient(
  private val gitHubApi: GitHubApi
) {
  suspend fun _getUser(id: String): GitHubUser = withContext(Dispatchers.IO) { gitHubApi.getUser(id) }
}