package com.naufalprakoso.researchworkmanager.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.work.WorkInfo
import androidx.work.WorkManager
import com.naufalprakoso.researchworkmanager.databinding.ActivityMainBinding
import com.naufalprakoso.researchworkmanager.utils.count
import com.naufalprakoso.researchworkmanager.utils.workRequestId
import com.naufalprakoso.researchworkmanager.vo.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var adapter: HeroAdapter
    private lateinit var mainBinding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        initAdapter()
        initRecyclerView()
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

    private fun checkWorker() {
        val tag = MainActivity::class.java.name
        workRequestId?.let {
            WorkManager.getInstance(this).getWorkInfoByIdLiveData(it)
                .observe(this, Observer { workInfo ->
                    when (workInfo.state) {
                        WorkInfo.State.ENQUEUED -> Log.d(tag, "checkWorker enqueued in $count")
                        WorkInfo.State.RUNNING -> Log.d(tag, "checkWorker running in $count")
                        WorkInfo.State.SUCCEEDED -> {
                            Log.d(tag, "checkWorker success in $count")
                            observeData()
                        }
                        WorkInfo.State.BLOCKED -> Log.d(tag, "checkWorker blocked in $count")
                        WorkInfo.State.FAILED -> Log.d(tag, "checkWorker failed in $count")
                        else -> Log.d(tag, "checkWorker canceled in $count")
                    }
                })
        }
    }

    private fun observeData() {
        viewModel.getHeroes()?.observe(this, Observer {
            when (it.status) {
                Status.LOADING -> {
                    // Loading
                }
                Status.SUCCESS -> {
                    val data = it.data
                    if (!data.isNullOrEmpty()) {
                        adapter.setHeroes(data)
                        adapter.notifyDataSetChanged()
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(this, "Check your connection", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}