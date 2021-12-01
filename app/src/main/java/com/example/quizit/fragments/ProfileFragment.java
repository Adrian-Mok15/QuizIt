package com.example.quizit.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.quizit.Leaderboard2;
import com.example.quizit.LoginActivity;
import com.example.quizit.R;
import com.example.quizit.SettingsActivity;
import com.parse.ParseFile;
import com.parse.ParseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView LeaderBoard;
    private Button btnLogout;
    private Button btnSettings;
    private ImageView image;
    private TextView username;
    private TextView rank;
    private TextView FavoriteTopic;
    private TextView average;
    private TextView leastFavorite;
    private TextView average2;
    protected List<QuizAttempt> parseObjects;
    protected List<String> stringList;

    private int scoreList;
    public static final String TAG = "ProfileFragment";

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        parseObjects = new ArrayList<>();
        stringList = new ArrayList<>();
        queryAttempts();

        swipeRefreshLayout = view.findViewById(R.id.SwipeContainer);
        swipeRefreshLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ParseUser currentUser = ParseUser.getCurrentUser();
                String Rank = currentUser.getString("rank");
                String UserName = currentUser.getString("username");
                username.setText("Username: " + UserName);
                rank.setText("Rank: " + Rank);
                queryAttempts();
                swipeRefreshLayout.setRefreshing(false);

            }
        });
        average = view.findViewById(R.id.txtAverageScore);
        average2 = view.findViewById(R.id.txtAverageScore2);
        FavoriteTopic = view.findViewById(R.id.txtFavTopic);
        leastFavorite = view.findViewById(R.id.txtLeastFavorite);
        username =  view.findViewById(R.id.txtUsername);
        rank = view.findViewById(R.id.txtRank);
        image = view.findViewById(R.id.ProfileImageView);
        LeaderBoard = view.findViewById(R.id.txtLeaderBoard);
        btnSettings = view.findViewById(R.id.btnSettings);
        //New***
        LeaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), Leaderboard2.class);
                startActivity(i);
            }
        });
        //****

        ParseUser currentUser = ParseUser.getCurrentUser();
        ParseFile ProImage = currentUser.getParseFile("Profile_picture");;
        if(ProImage != null ){
            Glide.with(getContext()).load(ProImage.getUrl()).into(image);
        }
        else {
            Glide.with(getContext()).load(R.drawable.profile).into(image);
        }
        String Rank = currentUser.getString("rank");
        String UserName = currentUser.getString("username");
        username.setText("Username: " + UserName);

        btnLogout = view.findViewById(R.id.btnLogOut);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser.logOut();
                ParseUser currentUser = ParseUser.getCurrentUser();
                Log.i(TAG, "Log out successful. Current user is " + currentUser);
                goLoginActivity();
            }
        });

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), SettingsActivity.class);
                startActivity(i);
            }
        });

    }
    private void goLoginActivity() {
        Intent i = new Intent(getContext(), LoginActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    private void queryAttempts() {
        ParseQuery<QuizAttempt> query = ParseQuery.getQuery(QuizAttempt.class);
        query.include(QuizAttempt.KEY_QUIZ);
        query.whereEqualTo(QuizAttempt.KEY_USER, ParseUser.getCurrentUser());
        query.addAscendingOrder(QuizAttempt.KEY_CREATED);
        query.findInBackground(new FindCallback<QuizAttempt>() {
            @Override
            public void done(List<QuizAttempt> attempts, ParseException e) {
                if(e != null) {
                    Log.e(TAG, "Issue with getting quiz history");
                    return;
                }
                //Do this later
                boolean checker = false;
                boolean checker2 = false;
                boolean rankChecker = false;
                double rankCounter = 0;
                double rankTimes = 1;
                double rankFinal = 0;
                int times = 1;
                int times2 = 1;
                double result1 = 0;
                double result2 = 0;
                int counter1= 0;
                int counter2 = 0;
                String good = null;
                String bad = null;
                for(int i =0; i < attempts.size(); i++) {
                    rankChecker = true;
                    QuizAttempt attempt = attempts.get(i);
                    rankTimes++;
                    rankCounter = rankCounter + attempt.getScore();
                            Log.i(TAG, "Quiz: " + attempt.getScore());
                    stringList.add(attempt.getQuiz().getKeyName());
                    if(i == 0){
                        good = attempt.getQuiz().getKeyTopic();
                        FavoriteTopic.setText("Favorite Topic: " + attempt.getQuiz().getKeyTopic());
                    }
                    else if(i == attempts.size()-1){
                        bad  = attempt.getQuiz().getKeyTopic();
                        leastFavorite.setText("Least Favorite Topic: " + attempt.getQuiz().getKeyTopic());
                    }
                }
                rankFinal = rankCounter/rankTimes;
                ParseUser currentUser2 = ParseUser.getCurrentUser();
                if(rankChecker == true){
                    if(rankFinal < 2){
                        rank.setText("Rank: Bronze");
                        currentUser2.put("rank", "Bronze");
                        currentUser2.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {

                            }
                        });
                        currentUser2.put("Score", rankFinal);
                        currentUser2.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {

                            }
                        });
                    }
                    else if ( rankFinal >= 2 && rankFinal <= 4){
                        rank.setText("Rank: Silver");
                        currentUser2.put("rank", "Silver");
                        currentUser2.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {

                            }
                        });
                        currentUser2.put("Score", rankFinal);
                        currentUser2.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {

                            }
                        });
                    }
                    else{
                        rank.setText("Rank: Gold");
                        currentUser2.put("rank", "Gold");
                        currentUser2.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {

                            }
                        });
                        currentUser2.put("Score", rankFinal);
                        currentUser2.saveInBackground(new SaveCallback() {
                            @Override
                            public void done(ParseException e) {

                            }
                        });
                    }


                }
                if(rankChecker == false){
                    rank.setText("Unknown");
                }

                for(int i =0; i < attempts.size(); i++) {
                    QuizAttempt attempt = attempts.get(i);
                    if (attempt.getQuiz().getKeyTopic() == good ){
                        counter1 = counter1 + attempt.getScore();
                        times++;
                    }
                    else if ( attempt.getQuiz().getKeyTopic() == bad){
                        counter2 = counter2 + attempt.getScore();
                        times2++;
                    }
                }
                result1 = counter1/times;
                result2 = counter2/times2;
                average.setText("Average: " + String.valueOf(result1));
                average2.setText("Average: " + String.valueOf(counter2));
            }
        });
    }
}
