package com.madhav.culinaryfood.features.blog.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madhav.culinaryfood.core.data.models.BlogModel
import com.madhav.culinaryfood.databinding.ItemBlogBinding

class BlogRecyclerAdapter(private val myList: List<BlogModel>): ListAdapter<BlogModel, BlogRecyclerAdapter.BlogViewHolder>(BlogModelDiffCallBack()) {

    inner class BlogViewHolder(private val binding: ItemBlogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(blog: BlogModel) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlogViewHolder {
        val binding = ItemBlogBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BlogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BlogViewHolder, position: Int) {
        if(currentList.isEmpty() && position >= currentList.size) return
        holder.bind(currentList[position])
    }

}


class BlogModelDiffCallBack: DiffUtil.ItemCallback<BlogModel>() {
    override fun areItemsTheSame(oldItem: BlogModel, newItem: BlogModel): Boolean {
        return oldItem.blogId == newItem.blogId
    }

    override fun areContentsTheSame(oldItem: BlogModel, newItem: BlogModel): Boolean {
        return oldItem == newItem
    }

}