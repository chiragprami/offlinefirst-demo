package com.chirag.localstorage.modules.owner.view

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.chirag.localstorage.R
import com.chirag.localstorage.entity.Items
import com.chirag.localstorage.entity.Owner

class OwnerAdapter(var context: Context, var data: ArrayList<Owner>) :
    RecyclerView.Adapter<OwnerAdapter.ViewHolder>() {

    var click: (Owner) -> Unit = { }

    fun addItemClickListener(f: (Owner) -> Unit) {
        click = f
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.row_owner,
                parent,
                false
            )
        )
    }

    fun updateData(list: ArrayList<Items>){
        data to list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size
    inner class ViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Owner) {
            binding.setVariable(BR.data, data)
            binding.executePendingBindings()
            binding.root.setOnClickListener { click(data) }
        }
    }
}

