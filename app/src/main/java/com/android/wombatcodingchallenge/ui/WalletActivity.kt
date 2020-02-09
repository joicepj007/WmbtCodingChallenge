package com.android.wombatcodingchallenge.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.android.wombatcodingchallenge.R
import com.android.wombatcodingchallenge.databinding.ActivityWalletBinding
import com.android.wombatcodingchallenge.ui.wallet.AuthViewModel
import com.android.wombatcodingchallenge.ui.wallet.AuthViewModelFactory
import com.android.wombatcodingchallenge.util.hide
import com.android.wombatcodingchallenge.util.show
import com.example.myapplication.ui.auth.responses.AuthResponse
import kotlinx.android.synthetic.main.activity_wallet.*
import kotlinx.android.synthetic.main.resource_view.view.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class WalletActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()
    private lateinit var binding: ActivityWalletBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding= DataBindingUtil.setContentView(this, R.layout.activity_wallet)

        val viewModel = ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)

        progress_bar.show()
        viewModel.getWalletDetails().observe(this, Observer { authResponse ->
            if(authResponse!=null){
                progress_bar.hide()
                binding.response=authResponse
                init(authResponse)
            }
        })
    }

    private fun init(authResponse: AuthResponse) {
        authResponse.netProgress?.let { binding.root.progressBar.setProgress(it) }
        //authResponse.cpuProgress.let { binding.root.cpu_pb.setProgress(it) }
        authResponse.ramProgress?.let { binding.root.ram_pb.progress = it }

    }


}


