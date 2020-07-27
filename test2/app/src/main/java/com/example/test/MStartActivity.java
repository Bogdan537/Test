package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_start);
    }
    public void startQuiz(View view){

        int quizCategory = 0;
        switch (view.getId()){
            case R.id.ALL:
                quizCategory = 1;
                break;

        }
        Intent intent = new Intent(getApplicationContext(), ModulActivity.class);
        intent.putExtra("QUIZ_CATEGORY", quizCategory);
        startActivity(intent);
    }

}
