package com.example.quizit;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Question")
public class Question extends ParseObject {

    public static final String KEY_QUIZ_ID = "quiz_string";
    public static final String KEY_QUESTION = "question";
    public static final String KEY_CHOICE_ONE = "choice_one";
    public static final String KEY_CHOICE_TWO = "choice_two";
    public static final String KEY_CHOICE_THREE = "choice_three";
    public static final String KEY_CHOICE_FOUR = "choice_four";
    public static final String KEY_ANSWER = "correct_choice";

    public String getKeyQuizId(){
        return getString(KEY_QUIZ_ID);
    }

    public String getKeyQuestion() {
        return getString(KEY_QUESTION);
    }

    public String getKeyChoiceOne() {
        return getString(KEY_CHOICE_ONE);
    }

    public String getKeyChoiceTwo() {
        return getString(KEY_CHOICE_TWO);
    }

    public String getKeyChoiceThree() {
        return getString(KEY_CHOICE_THREE);
    }

    public String getKeyChoiceFour() {
        return getString(KEY_CHOICE_FOUR);
    }

    public String getKeyAnswer(){
        return getString(KEY_ANSWER);
    }
}
