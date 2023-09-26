package api

import okhttp3.OkHttpClient
import org.example.dto.Author
import org.example.dto.Comment
import org.example.dto.Post
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.concurrent.TimeUnit
import okhttp3.logging.HttpLoggingInterceptor


val client = OkHttpClient
    .Builder()
    .connectTimeout(5, TimeUnit.SECONDS)
 /*   .addInterceptor(HttpLoggingInterceptor(::println).apply {
        level = HttpLoggingInterceptor.Level.BODY
    })*/
    .build()

val retrofit = Retrofit
    .Builder()
    .baseUrl("http://localhost:9999/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .client(client)
    .build()

interface ApiServiceInterface {

    @GET("posts")
    fun getAllPosts(): Call<List<Post>>

    @GET("authors/{id}")
    fun getAuthor(@Path("id") id: Long): Call<Author>

    @GET("posts/{id}/comments")
    fun getComment(@Path("id") id: Long): Call<List<Comment>>
}

object ApiService {
    val service: ApiServiceInterface by lazy {
        retrofit.create()
    }
}