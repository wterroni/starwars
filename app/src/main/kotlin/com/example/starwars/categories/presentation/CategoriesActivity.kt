package com.example.starwars.categories.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.starwars.R
import com.example.starwars.categories.domain.model.Category
import com.example.starwars.databinding.CategoriesActivityBinding

class CategoriesActivity : AppCompatActivity(), ICallCategoryDetail {
    /*private val binding by lazy {
        CategoriesActivityBinding.inflate(layoutInflater)
    }*/

    private lateinit var binding: CategoriesActivityBinding

    private lateinit var charatersPagerAdapter: StarWarsPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CategoriesActivityBinding.inflate(layoutInflater)

        setContentView(binding.root)

        charatersPagerAdapter = StarWarsPageAdapter(supportFragmentManager)
        charatersPagerAdapter.add(AllCategoriesFragment(), resources.getString(R.string.all))
        charatersPagerAdapter.add(
            FavoriteCategoriesFragment(),
            resources.getString(R.string.favorite)
        )
        binding.pager.adapter = charatersPagerAdapter


    }

    override fun callCategoryDetail(categoriesVO: Category) {
        TODO("Not yet implemented")
    }
}

class StarWarsPageAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val listFragments = ArrayList<Fragment>()
    private val listFragmentsTitle = ArrayList<String>()

    override fun getCount(): Int = listFragments.size

    fun add(frag: Fragment, title: String) {
        listFragments.add(frag)
        listFragmentsTitle.add(title)
    }

    override fun getItem(position: Int): Fragment {
        return listFragments[position];
    }

    override fun getPageTitle(position: Int): CharSequence {
        return listFragmentsTitle[position]
    }
}