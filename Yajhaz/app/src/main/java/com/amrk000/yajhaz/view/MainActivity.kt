package com.amrk000.yajhaz.view


import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.BuildCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigation
import com.amrk000.yajhaz.BuildConfig
import com.amrk000.yajhaz.R
import com.amrk000.yajhaz.databinding.ActivityMainBinding
import com.amrk000.yajhaz.util.NetworkBroadcastReceiver
import com.amrk000.yajhaz.util.SharedPrefManager
import com.amrk000.yajhaz.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //view binding init
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())

        //window init
        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, 0)
            insets
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragmentsContainer)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, 0)
            insets
        }

        //view model init
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //nav controller init
        navController = Navigation.findNavController(this, R.id.fragmentsContainer)

        //Network Checker
        registerReceiver(NetworkBroadcastReceiver(this), IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))

        //check if user signed in (sign out if not)
        if(!viewModel.isUserSignedIn()) navController.navigate(
            R.id.action_home_to_signIn,
            null,
            NavOptions.Builder().setPopUpTo(R.id.home, true).build()
            )

    }


}