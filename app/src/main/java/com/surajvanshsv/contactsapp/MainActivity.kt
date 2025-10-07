package com.surajvanshsv.contactsapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.surajvanshsv.contactsapp.databinding.ActivityMainBinding
import com.surajvanshsv.contactsapp.room.Contact
import com.surajvanshsv.contactsapp.util.ContactAdapter
import com.surajvanshsv.contactsapp.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ContactAdapter

    // viewModels() delegate ensures that the ViewModel is
    // scoped to the current Activity, meaning it will survive
    // configuration changes
    private val viewModel: ContactViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ContactAdapter {
                contact -> viewModel.delete(contact)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        // observe the LiveData returned by getAllContacts()
        // and update the RecyclerView accordingly
        // when the data changes
        viewModel.allContacts.observe(this){
                contacts-> adapter.submitList(contacts)
        }

        // Inserting new contact into ROOM DB
        binding.addButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()

            if(name.isNotEmpty() && phone.isNotEmpty()){
                val contact = Contact(name = name, phone = phone)
                viewModel.insert(contact)
            }

        }



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}