package com.personal.yuki.fuelsampleapp

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Reader

data class Post(val postId: String, val id: String) {

    class Deserializer : ResponseDeserializable<Post> {
        override fun deserialize(reader: Reader) = Gson().fromJson(reader, Post::class.java)
    }

    class ListDeserializer : ResponseDeserializable<List<Post>> {
        override fun deserialize(reader: Reader): List<Post> {
            val type = object : TypeToken<List<Post>>() {}.type
            return Gson().fromJson(reader, type)
        }
    }
}
