package com.android.wombatcodingchallenge.ui.wallet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.wombatcodingchallenge.data.repository.WalletRepository
import com.android.wombatcodingchallenge.util.ApiException
import com.android.wombatcodingchallenge.util.Coroutines
import com.android.wombatcodingchallenge.util.NoInternetException
import com.example.myapplication.ui.auth.AuthRequest
import com.example.myapplication.ui.auth.responses.AuthResponse
import kotlin.math.round


class AuthViewModel(
    private val repository: WalletRepository
) : ViewModel() {
    private var mutableLiveData: MutableLiveData<AuthResponse?> = MutableLiveData()

    /**
     * View model method which does network call and returns the {@link LiveData#AuthResponse} in which the
     * view can observe on it for the data changes
     * */
    fun getWalletDetails(): LiveData<AuthResponse?> {
        Coroutines.main {
            try {
                val authResponse = repository.walletDetails(AuthRequest("genialwombat"))
                authResponse.eosToken = authResponse.core_liquid_balance?.replace("EOS", "")
                authResponse.eosNetWeight =
                    authResponse.total_resources?.net_weight?.replace("EOS", "")
                authResponse.eosCpuWeight =
                    authResponse.total_resources?.cpu_weight?.replace("EOS", "")
                val usd: Double?
                usd = (authResponse.eosToken?.toDoubleOrNull()?.times(4.59))
                authResponse.netProgress =
                    round((authResponse.net_limit?.used)?.div(authResponse.net_limit.max)!! * 100).toInt()
                authResponse.cpuProgress =
                    (authResponse.cpu_limit?.max?.minus(authResponse.cpu_limit.used / authResponse.cpu_limit.max))?.times(
                        100
                    )!!
                authResponse.ramProgress =
                    round((authResponse.ram_usage).div(authResponse.ram_quota!!) * 100).toInt()
                authResponse.netUsed =
                    (authResponse.net_limit.used.div(1000)).toString().replace(".", ",") + " KB"
                authResponse.netMax = (authResponse.net_limit.max.div(
                    1000
                )).toString().replace(".", ",") + " KB"
                authResponse.cpuUsed =
                    (authResponse.cpu_limit.used.div(1000)).toString().replace(".", ",") + " ms"
                authResponse.cpuMax =
                    (authResponse.cpu_limit.max.div(1000)).toString().replace(".", ",") + " ms"
                authResponse.ramUsed =
                    (authResponse.ram_usage.div(1000)).toString().replace(".", ",") + " KB"
                authResponse.ramMax =
                    (authResponse.ram_quota.div(1000)).toString().replace(".", ",") + " KB"
                authResponse.usdValue = "≈ $usd $"
                authResponse.netUsedMax = authResponse.netUsed + "/" + authResponse.netMax
                authResponse.ramUsedMax = authResponse.ramUsed + "/" + authResponse.ramMax
                authResponse.netProgressText=authResponse.netProgress.toString()+"%"
                authResponse.ramProgressText=authResponse.ramProgress.toString()+"%"
                mutableLiveData.value = authResponse
            } catch (e: ApiException) {
                mutableLiveData.value = null
            } catch (e: NoInternetException) {
                mutableLiveData.value = null
            }
        }

        return mutableLiveData
    }
}