package com.madhav.culinaryfood.features.blog.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madhav.culinaryfood.core.data.helpers.ShareDataHelper
import com.madhav.culinaryfood.core.data.models.BlogModel
import com.madhav.culinaryfood.databinding.ItemBlogBinding

class BlogRecyclerAdapter(private val myList: List<BlogModel>): ListAdapter<BlogModel, BlogRecyclerAdapter.BlogViewHolder>(BlogModelDiffCallBack()) {

    inner class BlogViewHolder(private val binding: ItemBlogBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(blog: BlogModel) {
            binding.tvTitleBlog.text = blog.title
            binding.tvDatePosted.text = blog.postedDate
            binding.tvDescriptions.text = blog.description
            binding.btnShare.setOnClickListener {
                ShareDataHelper().sharePlainText(binding.root.context, blog.title + "\n" + blog.description)
            }
            binding.tvAuthorName.text = "Posted by " + blog.authorId
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