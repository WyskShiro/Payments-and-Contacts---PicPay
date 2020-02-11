package will.shiro.desafiopicpay.view.user.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import will.shiro.desafiopicpay.databinding.VhContactBinding
import will.shiro.domain.entity.User

class ContactViewHolder(
    private val binding: VhContactBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun setupBinding(user: User) {

    }

    companion object {
        fun inflate(
            root: ViewGroup
        ): ContactViewHolder {
            return ContactViewHolder(
                VhContactBinding.inflate(
                    LayoutInflater.from(root.context),
                    root,
                    false
                )
            )
        }
    }
}