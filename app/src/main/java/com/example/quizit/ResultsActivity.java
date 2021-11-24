package com.example.quizit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity {

    public static final String TAG = "ResultsActivity";
    private Button btnDone;
    private TextView tvScore;
    private RecyclerView rvResults;
    protected ResultAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        tvScore = findViewById(R.id.tvScore);
        btnDone = findViewById(R.id.btnDone);

        int score = getIntent().getIntExtra("score", 0);
        ArrayList<String> questions = getIntent().getStringArrayListExtra("questions");
        ArrayList<String> user_answers = getIntent().getStringArrayListExtra("user_answers");
        ArrayList<String> correct_answers = getIntent().getStringArrayListExtra("correct_answers");
        rvResults = findViewById(R.id.rvResults);
        adapter = new ResultAdapter(this, questions, user_answers, correct_answers);
        rvResults.setAdapter(adapter);
        rvResults.setLayoutManager(new LinearLayoutManager(this));

        tvScore.setText("Score: " + Integer.toString(score) + "/5");


        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ResultsActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        Log.i(TAG, "score: " + score + "\nquestions: " + questions + "\nuser_answers" + user_answers);
    }
}