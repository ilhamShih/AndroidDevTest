package com.android.devtest.presentation.fragment.one

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.android.devtest.databinding.FragmentOneBinding
import com.android.devtest.presentation.helper.Helper
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentOne : Fragment(), View.OnClickListener {

    private var _bind: FragmentOneBinding? = null
    private val bind
        get() = _bind ?: throw java.lang.RuntimeException("Error: FragmentOneBinding = null")
    private var flag = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FragmentOneBinding.inflate(inflater, container, false)
        bind.lottieAnim.button1.setOnClickListener(this)
        bind.lottieAnim.button2.setOnClickListener(this)
        bind.lottieAnim.button3.setOnClickListener(this)
        return bind.root
    }

    override fun onStart() {
        super.onStart()
        flag = false
        lifecycleScope.launch {
            bind.progress.linearProgressIndicator.progress = Helper.mainGlobalFloat
            for (i in Helper.mainGlobalInt..100) {
                if (flag) {
                    break
                }
                delay(140)
                Helper.mainGlobalFloat += 0.01f
                Helper.mainGlobalInt = i
                bind.progress.percent.text = i.toString().plus("%")
                bind.progress.linearProgressIndicator.progress = Helper.mainGlobalFloat
            }
        }
    }

    override fun onPause() {
        super.onPause()
        flag = true
        bind.progress.linearProgressIndicator.pauseAnimation()
    }

    override fun onDetach() {
        super.onDetach()
        Helper.mainGlobalFloat = 0F
        Helper.mainGlobalInt = 0
    }

    override fun onClick(p0: View?) {
        when (p0) {
            bind.lottieAnim.button1 -> {
                bind.lottieAnim.linearProgressIndicator.playAnimation()
            }
            bind.lottieAnim.button2 -> {
                bind.lottieAnim.linearProgressIndicator.pauseAnimation()
            }
            bind.lottieAnim.button3 -> {
                bind.lottieAnim.linearProgressIndicator.apply {
                    visibility = if (visibility == View.VISIBLE) View.GONE else View.VISIBLE
                }
            }
        }
    }
}