package com.example.hilt.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.hilt.adapter.RepoAdapter
import com.example.hilt.databinding.ActivityMainBinding
import com.example.hilt.model.ActivityResponse
import com.example.hilt.utils.Status
import com.example.hilt.viewModels.HiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModel: HiltViewModel by viewModels()
    private lateinit var redoAdapter: RepoAdapter
    private lateinit var binding: ActivityMainBinding
    private var mList: ArrayList<ActivityResponse> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel.getRepo()
        initObserve()
        refreshLayout()
        setContentView(binding.root)
    }

    private fun refreshLayout() = with(binding) {
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.getRepo()
            swipeRefreshLayout.isRefreshing = false
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun adapterSetup() {
        redoAdapter = RepoAdapter(this, mList)
        binding.rvRepo.layoutManager = LinearLayoutManager(this)
        binding.rvRepo.adapter = redoAdapter
        redoAdapter.notifyDataSetChanged()

    }


    private fun initObserve() {
        viewModel.resultUser.observe(this) {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvRepo.visibility = View.VISIBLE
                    mList.addAll(listOf(it.data!!))
                    adapterSetup()
                }

                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.rvRepo.visibility = View.GONE
                }

                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    binding.rvRepo.visibility = View.VISIBLE
                }

            }

        }
    }
}