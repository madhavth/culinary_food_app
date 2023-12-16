package com.madhav.culinaryfood.features.meal_planner.presentation.page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.madhav.culinaryfood.R
import com.madhav.culinaryfood.core.data.helpers.FormattingHelper
import com.madhav.culinaryfood.core.data.models.MealPlanModel
import com.madhav.culinaryfood.databinding.FragmentMealPlannerBinding
import com.madhav.culinaryfood.databinding.LayoutAlertAddMealPlanBinding
import com.madhav.culinaryfood.features.meal_planner.data.MealPlannerAdapter
import com.madhav.culinaryfood.features.meal_planner.presentation.view_models.MealPlannerViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId


class MealPlannerFragment : Fragment() {

    private lateinit var binding: FragmentMealPlannerBinding
    private val mealPlannerViewModel by viewModels<MealPlannerViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMealPlannerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMealsPlanned.adapter = MealPlannerAdapter(listOf()) {}
        lifecycleScope.launch {
            mealPlannerViewModel.getMealPlans().collectLatest {
                (binding.rvMealsPlanned.adapter as MealPlannerAdapter).submitList(it)
                binding.rvMealsPlanned.visibility = if(it.isNullOrEmpty()) View.GONE else View.VISIBLE
                binding.tvEmpty.visibility = if(it.isNullOrEmpty()) View.VISIBLE else View.GONE
            }
        }
        binding.fabAdd.setOnClickListener {
            mealPlannerViewModel.addNewMealPlanClicked(it)
        }
    }

    companion object {
    }
}