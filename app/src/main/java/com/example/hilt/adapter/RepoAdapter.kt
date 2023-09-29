package com.example.hilt.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hilt.databinding.ItemRepoBinding
import com.example.hilt.model.ActivityResponse

class RepoAdapter(
    val context: Context,
    private val body: ArrayList<ActivityResponse>
) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding =
            ItemRepoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val body = body[position]
        holder.bind(body)
    }

    override fun getItemCount(): Int {
        return body.size
    }

    inner class ViewHolder(private val binding: ItemRepoBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(body: ActivityResponse) = with(binding) {
                binding.nameTextView.text =
                    "Type: ${body.type}"
                binding.descTextView.text =
                    "Key: ${body.key}"
                binding.createdDateTextView.text =
                    "Activity: ${body.activity}"
            
        }


    }
}