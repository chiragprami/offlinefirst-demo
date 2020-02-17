package com.chirag.localstorage.post.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.chirag.localstorage.R
import com.chirag.localstorage.post.entity.Post

class PostAdapter(var context: Context, var data: MutableList<Post>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.row_post,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = data.size
    inner class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Post) {
            binding.setVariable(BR.data, data)
            binding.executePendingBindings()
        }
    }
}

