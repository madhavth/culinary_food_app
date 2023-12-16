package com.madhav.culinaryfood.features.blog.presentation.page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.madhav.culinaryfood.core.presentation.views.AddBlogAlertDialogFragment
import com.madhav.culinaryfood.databinding.FragmentBlogBinding
import com.madhav.culinaryfood.features.blog.data.BlogRecyclerAdapter
import com.madhav.culinaryfood.features.blog.presentation.view_models.BlogViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class BlogFragment : Fragment() {

    private lateinit var binding: FragmentBlogBinding
    private val blogViewModel by viewModels<BlogViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBlogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()
    }

    private fun bindViews() {
        binding.rvBlogs.adapter = BlogRecyclerAdapter(listOf())
        binding.rvBlogs.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, true)
        binding.fabAdd.setOnClickListener {
            val dialog = AddBlogAlertDialogFragment { blog ->
                lifecycleScope.launch {
                    blogViewModel.addBlog(blog)
                }
            }
            dialog.show(childFragmentManager, "AddBlogAlertDialogFragment")
        }

        lifecycleScope.launch {
            blogViewModel.getAllBlogs().collectLatest {
                if(it == null) return@collectLatest
                (binding.rvBlogs.adapter as BlogRecyclerAdapter).submitList(it ?: listOf())
            }
        }
    }

    companion object {
    }
}