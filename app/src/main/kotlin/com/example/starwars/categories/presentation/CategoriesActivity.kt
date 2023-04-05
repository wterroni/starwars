package com.example.starwars.categories.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.starwars.R
import com.example.starwars.categories.domain.model.Category
import com.example.starwars.component.InfoViewState
import com.example.starwars.databinding.CategoriesActivityBinding
import com.example.starwars.detail.presentation.DetailActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesActivity : AppCompatActivity(), ICallCategoryDetail {
    private val binding by lazy {
        CategoriesActivityBinding.inflate(layoutInflater)
    }

    private val categoriesAdapter: CategoriesAdapter by lazy { CategoriesAdapter() }
    private lateinit var lManager: StaggeredGridLayoutManager
    private val categoriesViewModel: CategoriesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        setupRecyclerView()
        setListeners()
        setupObservers()
    }

    private fun setupObservers() {
        categoriesViewModel.categoriesOb.observe(
            this,
            Observer(::handleCategory)
        )
        categoriesViewModel.loadingOB.observe(
            this,
            Observer(::handleLoading)
        )
        categoriesViewModel.categoriesExceptionOb.observe(
            this,
            Observer(::handleError)
        )
    }

    private fun handleLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.shimmerViewContainer.visibility = View.VISIBLE
            binding.shimmerViewContainer.startShimmer()
            binding.categoriesList.visibility = View.GONE
            binding.infoView.visibility = View.GONE
        } else {
            binding.shimmerViewContainer.visibility = View.GONE
            binding.shimmerViewContainer.stopShimmer()
        }
    }

    private fun handleCategory(categories: Array<Category>) {
        binding.categoriesList.visibility = View.VISIBLE
        categoriesAdapter.setList(categories)
    }

    private fun handleError(exception: Exception?) {
        binding.infoView.visibility = View.VISIBLE
        binding.infoView.setState(
            InfoViewState(
                iconRes = R.drawable.reload,
                title = getString(R.string.title_error),
                description = getString(R.string.description_error),
                iconAction = categoriesViewModel::retryCategories
            )
        )
    }

    private fun setupRecyclerView() {
        categoriesAdapter.apply {
            setCallDetail(this@CategoriesActivity)
            setLayoutManager(lManager)
        }
        binding.categoriesList.apply {
            layoutManager = lManager
            adapter = categoriesAdapter
        }
    }

    override fun callCategoryDetail(category: Category) {
        startActivity(DetailActivity.newInstance(this, category.categoryType))
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
            categoriesAdapter.notifyItemRangeChanged(0, categoriesAdapter.itemCount ?: 0)
        }
    }
}