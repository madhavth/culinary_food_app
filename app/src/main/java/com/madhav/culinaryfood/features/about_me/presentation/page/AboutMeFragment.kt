package com.madhav.culinaryfood.features.about_me.presentation.page

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.madhav.culinaryfood.R
import com.madhav.culinaryfood.databinding.FragmentAboutMeBinding

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

    }

}