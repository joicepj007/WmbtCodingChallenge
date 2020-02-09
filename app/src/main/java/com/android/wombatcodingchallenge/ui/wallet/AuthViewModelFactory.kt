package com.android.wombatcodingchallenge.ui.wallet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.wombatcodingchallenge.data.repository.WalletRepository

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(
    private val repository: WalletRepository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository) as T
    }
}