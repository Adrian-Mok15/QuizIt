package com.example.quizit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> questions;
    private ArrayList<String> selected;
    private ArrayList<String> correct;

    public ResultAdapter(Context context, ArrayList<String> questions, ArrayList<String> selected, ArrayList<String> correct) {
        this.context = context;
        this.questions = questions;
        this.selected = selected;
        this.correct = correct;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String question = questions.get(position);
        String select = selected.get(position);
        String corr = correct.get(position);
        holder.bind(question, select, corr);
    }
    

    @Override
    public int getItemCount() {
        return questions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvQuestion;
        private TextView tvSelected;
        private TextView tvCorrect;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvQuestion = itemView.findViewById(R.id.tvQuestion);
            tvSelected = itemView.findViewById(R.id.tvSelected);
            tvCorrect = itemView.findViewById(R.id.tvCorrect);
        }

        public void bind(String question, String select, String corr) {
            tvQuestion.setText("Question: " + question);
            tvSelected.setText("Selected Answer: " + select);
            tvCorrect.setText("Correct Answer: " + corr);
        }
    }
}
