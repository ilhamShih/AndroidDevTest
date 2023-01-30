package com.android.devtest.presentation.fragment.two

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Context
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.devtest.databinding.FragmentTwoBinding
import com.android.devtest.presentation.MainViewModel
import com.android.devtest.presentation.adapter.AdapterRaitingsIthems
import com.android.devtest.presentation.helper.Helper.onGenerator
import com.android.devtest.presentation.helper.Helper.permissionSave
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class FragmentTwo : Fragment() {

    private lateinit var adapterRaitingsIthems: AdapterRaitingsIthems
    private lateinit var mMainViewModel: MainViewModel
    private var progressOneJob: Job? = null
    private var progressTwoJob: Job? = null
    private var _bind: FragmentTwoBinding? = null
    private lateinit var mContext: Context
    private var launcherPermission: ActivityResultLauncher<String>? = null
    private val bind
        get() = _bind
            ?: throw java.lang.RuntimeException("Error: FragmentTwoBinding = null")
    private var click = false


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        adapterRaitingsIthems = AdapterRaitingsIthems(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = FragmentTwoBinding.inflate(inflater, container, false)
        mMainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        launcherPermission =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) {
                when (it) {
                    true -> {
                        mObserve()
                        mContext.permissionSave = true
                    }
                    else -> {
                        mContext.permissionSave = false
                    }
                }
            }
        bind.recyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        timer()
        bind.randomProgress.randomize.setOnClickListener {
            startProgress()
        }
    }

    fun setRecyclerView() {
        bind.recyclerView.apply {
            adapter = adapterRaitingsIthems
        }

    }

    override fun onStart() {
        super.onStart()
        if (mContext.permissionSave){
            mObserve()
        }else{
            launcherPermission?.launch(READ_EXTERNAL_STORAGE)
        }
    }


    fun startProgress() {
        val random: Set<Long> = onGenerator()
        if (!click) {
            click = true
            progressOneJob = lifecycleScope.launch {
                progressOne(random.first())
            }
            progressTwoJob = lifecycleScope.launch {
                progressTwo(random.last())
            }
        } else {
            click = false
            progressOneJobCancel(true)
            progressTwoJobCancel(true)

        }
    }



    fun timer() {
        val mHours = TimeUnit.MINUTES.toMillis(60)
        val countDownTimer = object : CountDownTimer(mHours, 1000) {
            override fun onTick(millis: Long) {
                val h = TimeUnit.MILLISECONDS.toSeconds(millis) / (60 * 60) % 24
                val m = TimeUnit.MILLISECONDS.toSeconds(millis) / 60 % 60
                val s = TimeUnit.MILLISECONDS.toSeconds(millis) % 60
                bind.timer.hours.text = String.format("%02d", h)
                bind.timer.minutes.text = String.format("%02d", m)
                bind.timer.seconds.text = String.format("%02d", s)
            }

            override fun onFinish() {

            }
        }
        countDownTimer.start()
    }

    suspend fun progressOne(first: Long) {
        for (i in 0..100) {
            delay(first)
            bind.randomProgress.rendomVelueOne.text = "$i%"
            bind.randomProgress.progressOneTrack.setProgressCompat(i, true)
            if (i == 100) {
                progressOneJobCancel()
            }
        }
    }

    suspend fun progressTwo(last: Long) {
        for (i in 0..100) {
            delay(last)
            bind.randomProgress.rendomVelueTwo.text = "$i%"
            bind.randomProgress.progressTwoTrack.setProgressCompat(i, true)
            if (i == 100) {
                progressTwoJobCancel()
            }
        }
    }

    fun progressOneJobCancel(boolean: Boolean = false) {
        progressOneJob?.cancel()
        progressOneJob = null
        if (boolean) {
            bind.randomProgress.progressOneTrack.progress = 0
            bind.randomProgress.rendomVelueOne.text = "0%"
            bind.randomProgress.progressOneTrack.invalidate()
        }
    }

    fun progressTwoJobCancel(boolean: Boolean = false) {
        progressTwoJob?.cancel()
        progressTwoJob = null
        if (boolean) {
            bind.randomProgress.progressTwoTrack.progress = 0
            bind.randomProgress.rendomVelueTwo.text = "0%"
            bind.randomProgress.progressTwoTrack.invalidate()
        }
    }


    fun mObserve() {
        mMainViewModel.progeressLoader.observe(viewLifecycleOwner) { p0 ->
            if (p0 in 0..95) {
                bind.serverProgress.progressServerTrack.setProgressCompat(p0, true)
                bind.serverProgress.downloadProcessVelue.text = "$p0%"
            } else {
                bind.serverProgress.progressServerTrack.setProgressCompat(100, true)
                bind.serverProgress.downloadProcessVelue.text = "100%"
                setRecyclerView()
            }

        }
        mMainViewModel.getJsonIthem.observe(viewLifecycleOwner) {
            adapterRaitingsIthems.submitList(it)
            mMainViewModel.getImage



        }
    }

    override fun onStop() {
        super.onStop()
        mMainViewModel.progeressLoader.removeObservers(viewLifecycleOwner)
    }

}