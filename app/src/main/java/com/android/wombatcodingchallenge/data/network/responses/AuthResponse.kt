package com.example.myapplication.ui.auth.responses

import com.android.wombatcodingchallenge.data.network.responses.TotalResources
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AuthResponse(
    val core_liquid_balance: String?,
    val ram_quota: Double?,
    val net_weight: Int?,
    val cpu_weight: Int?,
    val ram_usage: Double,
    var eosToken: String?,
    var eosNetWeight: String?,
    var eosCpuWeight: String?,
    var usdValue: String?,
    var netProgress: Int?,
    var cpuProgress: Int,
    var ramProgress: Int?,
    var netUsed: String?,
    var netMax: String?,
    var ramUsed: String?,
    var ramMax: String?,
    var cpuUsed: String?,
    var cpuMax: String?,
    var netUsedMax: String?,
    var ramUsedMax: String?,
    var netProgressText: String?,
    var ramProgressText: String?,
    @Expose
    @SerializedName("net_limit")
    val net_limit: NetLimit?,
    @Expose
    @SerializedName("cpu_limit")
    val cpu_limit: CpuLimit?,
    @Expose
    @SerializedName("total_resources")
    val total_resources: TotalResources?
)