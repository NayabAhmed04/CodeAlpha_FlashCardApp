package com.example.flashcardapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flashcardapp.Flashcard;
import com.example.flashcardapp.R;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    TextView questionText, answerText;
    Button showAnswerBtn, nextBtn, prevBtn, addBtn, editBtn, deleteBtn;

    ArrayList<Flashcard> flashcards;
    int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        questionText = findViewById(R.id.questionText);
        answerText = findViewById(R.id.answerText);
        showAnswerBtn = findViewById(R.id.showAnswerBtn);
        nextBtn = findViewById(R.id.nextBtn);
        prevBtn = findViewById(R.id.prevBtn);
        addBtn = findViewById(R.id.addBtn);
        editBtn = findViewById(R.id.editBtn);
        deleteBtn = findViewById(R.id.deleteBtn);

        flashcards = new ArrayList<>();
        flashcards.add(new Flashcard("What is Java?", "A programming language"));
        flashcards.add(new Flashcard("What is Android?", "Mobile OS by Google"));
        flashcards.add(new Flashcard("What is RecyclerView?", "A list UI component"));

        displayCard();

        showAnswerBtn.setOnClickListener(v -> answerText.setVisibility(View.VISIBLE));

        nextBtn.setOnClickListener(v -> {
            if(currentIndex < flashcards.size() - 1){
                currentIndex++;
                displayCard();
            } else {
                Toast.makeText(this, "This is the last card", Toast.LENGTH_SHORT).show();
            }
        });

        prevBtn.setOnClickListener(v -> {
            if(currentIndex > 0){
                currentIndex--;
                displayCard();
            } else {
                Toast.makeText(this, "This is the first card", Toast.LENGTH_SHORT).show();
            }
        });

        addBtn.setOnClickListener(v -> addEditFlashcard(null));

        editBtn.setOnClickListener(v -> addEditFlashcard(flashcards.get(currentIndex)));

        deleteBtn.setOnClickListener(v -> {
            if(flashcards.size() > 0){
                flashcards.remove(currentIndex);
                if(currentIndex >= flashcards.size()) currentIndex = flashcards.size() - 1;
                displayCard();
            }
        });
    }

    private void displayCard(){
        if(flashcards.size() == 0){
            questionText.setText("No Flashcards");
            answerText.setText("");
            answerText.setVisibility(View.GONE);
        } else {
            Flashcard card = flashcards.get(currentIndex);
            questionText.setText(card.getQuestion());
            answerText.setText(card.getAnswer());
            answerText.setVisibility(View.GONE);
        }
    }

    private void addEditFlashcard(Flashcard cardToEdit){
        View view = getLayoutInflater().inflate(R.layout.dialog_add_edit, null);
        TextView q = view.findViewById(R.id.inputQuestion);
        TextView a = view.findViewById(R.id.inputAnswer);

        if(cardToEdit != null){
            q.setText(cardToEdit.getQuestion());
            a.setText(cardToEdit.getAnswer());
        }

        new AlertDialog.Builder(this)
                .setTitle(cardToEdit == null ? "Add Flashcard" : "Edit Flashcard")
                .setView(view)
                .setPositiveButton("Save", (dialog, which) -> {
                    String question = q.getText().toString();
                    String answer = a.getText().toString();
                    if(question.isEmpty() || answer.isEmpty()){
                        Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(cardToEdit != null){
                        cardToEdit.setQuestion(question);
                        cardToEdit.setAnswer(answer);
                    } else {
                        flashcards.add(new Flashcard(question, answer));
                        currentIndex = flashcards.size() - 1;
                    }
                    displayCard();
                })
                .setNegativeButton("Cancel", null)
                .show();
    }
}
