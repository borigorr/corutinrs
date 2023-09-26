package org.example.repository

import org.example.dto.Author
import org.example.dto.Comment
import org.example.dto.Post

interface PostRepository {
    suspend fun getAll(): List<Post>

    suspend fun author(id: Long): Author

    suspend fun comment(id: Long): List<Comment>?
}