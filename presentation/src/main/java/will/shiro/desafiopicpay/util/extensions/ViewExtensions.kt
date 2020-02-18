import android.text.Editable
import android.view.View
import android.widget.TextView
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import will.shiro.desafiopicpay.util.watcher.SimpleTextWatcher

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

fun View.setClick(callBack: () -> Unit) {
    setOnClickListener {
        callBack()
    }
}