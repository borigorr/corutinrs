package org.example

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.example.repository.PostRepositoryImpl
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.awaitAll

fun main(args: Array<String>) {
    val repository = PostRepositoryImpl()
    with(CoroutineScope(EmptyCoroutineContext)) {
        launch {
            val posts = repository.getAll()
            val authors = posts.map { post ->
                async {
                    repository.author(post.authorId)
                }
            }.awaitAll()
            val comments = posts.map { post ->
                async {
                    repository.comment(post.id)
                }
            }.awaitAll()
            println(authors)
            println(comments)
        }
    }
    Thread.sleep(30_000L)
}