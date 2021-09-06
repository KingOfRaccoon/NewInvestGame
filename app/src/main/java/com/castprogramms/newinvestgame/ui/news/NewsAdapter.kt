package com.castprogramms.newinvestgame.ui.news

import android.app.AlertDialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.castprogramms.newinvestgame.R
import com.castprogramms.newinvestgame.databinding.DialogNewBinding
import com.castprogramms.newinvestgame.databinding.ItemNewsBinding
import com.castprogramms.newinvestgame.news.New

class NewsAdapter: RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    var news = mutableListOf<New>()
    set(value) {
        field = value
        value.indices.forEach { notifyItemInserted(it) }
    }

    fun addNew(new: New){
        news.add(0, new)
        notifyItemInserted(0)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_news, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news[position])
    }

    override fun getItemCount() = news.size

    inner class NewsViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val binding = ItemNewsBinding.bind(view)

        fun bind(new: New){
            binding.textNew.text = new.msg
            binding.root.setOnClickListener {
                createFullTextAlertDialog(new.msg, it.context)
            }
        }
    }


    private fun createFullTextAlertDialog(text: String, context: Context) {
        val view = LayoutInflater.from(context).inflate(R.layout.dialog_new, null)

        val ad = AlertDialog.Builder(context)
            .setView(view)
            .create()

        val binding = DialogNewBinding.bind(view)
        binding.textBody.text = text

        if (ad.window != null)
            ad.window!!.setBackgroundDrawable(ColorDrawable(0))

        ad.show()
    }
}