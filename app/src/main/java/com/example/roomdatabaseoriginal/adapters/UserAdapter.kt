package com.example.roomdatabaseoriginal.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.roomdatabaseoriginal.data.entity.User
import com.example.roomdatabaseoriginal.databinding.ItemUserBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var onUserClickListener: ((User) -> Unit)? = null

    fun setOnUserClickListener(block: (User) -> Unit) {
        onUserClickListener = block
    }

    inner class UserViewHolder(private val binding: ItemUserBinding) : ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind() {
            val position = models[adapterPosition]
            binding.tvName.text = position.name
            binding.tvPhoneNumber.text = "+998" + position.phoneNumber.toString()

            binding.root.setOnClickListener {
                onUserClickListener?.invoke(position)
            }
        }
    }

    var models = mutableListOf<User>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int {
        return models.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind()
    }
}