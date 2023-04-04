package com.example.starwars.categories.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.starwars.R
import com.example.starwars.categories.domain.model.Category
import com.example.starwars.databinding.CategoriesActivityBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoriesActivity : AppCompatActivity(), IcallDetail {
    //private lateinit var binding: CategoriesActivityBinding
    private val binding by lazy {
        CategoriesActivityBinding.inflate(layoutInflater)
    }

    private val categoriesAdapter: CategoriesAdapter by lazy { CategoriesAdapter() }
    private lateinit var lManager: StaggeredGridLayoutManager
    private val categoriesViewModel: CategoriesViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        lManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        //categoriesViewModel.getCategories()

        setContentView(binding.root)
        setupView()
    }

    private fun setupView() {
        setupRecyclewView()
        setListeners()
        setupObservers()
    }

    private fun setupObservers() {
        categoriesViewModel.categoriesOb.observe(this,
            Observer(::handleCategory)
        )
        categoriesViewModel.categoriesExceptionOb.observe(this,
            Observer(::handleError)
        )
    }

    private fun handleCategory(categories: Array<Category>) {
        categoriesAdapter.setList(categories)
    }

    private fun handleError(exception: Exception?) {
        //TODO showErrorView()
    }

    private fun setupRecyclewView() {
        categoriesAdapter.apply {
            setCallDetail(this@CategoriesActivity)
            setLayoutManager(lManager)
        }
        binding.categoriesList.apply {
            layoutManager = lManager
            adapter = categoriesAdapter
        }
    }

    override fun callDetail(category: Category) {
        // TODO callCategoryDetail(category)
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