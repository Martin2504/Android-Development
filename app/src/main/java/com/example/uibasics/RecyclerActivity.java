package com.example.uibasics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView contactsRecView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);

        contactsRecView = findViewById(R.id.contactsRecView);

        // Creating some contacts
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact("Margot Robbie", "Margot@gmail.com", "https://i.pinimg.com/736x/d1/ef/30/d1ef30b3fb72ab617b8dd802aac28694.jpg"));
        contacts.add(new Contact("Megan Fox", "Megan@gmail.com", "https://i.ytimg.com/vi/fqWuCsHKZU0/maxresdefault.jpg"));
        contacts.add(new Contact("Madelyn Cline", "Madelyn@gmail.com", "https://glamourfame.com/uploads/biography/2021/8/11/https___hypebeast.com_wp-content_blogs.dir_6_files_2021_03_set-active-madelyn-cline-collaboration-sports-bras-leggings-loungewear-release-date-2-1628691865309.jpg"));

        // Creating recycler view adapter
        ContactsRecViewAdapter adapter = new ContactsRecViewAdapter(this);
        adapter.setContacts(contacts);

        // Setting adapter to recycler view
        contactsRecView.setAdapter(adapter);

        // Setting a layout manager for our recycler view
        contactsRecView.setLayoutManager(new LinearLayoutManager(this));                                         // Sets the items in a linear manner
//        contactsRecView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));       // Sets the items in a horizontal manner
//        contactsRecView.setLayoutManager(new GridLayoutManager(this, 2));                                             // Sets the items in a grid manner
    }
}