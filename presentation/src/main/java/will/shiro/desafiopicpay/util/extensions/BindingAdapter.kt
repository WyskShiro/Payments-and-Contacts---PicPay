package will.shiro.desafiopicpay.util.extensions

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("loadCircle")
fun ImageView.loadCircle(url: String?) {
    Glide.with(this).load(url).apply(RequestOptions().circleCrop()).into(this)
}

@BindingAdapter("setVisible")
fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}