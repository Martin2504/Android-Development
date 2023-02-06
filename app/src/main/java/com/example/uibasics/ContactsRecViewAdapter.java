package com.example.uibasics;
import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


/** The recycler view adapter class **/
public class ContactsRecViewAdapter extends RecyclerView.Adapter<ContactsRecViewAdapter.ViewHolder> {           // The datatype of our adapter is the ViewHolder class.

    // In order to show a list of different contacts inside the RecyclerView, therefore the list
    // of different contacts must be passed to this adapter class
    private ArrayList<Contact> contacts = new ArrayList<>();

    private Context context;

    public ContactsRecViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {     // This method is where I create an instance of my view holder class for every item in the recycler view
        // Creating a view object using the LayoutInflater
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_list_item, parent, false);     // .inflate(address of layout file, view group, boolean)
        /* ViewGroup is the parent of all the layout files (relative/linear/constraint).
        It can be used to group different views inside it   */

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {        // position = position of item inside the recycler view

        holder.txtName.setText(contacts.get(position).getName());   // sets the txtView's Name text as the same as the position in the arrayList
        holder.txtEmail.setText(contacts.get(position).getEmail()); // sets the txtView's Email text as the same as the position in the arrayList

        String newName = contacts.get(position).getName();          // gathering the name for the toast to display

        // Setting listener for the relative layout containing the items.
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, newName, Toast.LENGTH_SHORT).show();
            }
        });

        Glide.with(context)
                .asBitmap()                                         // what file it will be
                .load(contacts.get(position).getImageUrl())         // passing the source of my image
                .into(holder.image);                                // specify the image view in which I am going to show my image


    }

    @Override
    public int getItemCount() {     // Returns the number of items in my adapter
        return contacts.size();
    }

    // Setter for private contacts ArrayList
    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;

        /* After changing the list of different contacts at any time, the
        adapter must be notified that the dataset had been changed.
        This way I can refresh the Recycler View with the new data received/passed to this adapter class. */
        notifyDataSetChanged();

    }


    // This inner class is responsible for generating our view objects.
    // This class will hold the view item for every item inside my recycler view.
    public class ViewHolder extends RecyclerView.ViewHolder {
        // To have access to elements inside my view object they must be added as fields of this inner class
        private TextView txtName;
        private TextView txtEmail;
        private CardView parentLayout;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);  // findViewById() must be used like this since im not inside an activity. The view passed must be used instead.
            parentLayout = itemView.findViewById(R.id.parentLayout);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            image = itemView.findViewById(R.id.image);
        }
    }


}
