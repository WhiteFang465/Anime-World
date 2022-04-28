package com.atulj.portfolioapp;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.atulj.portfolioapp.databinding.ActivityEditBinding;
import com.atulj.portfolioapp.db.AppDatabase;
import com.atulj.portfolioapp.db.dao.UserDao;
import com.atulj.portfolioapp.db.entities.User;
import com.google.android.material.textfield.TextInputLayout;

public class EditActivity extends AppCompatActivity {
    private TextInputLayout username;
    private TextInputLayout password;
    private TextInputLayout firstName;
    private TextInputLayout lastName;
    private TextInputLayout email;
    private TextInputLayout age;
    private TextInputLayout address;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEditBinding binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        username = binding.EditActivityUserName;
        password = binding.EditActivityPassword;
        firstName = binding.EditActivityFirstName;
        lastName = binding.EditActivityLastName;


        Intent givenIntent = getIntent();
        if (!givenIntent.hasExtra(LoginActivity.EXTRA_LOGGED_IN_USER_ID)) {
            //Error extra wasn't passed
            //For now, let's Log it and Change back to Login Activity.  NOT a good way of handling this issue
            //Bad: Because user has no idea why.
            Log.e("EditActivity_onCreate", "Login Id not provided in Intent with Extra: " + LoginActivity.EXTRA_LOGGED_IN_USER_ID);
            finish();
            int userId = givenIntent.getIntExtra(LoginActivity.EXTRA_LOGGED_IN_USER_ID, -1);
            Log.e("EditActivity_onCreate", "Login Id not provided in Intent with Extra: " + LoginActivity.EXTRA_LOGGED_IN_USER_ID);
            UserDao userDao = AppDatabase.getDatabaseInstance(this).getUserDao();

            AppDatabase.databaseWriteExecutor.execute(() -> {
                user = userDao.getUser(userId);
                runOnUiThread(this::populateFields);
            });

            findViewById(R.id.EditActivityRegisterButton).setOnClickListener(v -> {
                user.setUserName(getValue(username));
                user.setPassword(getValue(password));
                user.setFirstName(getValue(firstName));
                user.setLastName(getValue(lastName));

                AppDatabase.databaseWriteExecutor.execute(() -> {
                    userDao.update(user);
                    runOnUiThread(() -> Toast.makeText(this, "Updated Successfully", Toast.LENGTH_SHORT).show());
                });
            });
        }
    }

    private String getValue(TextInputLayout view) {
        return view.getEditText().getText().toString();
    }

    private void populateFields() {
        username.getEditText().setText(user.getUserName());
        password.getEditText().setText(user.getPassword());
        firstName.getEditText().setText(user.getFirstName());
        lastName.getEditText().setText(user.getLastName());
    }
}