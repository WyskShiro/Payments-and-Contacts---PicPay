package will.shiro.desafiopicpay.util.extensions

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import will.shiro.desafiopicpay.R
import will.shiro.domain.util.extension.DD_MM_YYYY
import will.shiro.domain.util.extension.HH_MM
import will.shiro.domain.util.extension.format
import java.text.NumberFormat
import java.util.*

@BindingAdapter("loadCircle")
fun ImageView.loadCircle(url: String?) {
    Glide.with(this).load(url).apply(RequestOptions().circleCrop()).into(this)
}

@BindingAdapter("setVisible")
fun View.setVisible(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

// Receipt BindingAdapters
@BindingAdapter("setTransactionDate")
fun TextView.setTransactionDate(date: Date) {
    with(date) {
        text = context.getString(
            R.string.receipt_credit_card_time,
            format(DD_MM_YYYY),
            format(HH_MM)
        )
    }
}

@BindingAdapter("formatMoneyText")
fun TextView.formatMoneyText(value: Float) {
    text = context.getString(
        R.string.receipt_credit_card_value,
        NumberFormat.getInstance().format(value)
    )
}