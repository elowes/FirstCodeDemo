package com.example.firstcodedemo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class NewsContentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.news_content)
        val newsTitle: String? = intent.getStringExtra("news_title")
        val newsContent: String? = intent.getStringExtra("news_content")
        val newsContentFragment: NewsContentFragment = supportFragmentManager.findFragmentById(R.id.news_content_fragment) as NewsContentFragment
        newsContentFragment.refresh(newsTitle, newsContent)
    }

    companion object {
        fun actionStart(context: Context, newsTitle: String, newsContent: String) {
            val intent = Intent(context, NewsContentActivity::class.java)
            intent.putExtra("news_title", newsTitle)
            intent.putExtra("news_content", newsContent)
            context.startActivity(intent)
        }
    }
}