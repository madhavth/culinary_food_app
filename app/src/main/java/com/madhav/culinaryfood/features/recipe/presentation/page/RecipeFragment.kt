package com.madhav.culinaryfood.features.recipe.presentation.page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.madhav.culinaryfood.RecipeDetailActivity
import com.madhav.culinaryfood.databinding.FragmentRecipeBinding
import com.madhav.culinaryfood.features.recipe.data.RecipeAdapter
import com.madhav.culinaryfood.features.recipe.presentation.view_models.RecipeViewModel
import com.madhav.culinaryfood.features.recipe.presentation.views.AddRecipeDialogFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RecipeFragment : Fragment() {

    private lateinit var binding: FragmentRecipeBinding
    private val viewModel: RecipeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvRecipe.adapter = RecipeAdapter(listOf()) {
            recipeModel ->
             startActivity(RecipeDetailActivity.getIntent(requireContext(), recipeModel))
        }
        binding.rvRecipe.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            true
        )
        lifecycleScope.launch {
            viewModel.getRecipesListFlow().collectLatest {
                (binding.rvRecipe.adapter as RecipeAdapter).submitList(it)
            }
        }
        binding.fabAdd.setOnClickListener {
            AddRecipeDialogFragment {
                lifecycleScope.launch {
                    viewModel.addRecipeToList(it)
                }
            }.show(childFragmentManager, "AddRecipeDialogFragment")
        }
    }

    companion object {
    }
}