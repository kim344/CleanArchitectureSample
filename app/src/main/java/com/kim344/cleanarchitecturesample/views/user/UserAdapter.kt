package com.kim344.cleanarchitecturesample.views.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kim344.cleanarchitecturesample.R
import com.kim344.cleanarchitecturesample.databinding.ItemUserBinding
import com.kim344.domain.search.Result

class UserAdapter(
    private val itemClick: ((Result) -> Unit)
): ListAdapter<Result, UserAdapter.ViewHolder>(diffUtil) {

    class ViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: Result) {
            binding.result = result
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ItemUserBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_user,
            parent,
            false
        )
        return ViewHolder(binding).apply {
            binding.root.setOnClickListener { view ->
                val position = adapterPosition.takeIf { it != RecyclerView.NO_POSITION }
                    ?: return@setOnClickListener
                itemClick(getItem(position))
            }
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Result>() {
            override fun areContentsTheSame(oldItem: Result, newItem: Result) =
                oldItem == newItem

            override fun areItemsTheSame(oldItem: Result, newItem: Result) =
                oldItem.email == newItem.email
        }
    }
}