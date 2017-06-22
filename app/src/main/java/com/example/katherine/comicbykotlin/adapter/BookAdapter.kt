package com.example.katherine.comicbykotlin.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.katherine.comicbykotlin.R
import com.example.katherine.comicbykotlin.mvp.model.Book
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_book.view.*

/**
 * Created by katherine on 6/20/17.
 */
class BookAdapter(var data:List<Book> = ArrayList<Book>(),var itemClick: (View, Int) -> Unit ):RecyclerView.Adapter<BookAdapter.BookViewHolder>(){

    companion object {
        val TYPE_TITLE :Int = 0
        val TYPE_CONTENT :Int = 1
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        bindView(holder.itemView, position)
    }

    private fun bindView(itemView: View, position: Int) {
        if (getItemViewType(position) == TYPE_CONTENT) {
            val book = data[position]
            itemView.tvTitle.text = book.title
            if (book.category.equals("")){
                itemView.tvCategory.setVisibility(View.GONE)
            }else {
                itemView.tvCategory.text = book.category
            }
            Picasso.with(itemView.context).load(book.cover).into(itemView.ivCover)
            itemView.container.setOnClickListener {
                itemClick(itemView, position)
            }
        }else if (getItemViewType(position) == TYPE_TITLE){
            val layoutParams = StaggeredGridLayoutManager.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            layoutParams.isFullSpan = true
            itemView.setLayoutParams(layoutParams)
            val book = data[position]
            itemView.tvTitle.text = book.title
            itemView.ivCover.setVisibility(View.GONE)
            itemView.tvCategory.setVisibility(View.GONE)
            itemView.ivIconLeft.setVisibility(View.VISIBLE)
            itemView.ivIconRight.setVisibility(View.VISIBLE)
            if (!book.cover.equals("")) {
                Picasso.with(itemView.context).load(book.cover).into(itemView.ivIconLeft)
                Picasso.with(itemView.context).load(book.cover).into(itemView.ivIconRight)
            }
        }
    }

    override fun getItemCount(): Int = data.size

    override fun getItemViewType(position: Int): Int {
        if (data[position].type === 0) {
            return TYPE_TITLE
        } else if (data[position].type === 1) {
            return TYPE_CONTENT
        }
        return super.getItemViewType(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, type: Int): BookViewHolder? {
        var itemView:View = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return BookViewHolder(itemView)
    }

    fun refreshData(newData: List<Book>) {
        data = newData
        notifyDataSetChanged()
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}