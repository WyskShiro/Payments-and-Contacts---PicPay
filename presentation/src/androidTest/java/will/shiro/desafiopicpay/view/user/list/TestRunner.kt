package will.shiro.desafiopicpay.view.user.list

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import will.shiro.desafiopicpay.PicPayApplication

class TestRunner : AndroidJUnitRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        return super.newApplication(cl, PicPayApplication::class.java.name, context)
    }
}