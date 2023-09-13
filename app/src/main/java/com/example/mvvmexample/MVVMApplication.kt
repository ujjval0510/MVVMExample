package com.example.mvvmexample

import android.app.Application
import com.example.mvvmexample.data.db.AppDatabase
import com.example.mvvmexample.data.network.MyAPI
import com.example.mvvmexample.data.network.NetworkConnectionInterceptor
import com.example.mvvmexample.data.repositories.UserRepository
import com.example.mvvmexample.ui.auth.AuthViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

/**
 * Base class of our application
 * It is instantiated before anything else.
 */
class MVVMApplication : Application(), KodeinAware{

    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        // MyAPI class need object of network class that we already bind above so it will take automatically that is the beauty of kodein
        bind() from singleton { MyAPI(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
    }
}