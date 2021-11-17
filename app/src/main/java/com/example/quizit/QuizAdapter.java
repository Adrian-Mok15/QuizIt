package com.example.quizit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuizAdapter extends RecyclerView.Adapter<QuizAdapter.ViewHolder> {

    private Context context;
    private List<Quiz> quizzes;

    public QuizAdapter(Context context, List<Quiz> quizzes) {
        this.context = context;
        this.quizzes = quizzes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_quiz, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  QuizAdapter.ViewHolder holder, int position) {
        Quiz quiz = quizzes.get(position);
        holder.bind(quiz);
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
        }

        public void bind(Quiz quiz) {
            tvName.setText(quiz.getKeyTopic() + " - " + quiz.getKeyName());
            tvName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(context, quiz.getKeyName(),Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(context, DetailActivity.class);
                    i.putExtra("topic", quiz.getKeyTopic());
                    i.putExtra("name", quiz.getKeyName());
                    i.putExtra("id", quiz.getKeyId());
                    context.startActivity(i);
                }
            });
        }
        // Clean all elements of the recycler
        public void clear(){
            quizzes.clear();
            notifyDataSetChanged();
        }
        // Add a list of items -- change to type used
        public void addAll(List<Quiz> quizList){
            quizzes.addAll(quizList);
            notifyDataSetChanged();

        }
    }
}
