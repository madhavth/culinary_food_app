package com.madhav.culinaryfood.features.contact.presentation.page

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.madhav.culinaryfood.R
import com.madhav.culinaryfood.databinding.FragmentContactBinding
import com.madhav.culinaryfood.features.contact.data.ContactAdapter
import com.madhav.culinaryfood.features.contact.presentation.view_models.ContactViewModel
import com.madhav.culinaryfood.features.contact.presentation.views.EmailWriteDialogFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class ContactFragment : Fragment() {

    private lateinit var binding: FragmentContactBinding
    private val viewModel by viewModels<ContactViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentContactBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val contactAdapter = ContactAdapter { email ->
            EmailWriteDialogFragment { title, body ->
                sendEmail(email, title, body)
            }.show(childFragmentManager, "emailFragment")
        }
        binding.rvChefContact.adapter = contactAdapter

        lifecycleScope.launch {
            viewModel.getListOfContacts().collectLatest { list ->
                contactAdapter.submitList(list)
            }
        }
    }

    private fun sendEmail(email: String, subject: String, description: String) {
        val emailIntent = Intent(Intent.ACTION_SEND)
        emailIntent.type = "text/plain"
        emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        emailIntent.putExtra(Intent.EXTRA_CC, arrayOf(""))
        emailIntent.putExtra(Intent.EXTRA_BCC, arrayOf(""))
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        emailIntent.putExtra(Intent.EXTRA_TEXT, description)
        context?.startActivity(Intent.createChooser(emailIntent, "Choose an email client"))
    }

    companion object {
    }
}