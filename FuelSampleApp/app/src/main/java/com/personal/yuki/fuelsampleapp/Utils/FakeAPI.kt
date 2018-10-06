package com.personal.yuki.fuelsampleapp

import com.github.kittinunf.fuel.util.FuelRouting
import com.github.kittinunf.fuel.core.Method

sealed class FakeAPI : FuelRouting {

    class posts(val userId: String, override val body: String?): FakeAPI()
    class comments(val postId: String, override val body: String?): FakeAPI()

    override val basePath = "https://jsonplaceholder.typicode.com"

    override val method: Method
        get() {
            return when(this) {
                is FakeAPI.posts -> Method.POST
                is FakeAPI.comments -> Method.GET
            }
        }

    override val path: String
        get() {
            return when(this) {
                is FakeAPI.posts -> "/posts"
                is FakeAPI.comments -> "/comments"
            }
        }

    override val params: List<Pair<String, Any?>>?
        get() {
            return when(this) {
                is FakeAPI.posts -> listOf("userId" to this.userId)
                is FakeAPI.comments -> listOf("postId" to this.postId)
            }
        }

    override val headers: Map<String, String>?
        get() {
            return null

//            // e.g.
//            return mapOf("Content-Type" to "application/json",  "User-Agent" to "Android")
        }
}