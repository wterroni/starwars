package com.example.starwars.detail.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.starwars.R
import com.example.starwars.categories.domain.model.CategoryType
import com.example.starwars.databinding.DetailActivityBinding
import com.example.starwars.detail.domain.model.Detail
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity(), ICallDetail {
    private val binding by lazy {
        DetailActivityBinding.inflate(layoutInflater)
    }

    private val categoryType by lazy {
        intent.getStringExtra(BUNDLE_CATEGORY_TYPE) ?: ""
    }

    private val detailAdapter: DetailAdapter by lazy { DetailAdapter() }
    private lateinit var lManager: StaggeredGridLayoutManager
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        //categoriesViewModel.getCategories()

        setContentView(binding.root)
        setupView()
        detailViewModel.getDetail(categoryType)
    }

    private fun setupView() {
        setupRecyclewView()
        setListeners()
        setupObservers()
    }

    private fun setupObservers() {
        detailViewModel.detailOb.observe(this,
            Observer(::handleDetail)
        )
        detailViewModel.detailExceptionOb.observe(this,
            Observer(::handleError)
        )
    }

    private fun handleDetail(detail: List<Detail>) {
        binding.titleTextView.text = categoryType
        detailAdapter.setList(detail)
    }

    private fun handleError(exception: Exception?) {
        //TODO showErrorView()
    }

    private fun setupRecyclewView() {
        detailAdapter.apply {
            setCallDetail(this@DetailActivity)
            setLayoutManager(lManager)
        }
        binding.categoriesList.apply {
            layoutManager = lManager
            adapter = detailAdapter
        }
    }

    private fun setListeners() {
        binding.changeListBtn.setOnClickListener {
            if (lManager.spanCount == 1) {
                lManager.spanCount = 2
                binding.changeListBtn.setImageResource(R.drawable.ic_list_grid)
            } else {
                lManager.spanCount = 1
                binding.changeListBtn.setImageResource(R.drawable.ic_grid_list)
            }
            detailAdapter.notifyItemRangeChanged(0, detailAdapter.itemCount ?: 0)
        }
    }

    override fun callDetail(detail: Detail) {
        TODO("Not yet implemented")
    }

    companion object {
        const val BUNDLE_CATEGORY_TYPE = "BUNDLE_CATEGORY_TYPE"

        fun newInstance(context: Context, categoryType: CategoryType) = Intent(
            context,
            DetailActivity::class.java
        ).apply {
            putExtra(BUNDLE_CATEGORY_TYPE, categoryType.name)
        }
    }
}