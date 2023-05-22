package com.example.latihanroom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.latihanroom.databinding.ActivityAddUserBinding;

public class AddUserActivity extends AppCompatActivity {
    private ActivityAddUserBinding binding;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        binding = ActivityAddUserBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        /**
         * Initialize database
         * allow main thread queries
         */
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "userdb").allowMainThreadQueries().build();

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.name = binding.name.getText().toString();
                user.email = binding.email.getText().toString();
                user.address = binding.address.getText().toString();
                insertData(user);
            }
        });
    }

    private void insertData(User user){
        db.userDao().insert(user);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}