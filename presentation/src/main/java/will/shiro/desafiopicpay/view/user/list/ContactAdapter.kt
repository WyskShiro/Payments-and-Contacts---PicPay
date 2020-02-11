package will.shiro.desafiopicpay.view.user.list

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import will.shiro.domain.entity.User

class ContactAdapter : ListAdapter<User, ContactViewHolder>(ContactDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        return ContactViewHolder.inflate(parent)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.setupBinding(getItem(position))
    }
}

object ContactDiffUtil : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }
}