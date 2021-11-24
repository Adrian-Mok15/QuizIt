package com.example.quizit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private String TAG = "DetailActivity";
    private TextView topicView;
    private TextView nameView;
    private Button option1Btn, option2Btn, option3Btn, option4Btn;
    private ArrayList<Question> questionArrayList = new ArrayList<>();
    Question currentQuestion;
    int position = 0;
    private ArrayList<String> user_answers = new ArrayList<>();
    private ArrayList<String> correct_answers = new ArrayList<>();
    private ArrayList<String> questions = new ArrayList<>();

    int currentScore = 0, questionAttempted = 1, currentPos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        topicView = findViewById(R.id.topicView);
        nameView = findViewById(R.id.nameView);
        option1Btn = findViewById(R.id.idBtnOption1);
        option2Btn = findViewById(R.id.idBtnOption2);
        option3Btn = findViewById(R.id.idBtnOption3);
        option4Btn = findViewById(R.id.idBtnOption4);

//        questionArrayList = new ArrayList<>();


        String topic = getIntent().getStringExtra("topic");
        topic += getIntent().getStringExtra("name");
        String objId = getIntent().getStringExtra("id");

        ParseQuery<Question> query = ParseQuery.getQuery(Question.class);
        query.whereEqualTo(Question.KEY_QUIZ_ID, objId);
        query.findInBackground(new FindCallback<Question>() {
            @Override
            public void done(List<Question> Questions, ParseException e) {
                if(e != null){
                    Log.e(TAG,"Issue with getting Questions", e);
                    return;
                }
                for(Question question: Questions){
                    Log.i(TAG, "Question: " + question.getKeyQuestion());
                }
                questionArrayList.addAll(Questions);

                if(questionArrayList.size() > position){
                    currentQuestion = questionArrayList.get(position);
                    setTextsToScreen(position);
                }

                option1Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(questionArrayList.get(position).getKeyAnswer().trim().toLowerCase().equals(option1Btn.getText().toString().trim().toLowerCase())){
                            currentScore++;
                            //this is where you would get the results for the results page.
                        }
                        user_answers.add(option1Btn.getText().toString());
                        Log.i(TAG, "Answers: " + user_answers);
                        correct_answers.add(questionArrayList.get(position).getKeyAnswer());
                        Log.i(TAG, "Correct: " + correct_answers);
                        questions.add(questionArrayList.get(position).getKeyQuestion());
                        position++;
                        setTextsToScreen(position);

                        if(position > questionArrayList.size() - 1){
                            Intent i = new Intent(DetailActivity.this, ResultsActivity.class); //for the results page
                            i.putExtra("questions", questions);
                            i.putExtra("user_answers", user_answers);
                            i.putExtra("correct_answers", correct_answers);
                            i.putExtra("score", currentScore);
                            startActivity(i);
                        }
                    }
                });

                option2Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(questionArrayList.get(position).getKeyAnswer().toString().trim().toLowerCase().equals(option2Btn.getText().toString().trim().toLowerCase())){
                            currentScore++;
                            //this is where you would get the results for the results page.
                        }
                        user_answers.add(option2Btn.getText().toString());
                        correct_answers.add(questionArrayList.get(position).getKeyAnswer());
                        questions.add(questionArrayList.get(position).getKeyQuestion());
                        position++;
                        setTextsToScreen(position);

                        if(position > questionArrayList.size() - 1){
                            Intent i = new Intent(DetailActivity.this, ResultsActivity.class); //for the results page
                            i.putExtra("questions", questions);
                            i.putExtra("user_answers", user_answers);
                            i.putExtra("correct_answers", correct_answers);
                            i.putExtra("score", currentScore);
                            startActivity(i);
                        }
                    }
                });

                option3Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(questionArrayList.get(position).getKeyAnswer().toString().trim().toLowerCase().equals(option3Btn.getText().toString().trim().toLowerCase())){
                            currentScore++;
                            //this is where you would get the results for the results page.
                        }
                        user_answers.add(option3Btn.getText().toString());
                        correct_answers.add(questionArrayList.get(position).getKeyAnswer());
                        questions.add(questionArrayList.get(position).getKeyQuestion());
                        position++;
                        setTextsToScreen(position);

                        if(position > questionArrayList.size() - 1){
                            Intent i = new Intent(DetailActivity.this, ResultsActivity.class); //for the results page
                            i.putExtra("questions", questions);
                            i.putExtra("user_answers", user_answers);
                            i.putExtra("correct_answers", correct_answers);
                            i.putExtra("score", currentScore);
                            startActivity(i);
                        }
                    }
                });

                option4Btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(questionArrayList.get(position).getKeyAnswer().toString().trim().toLowerCase().equals(option4Btn.getText().toString().trim().toLowerCase())){
                            currentScore++;
                            //this is where you would get the results for the results page.
                        }
                        user_answers.add(option4Btn.getText().toString());
                        correct_answers.add(questionArrayList.get(position).getKeyAnswer());
                        questions.add(questionArrayList.get(position).getKeyQuestion());
                        position++;
                        setTextsToScreen(position);

                        if(position > questionArrayList.size() - 1){
                            Intent i = new Intent(DetailActivity.this, ResultsActivity.class); //for the results page
                            i.putExtra("questions", questions);
                            i.putExtra("user_answers", user_answers);
                            i.putExtra("correct_answers", correct_answers);
                            i.putExtra("score", currentScore);
                            startActivity(i);
                        }
                    }
                });
            }
        });
    }

    protected void setTextsToScreen(int position){

        if(questionArrayList.size() > position){
            currentQuestion = questionArrayList.get(position);
            position++;
        }

        String topic = getIntent().getStringExtra("topic");
        topic += " - " + getIntent().getStringExtra("name");
        String objId = getIntent().getStringExtra("id");

        String question = currentQuestion.getKeyQuestion();
        String answer = currentQuestion.getKeyAnswer();
        String option1 = currentQuestion.getKeyChoiceOne();
        String option2 = currentQuestion.getKeyChoiceTwo();
        String option3 = currentQuestion.getKeyChoiceThree();
        String option4 = currentQuestion.getKeyChoiceFour();

        topicView.setText(topic);
        nameView.setText(question);
        option1Btn.setText(option1);
        option2Btn.setText(option2);
        option3Btn.setText(option3);
        option4Btn.setText(option4);
    }
}