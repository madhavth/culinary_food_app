package com.madhav.culinaryfood

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
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
        binding.viewPager.adapter = HomeViewPagerAdapter(
            listOf(
                RecipeFragment(),
                MealPlannerFragment(),
                BlogFragment(),
                ContactFragment(),
                AboutMeFragment()
            ), this
        )
        setTabLayoutWithViewPager()
        setTabLayoutWithNavBar()
        binding.viewPager.currentItem = 0
    }

    private fun setTabLayoutWithNavBar() {
        binding.bottomNavBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_recipes -> {
                    binding.viewPager.currentItem = 0
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.menu_blogs -> {
                    binding.viewPager.currentItem = 2
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.menu_contact -> {
                    binding.viewPager.currentItem = 3
                    return@setOnNavigationItemSelectedListener true
                }
            }
            false
        }

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> binding.bottomNavBar.selectedItemId = R.id.menu_recipes
                    2 -> binding.bottomNavBar.selectedItemId = R.id.menu_blogs
                    3 -> binding.bottomNavBar.selectedItemId = R.id.menu_contact
                }
            }
        })
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