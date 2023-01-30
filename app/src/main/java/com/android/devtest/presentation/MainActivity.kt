package com.android.devtest.presentation

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.android.devtest.R
import com.android.devtest.databinding.ActivityMainBinding
import com.android.devtest.presentation.helper.CastomView
import com.google.android.material.card.MaterialCardView


class MainActivity : AppCompatActivity() {
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private lateinit var view: View
    private lateinit var _bind: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var navController: NavController
    private lateinit var closeDialog: MaterialCardView
    private lateinit var customDialogs: CastomView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _bind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_bind.root)

        customDialog()
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        navController = findNavController(R.id.host_view)

        _bind.fragmentNext.setOnClickListener {
            navController.navigate(R.id.action_fragment_one_to_fragment_two2)
        }

        _bind.fragmentDialog.setOnClickListener {
            dialog.show()
        }

        closeDialog.setOnClickListener {
            dialog.cancel()

        }
        /**------ navController Listener -----*/
        navController.addOnDestinationChangedListener { _, destination, _ ->
            if (destination.label!! == "fragment_two") {
                _bind.fragmentNext.visibility = GONE
                _bind.fragmentDialog.visibility = GONE
            } else {
                _bind.fragmentNext.visibility = VISIBLE
                _bind.fragmentDialog.visibility = VISIBLE
            }
        }
    }

    /**------ Custom dialogue -----*/
    private fun customDialog() {
        view = this.layoutInflater.inflate(R.layout.custom_dialog, null)
        customDialogs = CastomView(this)
        customDialogs = view.findViewById<View>(R.id.myView) as CastomView
        closeDialog = view.findViewById<View>(R.id.close) as MaterialCardView

        builder = AlertDialog.Builder(this@MainActivity)
        builder.setCancelable(false)
        builder.setView(view)
        dialog = builder.create()
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.window!!.setBackgroundDrawableResource(android.R.color.transparent)
    }
}