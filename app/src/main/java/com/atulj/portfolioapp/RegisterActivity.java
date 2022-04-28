package com.atulj.portfolioapp;

import android.content.res.Resources;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.atulj.portfolioapp.databinding.ActivityRegisterBinding;
import com.atulj.portfolioapp.db.AppDatabase;
import com.atulj.portfolioapp.db.dao.UserDao;
import com.atulj.portfolioapp.db.entities.User;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterActivity extends AppCompatActivity {
    private Resources resource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityRegisterBinding binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TextInputLayout username = binding.RegisterActivityUserName;
        TextInputLayout password = binding.RegisterActivityPassword;
        TextInputLayout repeatPassword = binding.RegisterActivityConfirmPassword;
        TextInputLayout firstName = binding.RegisterActivityFirstName;
        TextInputLayout lastName = binding.RegisterActivityLastName;
        TextInputLayout email = binding.RegisterActivityEmail;
        TextInputLayout address = binding.RegisterActivityAddress;
        TextInputLayout age = binding.RegisterActivityAge;

        resource = getResources();

        binding.RegisterActivityRegisterButton.setOnClickListener(v -> {
            if (isEmpty(username, password, repeatPassword, firstName, lastName, email, address, age)) {
                return;
            }
            if (!doPasswordMatch(password, repeatPassword)) {
                return;
            }

            User user = new User(getValue(username), getValue(firstName), getValue(lastName), getValue(password), getValue(email), getValue(address), Integer.parseInt(age.getEditText().getText().toString()));
            UserDao userDao = AppDatabase.getDatabaseInstance(this).getUserDao();

            AppDatabase.databaseWriteExecutor.execute(() -> {
                userDao.insert(user);
                runOnUiThread(() -> Toast.makeText(this, "User Registered", Toast.LENGTH_SHORT).show());
                finish();
            });
        });
    }

    private String getValue(@NonNull TextInputLayout view) {
        return view.getEditText().getText().toString();
    }

    private boolean doPasswordMatch(@NonNull TextInputLayout password, @NonNull TextInputLayout repeatPassword) {
        String passwordValue = password.getEditText().getText().toString();
        String passwordRepeatValue = repeatPassword.getEditText().getText().toString();
        if (!passwordValue.equals(passwordRepeatValue)) {
            repeatPassword.setError(resource.getString(R.string.confirmPasswordError));
            repeatPassword.setErrorEnabled(true);
            return false;
        }
        repeatPassword.setErrorEnabled(false);
        return true;
    }

    private boolean isEmpty(TextInputLayout... views) {
        boolean foundError = false;

        for (TextInputLayout view : views) {
            String value = view.getEditText().getText().toString();
            if (value.isEmpty()) {
                view.setError(resource.getString(R.string.register_emptyError));
                view.setErrorEnabled(true);
                foundError = true;
            } else {
                view.setErrorEnabled(false);
            }

        }
        return foundError;
    }
}