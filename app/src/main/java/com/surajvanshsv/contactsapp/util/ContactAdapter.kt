package com.surajvanshsv.contactsapp.util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.surajvanshsv.contactsapp.databinding.ItemContactBinding
import com.surajvanshsv.contactsapp.room.Contact


class ContactAdapter(private val onDelete:(Contact) -> Unit) :
    ListAdapter<Contact, ContactAdapter.ContactViewHolder>(DIFF_CALLBACK) {

        companion object {
            private val DIFF_CALLBACK = object: DiffUtil.ItemCallback<Contact>(){

                override fun areItemsTheSame(oldItem: Contact, newItem: Contact): Boolean =
                    oldItem.id == newItem.id
                override fun areContentsTheSame(oldItem: Contact, newItem: Contact): Boolean =
                    oldItem == newItem
            }
        }

    inner class ContactViewHolder(private val binding: ItemContactBinding):
            RecyclerView.ViewHolder(binding.root){
                fun bind(contact: Contact){
                    binding.nameTextView.text = contact.name
                    binding.phoneTextView.text = contact.phone
                    binding.deleteBtn.setOnClickListener {
                        onDelete(contact)
                    }
                }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val binding = ItemContactBinding.inflate(LayoutInflater.from(
            parent.context),parent,false)

        return ContactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


}