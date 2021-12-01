package com.example.quizit.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.quizit.AttemptAdapter;
import com.example.quizit.QuizAttempt;
import com.example.quizit.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {

    public static final String TAG = "HistoryFragment";

    private RecyclerView rvAttempts;
    protected AttemptAdapter adapter;
    protected List<QuizAttempt> allAttempts;

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvAttempts = view.findViewById(R.id.rvAttempts);
        allAttempts = new ArrayList<>();
        adapter = new AttemptAdapter(getContext(), allAttempts);
        rvAttempts.setAdapter(adapter);
        rvAttempts.setLayoutManager(new LinearLayoutManager(getContext()));
        queryAttempts();
    }

    private void queryAttempts() {
        ParseQuery<QuizAttempt> query = ParseQuery.getQuery(QuizAttempt.class);
        query.include(QuizAttempt.KEY_QUIZ);
        query.whereEqualTo(QuizAttempt.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(QuizAttempt.KEY_SCORE);
        query.findInBackground(new FindCallback<QuizAttempt>() {
            @Override
            public void done(List<QuizAttempt> attempts, ParseException e) {
                if(e != null) {
                    Log.e(TAG, "Issue with getting quiz history");
                    return;
                }
                for(QuizAttempt attempt : attempts) {
                    Log.i(TAG, "Quiz: " + attempt.getQuiz().getKeyName());
                }
                adapter.clear();
                allAttempts.addAll(attempts);
            }
        });
    }
}
