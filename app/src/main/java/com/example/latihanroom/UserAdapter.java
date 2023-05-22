package com.example.latihanroom;


import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private ArrayList<User> localDataSet;
    private AppDatabase db;

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public UserAdapter(ArrayList<User> dataSet, AppDatabase db) {
        localDataSet = dataSet;
        this.db = db;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.user_item, viewGroup, false);

        return new ViewHolder(view);
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvUserName,tvUserEmail,tvUserAddress;
        private final ImageButton btnDelete;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            tvUserName = view.findViewById(R.id.name);
            tvUserEmail = view.findViewById(R.id.email);
            tvUserAddress = view.findViewById(R.id.address);
            btnDelete = view.findViewById(R.id.btn_delete);
        }

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        User user = localDataSet.get(position);

        viewHolder.tvUserName.setText(user.name);
        viewHolder.tvUserEmail.setText(user.email);
        viewHolder.tvUserAddress.setText(user.address);

        viewHolder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localDataSet.remove(viewHolder.getAdapterPosition());
                notifyItemRemoved(viewHolder.getAdapterPosition());


                db.userDao().delete(user);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return localDataSet.size();
    }
}

