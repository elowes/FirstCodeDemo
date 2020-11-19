package com.example.firstcodedemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewsTitleFragment : Fragment() {
    private var isTwoPane: Boolean? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.news_title_frag, container, false)
        val newsTitleRecyclerView: RecyclerView = view.findViewById(R.id.news_title_recycler_view)
        val layoutManager = LinearLayoutManager(activity)
        newsTitleRecyclerView.layoutManager = layoutManager
        val adapter = NewsAdapter(getNews())
        newsTitleRecyclerView.adapter = adapter
        return view
    }

    private fun getNews():ArrayList<News> {
        val newsList: ArrayList<News> = ArrayList();
        for (x in 1 until 50) {
            val news = News()
            news.title = "This is news title$x";
            news.content = "Hello world"
            newsList.add(news)
        }
        return newsList
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isTwoPane = activity?.findViewById<FrameLayout>(R.id.news_content_layout) != null
    }

    inner class NewsAdapter (private val newsList: ArrayList<News>): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.news_item, parent, false)
            val viewHolder = ViewHolder(view);
            view.setOnClickListener {
                val news: News = newsList[viewHolder.adapterPosition]
                if (isTwoPane == true) {
                    val newsContentFragment: NewsContentFragment? = fragmentManager?.findFragmentById(R.id.news_content_fragment) as NewsContentFragment
                    newsContentFragment?.refresh(news.title, news.content)
                } else {
                    NewsContentActivity.actionStart(activity as FragmentActivity, news.title, news.content)
                }
            }
            return viewHolder
        }

        override fun getItemCount(): Int = newsList.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val news: News = newsList[position]
            holder.newsTitleText.text = news.title;
        }

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            var newsTitleText: TextView = itemView.findViewById(R.id.news_title)
        }
    }
}