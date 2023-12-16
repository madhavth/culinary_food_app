package com.madhav.culinaryfood.features.about_me.presentation.page

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.madhav.culinaryfood.R
import com.madhav.culinaryfood.databinding.FragmentAboutMeBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class AboutMeFragment : Fragment() {

    companion object {
        fun newInstance() = AboutMeFragment()
    }

    private lateinit var viewModel: AboutMeViewModel
    private lateinit var binding: FragmentAboutMeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[AboutMeViewModel::class.java]
        binding = FragmentAboutMeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
        lifecycleScope.launch {
            viewModel.getCurrentUser().collectLatest {
                user->
                if(user == null) {
                    binding.tvName.setText("No user found")
                    return@collectLatest
                }

                binding.tvName.setText("${user.firstName} ${user.lastName}".uppercase())
            }
        }

        lifecycleScope.launch {
            viewModel.getAboutMe().collectLatest {
                aboutMe->
                if(aboutMe == null) {
                    binding.tvFavoriteRecipes.setText(getString(R.string.i_don_t_discriminate_i_love_all_recipes))
                    binding.tvFoodPhilosophy.setText(getString(R.string.food_philosophy))
                    return@collectLatest
                }

                binding.tvFavoriteRecipes.setText(aboutMe.favoriteRecipes)
                binding.tvFoodPhilosophy.setText(aboutMe.aboutMeDescriptionMotivations)
            }
        }
    }

    private fun bindViews() {
        binding.fabEditDone.setOnClickListener {
            if(viewModel.isEditMode) {
                binding.fabEditDone.setImageResource(R.drawable.ic_edit)
                binding.tvFavoriteRecipes.isClickable = false
                binding.tvFavoriteRecipes.isFocusableInTouchMode =false
                binding.tvFoodPhilosophy.isClickable = false
                binding.tvFoodPhilosophy.isFocusableInTouchMode = false
            } else {
                binding.fabEditDone.setImageResource(R.drawable.ic_done)
                binding.tvFavoriteRecipes.isClickable = true
                binding.tvFavoriteRecipes.isFocusableInTouchMode = true
                binding.tvFoodPhilosophy.isClickable = true
                binding.tvFoodPhilosophy.isFocusableInTouchMode = true
            }
            viewModel.toggleEditMode()

            lifecycleScope.launch {
                viewModel.saveAboutMe(
                    binding.tvFavoriteRecipes.text.toString(),
                    binding.tvFoodPhilosophy.text.toString()
                )
                binding.tvFavoriteRecipes.clearFocus()
                binding.tvFoodPhilosophy.clearFocus()
            }
        }
    }

}