package com.personal.yuki.fuelsampleapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 非同期処理
        val router = FakeAPI.comments("1", null)
        APIClient.requtest(router, Post.ListDeserializer()) { result ->

            when (result) {
                is APIResult.Success -> {

                    // レスポンスを表示
                    println("Result.Success\n$result.value")
                }
                is APIResult.Failure -> {

                    // 失敗理由を表示
                    println("Result.Failure\n$result.reason")
                }
            }
        }
    }
}
