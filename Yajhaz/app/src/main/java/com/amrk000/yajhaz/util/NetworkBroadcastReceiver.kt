package com.amrk000.yajhaz.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class NetworkBroadcastReceiver : BroadcastReceiver {
    private lateinit var dialog: AlertDialog

    constructor()

    constructor(context:Context){
        val alertDialogBuilder = MaterialAlertDialogBuilder(context)
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.setTitle("Disconnected !")
        alertDialogBuilder.setMessage("Please make sure that you are connected to the internet")

        dialog = alertDialogBuilder.create()
    }

    override fun onReceive(context: Context, intent: Intent) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val connected = connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo!!.isConnected



        if(!connected) dialog.show()
        else dialog.dismiss()

    }
}