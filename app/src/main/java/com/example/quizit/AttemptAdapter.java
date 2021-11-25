package com.example.quizit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AttemptAdapter extends RecyclerView.Adapter<AttemptAdapter.ViewHolder> {

    private Context context;
    private List<QuizAttempt> attempts;

    public AttemptAdapter(Context context, List<QuizAttempt> attempts) {
        this.context = context;
        this.attempts = attempts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_attempt, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        QuizAttempt attempt = attempts.get(position);
        holder.bind(attempt);
    }

    @Override
    public int getItemCount() {
        return attempts.size();
    }

    public void clear() {
        attempts.clear();
        notifyDataSetChanged();
    }
    public void addAll(List<QuizAttempt> attemptList) {
        attempts.addAll(attemptList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvQuiz;
        private TextView tvScore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuiz = itemView.findViewById(R.id.tvQuiz);
            tvScore = itemView.findViewById(R.id.tvQuizScore);
        }

        public void bind(QuizAttempt attempt) {
            tvQuiz.setText(attempt.getQuiz().getKeyTopic() + " - " + attempt.getQuiz().getKeyName());
            tvScore.setText(Integer.toString(attempt.getScore()) + "/5");
        }
    }
}
