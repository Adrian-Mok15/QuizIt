package com.example.quizit;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("QuizAttempt")
public class QuizAttempt extends ParseObject {

    public static final String KEY_USER = "user";
    public static final String KEY_QUIZ = "quiz";
    public static final String KEY_SCORE = "score";
    public static final String KEY_CREATED = "createdAt";

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public Quiz getQuiz() {
        return (Quiz) getParseObject(KEY_QUIZ);
    }

    public void setQuiz(Quiz quiz) {
        put(KEY_QUIZ, quiz);
    }

    public int getScore() {
        return getInt(KEY_SCORE);
    }

    public void setScore(int score) {
        put(KEY_SCORE, score);
    }
}
