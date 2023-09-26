package org.example.repository

import api.ApiService
import org.example.dto.Author
import org.example.dto.Comment
import org.example.dto.Post
import java.lang.RuntimeException

class PostRepositoryImpl: PostRepository {
    override suspend fun getAll(): List<Post> {
        val call = ApiService.service.getAllPosts().execute()
        if (!call.isSuccessful) {
            throw RuntimeException()
        }
        return call.body() ?: emptyList()
    }

    override suspend fun author(id: Long): Author {
        val call = ApiService.service.getAuthor(id).execute()
        return call.body() ?: throw RuntimeException();
    }

    override suspend fun comment(id: Long): List<Comment>? {
        val call = ApiService.service.getComment(id).execute()
        return call.body()
    }
}