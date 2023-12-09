package com.madhav.culinaryfood.core.presentation.page

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T: ViewBinding>: AppCompatActivity() {

    private var _binding: T? = null
    val binding: T get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        _binding = setBinding()
        setContentView(binding.root)
    }
    abstract fun setBinding() : T

}