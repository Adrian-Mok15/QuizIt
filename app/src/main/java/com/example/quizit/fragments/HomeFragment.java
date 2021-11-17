package com.example.quizit.fragments;

import android.content.Intent;
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

import com.example.quizit.LoginActivity;
import com.example.quizit.Quiz;
import com.example.quizit.QuizAdapter;
import com.example.quizit.R;
import com.parse.FindCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

    public class HomeFragment extends Fragment {


        public static final String TAG = "QuizzesFragment";
        private static final String ARG_PARAM1 = "param1";
        private static final String ARG_PARAM2 = "param2";

        // TODO: Rename and change types of parameters
        private String mParam1;
        private String mParam2;

        private RecyclerView rvQuizzes;
        protected QuizAdapter adapter;
        protected List<Quiz> allQuizzes;

        public HomeFragment() {
            // Required empty public constructor
        }

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment QuizzesFragment.
         */
        // TODO: Rename and change types and number of parameters
        public static HomeFragment newInstance(String param1, String param2) {
            HomeFragment fragment = new HomeFragment();
            Bundle args = new Bundle();
            args.putString(ARG_PARAM1, param1);
            args.putString(ARG_PARAM2, param2);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            if (getArguments() != null) {
                mParam1 = getArguments().getString(ARG_PARAM1);
                mParam2 = getArguments().getString(ARG_PARAM2);
            }
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_home, container, false);
        }

        @Override
        public void onViewCreated(@NonNull  View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            rvQuizzes = view.findViewById(R.id.rvQuizzes);
            allQuizzes = new ArrayList<>();
            adapter = new QuizAdapter(getContext(), allQuizzes);
            rvQuizzes.setAdapter(adapter);
            rvQuizzes.setLayoutManager(new LinearLayoutManager(getContext()));
            queryQuizzes();
        }


        protected void queryQuizzes() {
            ParseQuery<Quiz> query = ParseQuery.getQuery(Quiz.class);
            query.include(Quiz.KEY_ID);
            query.setLimit(20);
            query.findInBackground(new FindCallback<Quiz>() {
                @Override
                public void done(List<Quiz> Quizzes, ParseException e) {
                    if(e != null){
                        Log.e(TAG,"Issue with getting Quizzes", e);
                        return;
                    }
                    for(Quiz quiz: Quizzes){
                        Log.i(TAG, "Quiz: " + quiz.getKeyId() + ", username"+ quiz.getKeyTopic());
                    }
                    allQuizzes.addAll(Quizzes);
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

//    private void goQuizActivity() {
//        Intent i = new Intent(getContext(), LoginActivity.class); CHANGE LOGINACTIVITY.CLASS TO THE QUIZ PAGE
//        startActivity(i);
//        getActivity().finish();
//    }

