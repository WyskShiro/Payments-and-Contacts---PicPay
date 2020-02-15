package will.shiro.desafiopicpay

import androidx.multidex.MultiDexApplication
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.realm.Realm
import io.realm.RealmConfiguration
import will.shiro.desafiopicpay.util.di.NAMED_ENCRYPTION_KEY
import will.shiro.desafiopicpay.util.di.component.DaggerAppComponent
import javax.inject.Inject
import javax.inject.Named

class PicPayApplication : MultiDexApplication(), HasAndroidInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    @Named(NAMED_ENCRYPTION_KEY)
    lateinit var encryptionKey: ByteArray

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
//            .encryptionKey(encryptionKey)
            .build()
        Realm.setDefaultConfiguration(config)
    }
}
