package com.example.quizit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private TextView topicView;
    private TextView nameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        topicView = findViewById(R.id.topicView);
        nameView = findViewById(R.id.nameView);

        String topic = getIntent().getStringExtra("topic");
        String name = getIntent().getStringExtra("name");
        String objId = getIntent().getStringExtra("id");

        topicView.setText(topic);
        nameView.setText(name);




    }
}