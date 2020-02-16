import android.text.Editable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.material.textfield.TextInputLayout
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import will.shiro.desafiopicpay.util.watcher.SimpleTextWatcher

// TextInputLayout
fun TextInputLayout.getText(): String? {
    return editText?.toString()
}

fun TextInputLayout.observeChanges(callback: (String) -> Unit) {
    editText?.observeChanges(callback)
}

// TextView
fun TextView.observeChanges(callback: (String) -> Unit): Disposable? {
    val subject = PublishSubject.create<String>()
    addTextChangedListener(object : SimpleTextWatcher() {
        override fun afterTextChanged(s: Editable) {
            subject.onNext(s.toString())
        }
    })
    return subject.subscribe { callback(it) }
}

// views

fun View.isVisible(): Boolean {
    return visibility == View.VISIBLE
}

// images

fun ImageView.load(url: String) {
    Glide.with(this).load(url).into(this)
}

fun View.setClick(callBack: () -> Unit) {
    setOnClickListener {
        callBack()
    }
}