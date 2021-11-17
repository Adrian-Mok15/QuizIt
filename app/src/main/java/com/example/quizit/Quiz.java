package com.example.quizit;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Quiz")
public class Quiz extends ParseObject {


    public static final String KEY_TOPIC = "topic";
    public static final String KEY_ID = "objectId";

    public String getKeyTopic() {
        return getString(KEY_TOPIC);
    }

    public String getKeyId() {
        return getString(KEY_ID);
    }
}