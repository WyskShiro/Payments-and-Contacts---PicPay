package will.shiro.desafiopicpay.view.user.list

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import will.shiro.desafiopicpay.util.customview.NoResultsFoundViewHolder
import will.shiro.domain.entity.User

class ContactAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var contacts: List<User> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            EMPTY_LIST -> NoResultsFoundViewHolder.inflate(parent)
            CONTACT_ITEMS -> ContactViewHolder.inflate(parent)
            else -> throw RuntimeException("Invalid viewType for ContactAdapter")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ContactViewHolder)?.setupBinding(contacts[position])
    }

    override fun getItemCount(): Int {
        return if (contacts.isEmpty()) {
            1
        } else {
            contacts.size
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (contacts.isEmpty()) {
            EMPTY_LIST
        } else {
            CONTACT_ITEMS
        }
    }

    fun updateList(contacts: List<User>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }

    private companion object {
        const val EMPTY_LIST = 0
        const val CONTACT_ITEMS = 1
    }
}