package com.android.wombatcodingchallenge.data.repository

import com.android.wombatcodingchallenge.data.network.MyApi
import com.android.wombatcodingchallenge.data.network.SafeApiRequest
import com.example.myapplication.ui.auth.AuthRequest
import com.example.myapplication.ui.auth.responses.AuthResponse


class WalletRepository(
    private val api: MyApi
) : SafeApiRequest() {

    /**
     * Method which takes the {@link AuthRequest},does network call.
     * @param authRequest model class for the request
     * @return {@link AuthResponse} model
     * */
    suspend fun walletDetails(authRequest: AuthRequest): AuthResponse {
        return apiRequest { api.walletDetails(authRequest) }
    }


}