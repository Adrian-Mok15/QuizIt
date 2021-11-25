package com.example.quizit;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("User")
public class User extends ParseObject {

    public static final String KEY_USERNAME = "username";
    public static final String KEY_RANK = "rank";
    public static final String KEY_USER  = "User";
    public static final String KEY_SCORE ="score";
    public static final String KEY_PROFILE_PICTURE = "Profile_picture";

    public ParseFile getProfile_picture(){
        return getParseFile(KEY_PROFILE_PICTURE);
    }
    public String getScore(){
        return getString(KEY_SCORE);
    }
    public void setScore(String score){
        put(KEY_SCORE, score);
    }
    public String getRank(){
        return getString(KEY_RANK);
    }
    public void setRank(String rank){
        put(KEY_RANK, rank);
    }
    public String getUsername(){
        return getString(KEY_USERNAME);
    }
    public void setUsername(String username){
        put(KEY_USERNAME, username);
    }
}
