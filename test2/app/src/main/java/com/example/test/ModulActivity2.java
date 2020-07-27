package com.example.test;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class ModulActivity2 extends AppCompatActivity {
    private TextView countLabel;
    private TextView questionLabel;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int  QUIZ_COUNT = 5;

    ArrayList<ArrayList<String>>quizArray = new ArrayList<>();

    String quizData[][] = {
            { " — це одинична задокументована потреба у тому, що конкретний продукт чи послуга повинні бути або виконувати.","Requirements","Dependencies","Test Cases","Implementation"},
            {" — послідовність подій.","Event Flow","Implementation","Maintenance","Post-conditions"},
            { " залежності цієї вимоги від іншої(-их) або її взаємодія з іншими вимогами.","Dependencies ","Medium","Requirements ","Duplicates"},
            {  " вимоги описують внутрішню функціональність системи, її поведінку: обчислення даних, обмін та керування даними, вивчення даних та інших конкретних функцій, які система повинна робити. "," Functional Requirements","Requirements","Non-Functional","Operational Requirements"},
            { " Вимоги до продуктивності описують очікувані параметри продуктивності системи у термінах ‘не більше ніж’, ‘не менше ніж","Performance Requirements","Capacity Requirements ","Retention Requirements","Medium"},
            { " це по суті вимоги, але прості для сприйняття клієнта.","User Stories","Sprint Planning","Card ","Event Flow"},
            { " такі специфікації, що можна змінити","Modifiable","Duplicates","Consistent ","Verifiable "},

    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modul2);

        countLabel = (TextView)findViewById(R.id.countLabel);
        questionLabel = (TextView)findViewById(R.id.questionLabel);
        answerBtn1 = (Button)findViewById(R.id.answerBtn1);
        answerBtn2 = (Button)findViewById(R.id.answerBtn2);
        answerBtn3 = (Button)findViewById(R.id.answerBtn3);
        answerBtn4 = (Button)findViewById(R.id.answerBtn4);

        int quizCategory = getIntent().getIntExtra("QUIZ_CATEGORY",0);

        Log.v("CATEGORY_TAG", quizCategory + "");

        for (int i = 0; i < quizData.length; i++) {
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]);  //Question
            tmpArray.add(quizData[i][1]);  //Roght Answer
            tmpArray.add(quizData[i][2]);  // Choice1
            tmpArray.add(quizData[i][3]);  // Choice2
            tmpArray.add(quizData[i][4]);  //Choice3

            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz(){
        countLabel.setText("Q" + quizCount);
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());
        ArrayList<String> quiz = quizArray.get(randomNum);
        questionLabel.setText(quiz.get(0));
        rightAnswer = quiz.get(1);
        quiz.remove(0);
        Collections.shuffle(quiz);
        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));
        quizArray.remove(randomNum);
    }

    public void checkAnswer(View view) {
        Button answerBtn = (Button) findViewById(view.getId());
        String btnText = answerBtn.getText().toString();
        String alertTitle;
        if (btnText.equals(rightAnswer)){
            alertTitle = "Correct";
            rightAnswerCount++;
        } else {
            alertTitle = "Wrong";
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Answer :"+ rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (quizCount == QUIZ_COUNT) {
                    Intent intent = new Intent(getApplicationContext(), RezultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    startActivity(intent);

                }
                else {
                    quizCount++;
                    showNextQuiz();
                }
            }
        });
        builder.setCancelable(false);
        builder.show();

    }
    }

