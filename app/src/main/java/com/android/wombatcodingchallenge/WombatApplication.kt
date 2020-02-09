package com.android.wombatcodingchallenge

import android.app.Application
import com.android.wombatcodingchallenge.data.network.MyApi
import com.android.wombatcodingchallenge.data.network.NetworkConnectionInterceptor
import com.android.wombatcodingchallenge.data.repository.WalletRepository
import com.android.wombatcodingchallenge.ui.wallet.AuthViewModelFactory

import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class WombatApplication : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@WombatApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }
        bind() from singleton { WalletRepository(instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
    }

}