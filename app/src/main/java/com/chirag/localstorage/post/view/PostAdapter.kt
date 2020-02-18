package com.chirag.localstorage.post.view

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.chirag.localstorage.R
import com.chirag.localstorage.post.entity.Post

class PostAdapter(var context: Context, var data: MutableList<Post>) :
    RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    var click: (Post) -> Unit = { }

    fun addItemClickListener(f: (Post) -> Unit) {
        click = f
    }


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

    fun updateData(list: MutableList<Post>){
        data to list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size
    inner class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Post) {
            binding.setVariable(BR.data, data)
            binding.executePendingBindings()
            binding.root.setOnClickListener { click(data) }
        }
    }
}

