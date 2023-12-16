package com.madhav.culinaryfood

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.madhav.culinaryfood.databinding.ActivityHomeBinding
import com.madhav.culinaryfood.features.about_me.presentation.page.AboutMeFragment
import com.madhav.culinaryfood.features.blog.presentation.page.BlogFragment
import com.madhav.culinaryfood.features.contact.presentation.page.ContactFragment
import com.madhav.culinaryfood.features.home.presentation.view_models.HomeViewModel
import com.madhav.culinaryfood.features.home.presentation.views.HomeViewPagerAdapter
import com.madhav.culinaryfood.features.meal_planner.presentation.page.MealPlannerFragment
import com.madhav.culinaryfood.features.recipe.presentation.page.RecipeFragment

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        bindViews()
    }

    private fun bindViews() {
        binding.viewPager.adapter = HomeViewPagerAdapter(listOf(
            RecipeFragment(),
            MealPlannerFragment(),
            BlogFragment(),
            ContactFragment(),
            AboutMeFragment()
        ), this)
        setTabLayoutWithViewPager()
        setTabLayoutWithNavBar()
    }

    private fun setTabLayoutWithNavBar() {
//        binding.bottomNavBar.selectedItemId =
//        binding.bottomNavBar.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.recipe -> binding.viewPager.currentItem = 0
//                R.id.meal_plan -> binding.viewPager.currentItem = 1
//                R.id.blog -> binding.viewPager.currentItem = 2
//                R.id.contact -> binding.viewPager.currentItem = 3
//                R.id.about_me -> binding.viewPager.currentItem = 4
//            }
//            true
//        }
    }

    private fun setTabLayoutWithViewPager() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when (position) {
                0 -> "Recipes"
                1 -> "Meal Plan"
                2 -> "Blogs"
                3 -> "Contact"
                4 -> "About Me"
                else -> "Other $position"
            }
        }.attach()
    }

    companion object {
        fun getIntent(activity: Activity): Intent {
            return Intent(activity, HomeActivity::class.java)
        }
    }


}