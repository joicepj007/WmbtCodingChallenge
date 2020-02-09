package com.example.myapplication.ui.auth.responses

data class CpuLimit(val used: Int,
                    val available: Int,
                    val max: Int)