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
import com.example.quizit.LoginActivity;
import com.example.quizit.R;
import com.example.quizit.SettingsActivity;
import com.parse.ParseFile;
import com.parse.ParseUser;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    private Button btnLogout;
    private Button btnSettings;
    private ImageView image;
    private TextView username;
    private TextView rank;
    private TextView FavoriteTopic;
    private TextView average;
    private TextView leastFavorite;
    private TextView average2;
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
        //**************
        username =  view.findViewById(R.id.txtUsername);
        rank = view.findViewById(R.id.txtRank);
        image = view.findViewById(R.id.ProfileImageView);
        btnSettings = view.findViewById(R.id.btnSettings);

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
        rank.setText("Rank: " + Rank);
        //**********
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
}
