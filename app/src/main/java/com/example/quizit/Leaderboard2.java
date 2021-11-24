package com.example.instagram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard2 extends AppCompatActivity {
    
    protected List<ParseUser> allUsers;
    protected LeaderboardAdapter adapter;
    private RecyclerView rvUsers;

    public static final String TAG ="Leaderboard";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard2);

        allUsers = new ArrayList<ParseUser>();
        rvUsers = findViewById(R.id.rvUsers);
        allUsers= new ArrayList<ParseUser>();
        adapter = new LeaderboardAdapter(this, allUsers);
        rvUsers.setLayoutManager(new LinearLayoutManager(this));
        rvUsers.setAdapter(adapter);
        
        queryUsers();
    }

    private void queryUsers() {
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.addDescendingOrder(User.KEY_SCORE);
        //ParseQuery<User> query = ParseQuery.getQuery(User.class);
        query.findInBackground(new FindCallback<ParseUser>() {
            @Override
            public void done(List<ParseUser> users, ParseException e) {
                if (e != null){
                    Log.e(TAG, "issue with getting post", e);
                    return;
                }
                for(ParseUser user : users){
                    Log.i(TAG, "User " + user.getInt("score") + ", username: " + user.get("username"));
                }
                allUsers.addAll(users);
                adapter.notifyDataSetChanged();
            }
        });
    }
}