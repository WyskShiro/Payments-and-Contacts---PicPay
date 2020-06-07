package will.shiro.desafiopicpay

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDexApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.realm.Realm
import io.realm.RealmConfiguration
import will.shiro.desafiopicpay.util.di.component.DaggerAppComponent
import javax.inject.Inject

class PicPayApplication : MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        DaggerAppComponent.builder()
            .context(this)
            .build()
            .inject(this)
        setupRealm()
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchingAndroidInjector

    private fun setupRealm() {
        Realm.init(applicationContext)
        val config = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(config)
    }
}
