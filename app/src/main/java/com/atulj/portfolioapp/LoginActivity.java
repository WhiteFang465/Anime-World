package com.atulj.portfolioapp;


import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;

import androidx.appcompat.app.AppCompatActivity;

import com.atulj.portfolioapp.databinding.ActivityLoginBinding;
import com.atulj.portfolioapp.db.AppDatabase;
import com.atulj.portfolioapp.db.dao.UserDao;
import com.atulj.portfolioapp.db.entities.User;

public class LoginActivity extends AppCompatActivity {
    public static final String EXTRA_LOGGED_IN_USER_ID = "currentUserId";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.app_bar,menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.MainActivityRegisterButton.setOnClickListener(v -> {
            Intent registerIntent = new Intent(this, RegisterActivity.class);
            startActivity(registerIntent);
        });

        UserDao userDao = AppDatabase.getDatabaseInstance(this).getUserDao();
        Resources resources = getResources();

        binding.MainActivityLoginButton.setOnClickListener(v -> {
            String username = binding.MainActivityUsername.getEditText().getText().toString();
            String password = binding.MainActivitypassword.getEditText().getText().toString();
            AppDatabase.databaseWriteExecutor.execute(() -> {
                if (!userDao.usernameExists(username)) {
                    runOnUiThread(() -> binding.MainActivityUsername.setError(resources.getString(R.string.login_invalidUsername)));
                    return;
                } else {
                    runOnUiThread(() -> binding.MainActivityUsername.setError(null));
                }
                User user = userDao.validateUsernameAndPassword(username, password);
                if (user == null) {
                    runOnUiThread(() -> binding.MainActivitypassword.setError(resources.getString(R.string.login_invalidPassword)));
                } else {
                    runOnUiThread(() -> binding.MainActivitypassword.setError(null));

                }
                Intent editIntent = new Intent(this, AnimeListActivity.class);
                editIntent.putExtra(EXTRA_LOGGED_IN_USER_ID, user.getId());
                startActivity(editIntent);

            });
        });


    }

}