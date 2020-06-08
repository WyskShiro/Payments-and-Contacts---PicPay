package will.shiro.desafiopicpay.util.customview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import will.shiro.desafiopicpay.databinding.VhNoResultsFoundBinding;

public class NoResultsFoundViewHolder extends RecyclerView.ViewHolder {

    private NoResultsFoundViewHolder(@NonNull VhNoResultsFoundBinding binding) {
        super(binding.getRoot());
    }

    public static NoResultsFoundViewHolder inflate(ViewGroup root) {
        return new NoResultsFoundViewHolder(
                VhNoResultsFoundBinding.inflate(
                        LayoutInflater.from(root.getContext()),
                        root,
                        false
                )
        );
    }
}
