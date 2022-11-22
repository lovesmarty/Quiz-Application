package com.example.myquizapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView totalQuestionTextView, questionTextView;
    Button  answerA, answerB , answerC, answerD, submit;


    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        totalQuestionTextView = findViewById(R.id.total_question);
        questionTextView = findViewById(R.id.question_btn);
        answerA = findViewById(R.id.btn_a);
        answerB= findViewById(R.id.btn_b);
        answerC= findViewById(R.id.btn_c);
        answerD = findViewById( R.id.btn_d);
        submit = findViewById(R.id.submit_button);


        answerA.setOnClickListener(this);
        answerB.setOnClickListener(this);
        answerC.setOnClickListener(this);
        answerD.setOnClickListener(this);
        submit.setOnClickListener(this);

        totalQuestionTextView.setText("total question:"+totalQuestion);
        loadNewQuestion();
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);





    }

    @Override
    public void onClick(View view) {
        Button clickButton = (Button) view;
        answerA.setBackground(getResources().getDrawable(R.drawable.button_background));
        answerB.setBackground(getResources().getDrawable(R.drawable.button_background));
        answerC.setBackground(getResources().getDrawable(R.drawable.button_background));
        answerD.setBackground(getResources().getDrawable(R.drawable.button_background));



        if(clickButton.getId() == (R.id.submit_button)){
            if(selectedAnswer.equals(QuestionAnswer.answer[currentQuestionIndex])){
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();


        }else{

            selectedAnswer = clickButton.getText().toString();
            clickButton.setBackgroundColor(Color.BLUE);

        }



    }
    public void loadNewQuestion(){
        if(currentQuestionIndex==totalQuestion){
            finishQuiz();
            return;
        }
        questionTextView.setText(QuestionAnswer.question[currentQuestionIndex]);
        answerA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        answerB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        answerC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        answerD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }
    void finishQuiz(){
        String passStatus = "";
        if(score > totalQuestion*0.6){
            passStatus = "Passed";

        }else{
            passStatus = "Failed";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score " + score +"out of "+ totalQuestion)
                .setPositiveButton("Restart",(dialogInterface, i) -> restartQuiz())
                .setCancelable(false)//we also set negative button or neutral button
                .show();

    }
    void restartQuiz(){
        score =0;
        currentQuestionIndex =0;
        loadNewQuestion();

    }
}


