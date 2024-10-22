package com.naufalprakoso.researchworkmanager.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.BackoffPolicy
import androidx.work.WorkManager
import androidx.work.ExistingPeriodicWorkPolicy
import com.naufalprakoso.researchworkmanager.databinding.ActivityMainBinding
import com.naufalprakoso.researchworkmanager.utils.UNIQUE_WORK_NAME
import com.naufalprakoso.researchworkmanager.workmanager.worker.SeedDatabaseWorker
import dagger.hilt.android.AndroidEntryPoint
import java.util.UUID
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var adapter: HeroAdapter
    private lateinit var mainBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    private lateinit var workerRequest: PeriodicWorkRequest
    private lateinit var workRequestId: UUID

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initAdapter()
        initRecyclerView()
        initWorkManager()
        checkWorker()
    }

    private fun initAdapter() {
        adapter = HeroAdapter(this)
        adapter.setHasStableIds(true)
    }

    private fun initRecyclerView() {
        mainBinding.rvData.setHasFixedSize(true)
        mainBinding.rvData.setItemViewCacheSize(10)
        mainBinding.rvData.layoutManager = GridLayoutManager(this, 2)
        mainBinding.rvData.adapter = adapter
        mainBinding.rvData.isNestedScrollingEnabled = false
    }

    private fun initWorkManager() {
        // Must be connected to the internet
        val workerConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        // If work request retry the Worker, it will give 1 minutes backoff delay
        workerRequest = PeriodicWorkRequestBuilder<SeedDatabaseWorker>(
            15,
            TimeUnit.MINUTES
        ).setBackoffCriteria(
            BackoffPolicy.LINEAR,
            1,
            TimeUnit.MINUTES
        ).setConstraints(workerConstraints).build()
        workRequestId = workerRequest.id

        // If there is uncompleted work with the same unique name, replace it
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            UNIQUE_WORK_NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            workerRequest
        )
    }

    private fun checkWorker() {
        val tag = MainActivity::class.java.name

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(workerRequest.id)
            .observe(this, Observer { workInfo ->
                when (workInfo.state) {
                    WorkInfo.State.ENQUEUED -> Log.d(tag, "checkWorker enqueued in ${workInfo.runAttemptCount}")
                    WorkInfo.State.RUNNING -> {
                        Log.d(tag, "checkWorker running in ${workInfo.runAttemptCount}")
                        observeData()
                    }
                    WorkInfo.State.SUCCEEDED -> {
                        // Periodic Work Request will never enter this state
                        Log.d(tag, "checkWorker success in ${workInfo.runAttemptCount}")
                    }
                    WorkInfo.State.BLOCKED -> Log.d(tag, "checkWorker blocked in ${workInfo.runAttemptCount}")
                    WorkInfo.State.FAILED -> Log.d(tag, "checkWorker failed in ${workInfo.runAttemptCount}")
                    else -> Log.d(tag, "checkWorker canceled in ${workInfo.runAttemptCount}")
                }
            })
    }

    private fun observeData() {
        viewModel.getHeroes().observe(this, Observer { data ->
            if (!data.isNullOrEmpty()) {
                adapter.setHeroes(data)
                adapter.notifyDataSetChanged()
            }
        })
    }
}