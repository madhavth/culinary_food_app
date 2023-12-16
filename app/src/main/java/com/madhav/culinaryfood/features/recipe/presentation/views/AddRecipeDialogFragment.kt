package com.madhav.culinaryfood.features.recipe.presentation.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import com.madhav.culinaryfood.core.data.models.RecipeModel
import com.madhav.culinaryfood.databinding.LayoutAlertAddRecipeBinding
import com.madhav.culinaryfood.features.login.data.data_sources.LoginDataSource
import kotlinx.coroutines.launch

class AddRecipeDialogFragment(val addedCallback: (RecipeModel)-> Unit) :DialogFragment() {


    private lateinit var binding: LayoutAlertAddRecipeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutAlertAddRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCancel.setOnClickListener {
            dismiss()
        }

        lifecycleScope.launch {
            val currentUser = LoginDataSource().getCurrentUser()
            binding.btnAdd.setOnClickListener {
                if(binding.etTitle.text.toString().isBlank() ||
                    binding.etAddIngredients.text.toString().isBlank() ||
                    binding.etInstructions.text.toString().isBlank() ||
                    binding.etCookingTime.text.toString().isBlank()
                ) {
                    Snackbar.make(binding.root, "Please fill all the fields", Snackbar.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                addedCallback(
                    RecipeModel(
                        recipeName = binding.etTitle.text.toString(),
                        ingredients = binding.etAddIngredients.text.toString(),
                        instructions = binding.etInstructions.text.toString(),
                        "",
                        "",
                        binding.etCookingTime.text.toString() + " mins to cook",
                        listOf(),
                        recipeId = System.currentTimeMillis().toString(),
                    )
                )
                dismiss()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        super.onStart()
        val width: Int = (resources.displayMetrics.widthPixels * 0.95).toInt()
        val height: Int = (resources.displayMetrics.heightPixels * 0.95).toInt()
        dialog?.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
        //THIS WILL MAKE WIDTH 90% OF SCREEN
        //HEIGHT WILL BE WRAP_CONTENT
        //getDialog().getWindow().setLayout(width, height);
    }

}