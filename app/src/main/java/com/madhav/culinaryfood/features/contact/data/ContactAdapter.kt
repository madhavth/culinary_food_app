package com.madhav.culinaryfood.features.contact.data

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.madhav.culinaryfood.core.data.models.ContactInfoModel
import com.madhav.culinaryfood.databinding.ItemContactChefBinding

class ContactAdapter(): ListAdapter<ContactInfoModel, ContactAdapter.ContactViewHolder>(ContactDiffCallBack()) {

    inner class ContactViewHolder(val binding: ItemContactChefBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(contactInfoModel: ContactInfoModel) {
            binding.tvEmailContact.text= contactInfoModel.contactEmail
            binding.tvNameContact.text = contactInfoModel.contactName
            binding.tvPhoneContact.text = contactInfoModel.contactPhone
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ContactAdapter.ContactViewHolder {
        return ContactViewHolder(ItemContactChefBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false))
    }

    override fun onBindViewHolder(holder: ContactAdapter.ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}

class ContactDiffCallBack: DiffUtil.ItemCallback<ContactInfoModel>() {
    override fun areItemsTheSame(oldItem: ContactInfoModel, newItem: ContactInfoModel): Boolean {
        return oldItem.contactId == newItem.contactId
    }

    override fun areContentsTheSame(oldItem: ContactInfoModel, newItem: ContactInfoModel): Boolean {
        return oldItem == newItem
    }

}
