package will.shiro.desafiopicpay.util.customview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import will.shiro.desafiopicpay.databinding.VhNoResultsFoundBinding

class NoResultsFoundViewHolder private constructor(
    binding: VhNoResultsFoundBinding
) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun inflate(
            root: ViewGroup
        ): NoResultsFoundViewHolder {
            return NoResultsFoundViewHolder(
                VhNoResultsFoundBinding.inflate(
                    LayoutInflater.from(root.context),
                    root,
                    false
                )
            )
        }
    }
}