package com.example.quizit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;
import com.parse.ParseUser;

import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    private Context context;
    private List<ParseUser> parseUsers;
    private ImageView ProfilePic;
    private TextView txtUsername;
    private TextView txtScore;

    public LeaderboardAdapter(Context context, List<ParseUser> parseUsers){
        this.context = context;
        this.parseUsers = parseUsers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.leader_layout, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ParseUser users = parseUsers.get(position);
        holder.bind(users);
    }

    @Override
    public int getItemCount() {
        return parseUsers.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ProfilePic = itemView.findViewById(R.id.ivPic);
            txtUsername = itemView.findViewById(R.id.username_leaderboard);
            txtScore = itemView.findViewById(R.id.score_leaderboard);

        }

        public void bind(ParseUser user) {
           double score = user.getDouble("Score");
            DecimalFormat numberFormat = new DecimalFormat("#.00");
            txtScore.setText("Score: " + numberFormat.format(score));
            txtUsername.setText("Username: " +  user.getString("username"));
            ParseFile image = user.getParseFile("Profile_picture");
            if(image != null ){
                Glide.with(context).load(image.getUrl()).circleCrop().into(ProfilePic);
            }
        }
    }
}
