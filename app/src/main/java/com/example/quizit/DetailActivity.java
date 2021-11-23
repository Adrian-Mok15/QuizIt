package com.example.quizit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DetailActivity extends AppCompatActivity {

    private String TAG = "DetailActivity";
    private TextView topicView;
    private TextView nameView;
    private Button option1Btn, option2Btn, option3Btn, option4Btn;
    private ArrayList<Question> questionArrayList;
    Question currentQuestion;
    int position = 0;

    Random random;
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

        questionArrayList = new ArrayList<>();
        random = new Random();

//        getQuizQuestion(questionArrayList);


        String topic = getIntent().getStringExtra("topic");
        topic += getIntent().getStringExtra("name");
        String objId = getIntent().getStringExtra("id");

        queryQuestions(objId);

        Log.i(TAG, "Size: " + questionArrayList.size());

//        currentQuestion = questionArrayList.get(0);
//
//        if(questionArrayList.size() > position){
//            currentQuestion = questionArrayList.get(position);
//            position++;
//        }
//
//
//        String question = currentQuestion.getKeyQuestion();
//        String answer = currentQuestion.getKeyAnswer();
//        String option1 = currentQuestion.getKeyChoiceOne();
//        String option2 = currentQuestion.getKeyChoiceTwo();
//        String option3 = currentQuestion.getKeyChoiceThree();
//        String option4 = currentQuestion.getKeyChoiceFour();
//
//        topicView.setText(topic);
//        nameView.setText(question);
//        option1Btn.setText(option1);
//        option2Btn.setText(option2);
//        option3Btn.setText(option3);
//        option4Btn.setText(option4);

    }

    protected void queryQuestions(String objId) {
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




//               String topic = getIntent().getStringExtra("topic");
//                currentQuestion = questionArrayList.get(0);
//
//                if(questionArrayList.size() > position){
//                    currentQuestion = questionArrayList.get(position);
//                    position++;
//                }
//
//                String question = currentQuestion.getKeyQuestion();
//                String answer = currentQuestion.getKeyAnswer();
//                String option1 = currentQuestion.getKeyChoiceOne();
//                String option2 = currentQuestion.getKeyChoiceTwo();
//                String option3 = currentQuestion.getKeyChoiceThree();
//                String option4 = currentQuestion.getKeyChoiceFour();
//
//                topicView.setText(topic);
//                nameView.setText(question);
//                option1Btn.setText(option1);
//                option2Btn.setText(option2);
//                option3Btn.setText(option3);
//                option4Btn.setText(option4);

            }
        });
    }



//    private void getQuizQuestion(ArrayList<Question> questionArrayList) {
//        questionArrayList.add(new Question);
//    }
}