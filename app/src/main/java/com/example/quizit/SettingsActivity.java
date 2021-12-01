package com.example.quizit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SettingsActivity extends AppCompatActivity {
    private Button submitUser;
    private Context context;
    private EditText editUser;
    private EditText editPassword;
    private Button submitPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    ParseUser currentUser = ParseUser.getCurrentUser();
        submitUser = findViewById(R.id.button_changeusr);
        editUser = findViewById(R.id.EditTxtChangeUser);
        editPassword = findViewById(R.id.EditTxtChangePassword);
        submitPassword = findViewById(R.id.button_changepsw);

        //submitPassword.setOnClickListener(this);
        //button_erasedata.setOnClickListener(this);
        //button_contactus.setOnClickListener(this);



        submitPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = null;
                text = editPassword.getText().toString();
                if (text != null) {
                    currentUser.setPassword(text);
                    currentUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                        }
                    });
                }
                editPassword.getText().clear();
                Toast.makeText(SettingsActivity.this, "Password changed.", Toast.LENGTH_LONG).show();
            }
        });
        submitUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = null;
                text = editUser.getText().toString();
                if (text != null) {
                    currentUser.setUsername(text);
                    currentUser.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(ParseException e) {
                        }
                    });
                }
                editUser.getText().clear();
                Toast.makeText(SettingsActivity.this, "Username changed.", Toast.LENGTH_LONG).show();
            }
        });

    }

    /*@Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_changepsw:
                Toast.makeText(SettingsActivity.this, "Test", Toast.LENGTH_SHORT).show();
                break;
            case R.id.button_erasedata:
                Toast.makeText(SettingsActivity.this, "All quiz histories have been reset.", Toast.LENGTH_SHORT).show();
                break;
            /*case R.id.button_contactus:
                openDialog();
                break;
            case R.id.button_logout:
                openLoginActivity();
                break;*/
        /*}
    }*/
