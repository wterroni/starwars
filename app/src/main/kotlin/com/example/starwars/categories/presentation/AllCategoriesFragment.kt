package com.example.starwars.categories.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.starwars.R
import com.example.starwars.categories.domain.model.Category
import com.example.starwars.component.InfoViewState
import com.example.starwars.databinding.FragmentAllCategoriesBinding
import com.example.starwars.detail.presentation.DetailActivity
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllCategoriesFragment : Fragment(), ICallCategoryDetail, IFavoriteHandle {
    private lateinit var binding: FragmentAllCategoriesBinding

    private val categoriesAdapter: CategoriesAdapter by lazy { CategoriesAdapter() }
    private lateinit var lManager: StaggeredGridLayoutManager
    private val categoriesViewModel: CategoriesViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_all_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentAllCategoriesBinding.bind(view)
        lManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        setupView()
    }


    private fun setupView() {
        setupRecyclerView()
        setListeners()
        setupObservers()
    }

    private fun setupObservers() {
        categoriesViewModel.categoriesOb.observe(
            viewLifecycleOwner,
            Observer(::handleCategory)
        )
        categoriesViewModel.loadingOB.observe(
            viewLifecycleOwner,
            Observer(::handleLoading)
        )
        categoriesViewModel.categoriesExceptionOb.observe(
            viewLifecycleOwner,
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

    private fun handleCategory(categories: List<Category>) {
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
            setCallDetail(this@AllCategoriesFragment)
            setFavoriteHandle(this@AllCategoriesFragment)
            setLayoutManager(lManager)
        }
        binding.categoriesList.apply {
            layoutManager = lManager
            adapter = categoriesAdapter
        }
    }

    override fun callCategoryDetail(category: Category) {
        startActivity(DetailActivity.newInstance(this.requireActivity().applicationContext, category.categoryType))
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

    override fun saveFavorite(categoryModel: Category) {
        categoryModel.isFavority = true
        categoriesViewModel.saveFavorite(
            categoryModel
        )
    }

    override fun deleteFavorite(categoryModel: Category) {
        categoriesViewModel.removeFavorite(
            categoryModel
        )
    }
}