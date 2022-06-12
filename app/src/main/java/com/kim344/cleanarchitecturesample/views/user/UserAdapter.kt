package com.kim344.cleanarchitecturesample.views.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kim344.cleanarchitecturesample.base.BaseViewHolder
import com.kim344.cleanarchitecturesample.databinding.ItemUserBinding
import com.kim344.domain.search.Result

class UserAdapter(
    var itemList: List<Result>,
    var itemClick: ((Result) -> Unit)
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = itemList.size

    inner class UserItemViewHolder(var binding: ItemUserBinding) : BaseViewHolder(binding.root) {
        override fun bind(position: Int) {
            val context = binding.root.context
            val userData = itemList[position]

            Glide.with(context).load(userData.picture.thumbnail).into(binding.ivProfile)
            binding.tvUserName.text = userData.login.username
            binding.tvUserCountry.text = userData.location.country
            binding.tvUserEmail.text = userData.email

            binding.clBody.setOnClickListener {
                itemClick.invoke(userData)
            }
        }
    }

}