package com.example.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    TextView textCorrect;
    TextView textScore;
    TextView textQuestion;
    TextView textOption1;
    TextView textOption2;
    TextView textOption3;
    TextView textOption4;
    TextView textTimer;
    TextView textTryAgain;

    CountDownTimer countDownTimer;
    Boolean isRunning=true;

    int locOfCorrect,score=0,noQuestion=0;
    ArrayList<Integer> answerList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton= (Button)findViewById(R.id.buttonGo);
        startButton.setVisibility(View.VISIBLE);
         textQuestion = (TextView)findViewById(R.id.textQuestion);
         textOption1 = (TextView)findViewById(R.id.textOption1);
         textOption2 = (TextView)findViewById(R.id.textOption2);
         textOption3 = (TextView)findViewById(R.id.textOption3);
         textOption4 = (TextView)findViewById(R.id.textOption4);

        textTimer=(TextView)findViewById(R.id.textTimer);

        generateNextQuestion();

        countDownTimer= new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
                textTimer.setText(l/1000+"s");
            }

            @Override
            public void onFinish() {
                textCorrect.setText("Time Up");
                textTimer.setText("0s");
                textTryAgain=(TextView)findViewById(R.id.textTryAgain);
                textTryAgain.setVisibility(View.VISIBLE);
                isRunning=false;
            }
        };

    }

    public void start(View view){
        view.setVisibility(View.INVISIBLE);
        countDownTimer.start();

        textScore = (TextView) findViewById(R.id.textScore);
        textTimer = (TextView) findViewById(R.id.textTimer);
        textQuestion = (TextView)findViewById(R.id.textQuestion);
        textOption1 = (TextView)findViewById(R.id.textOption1);
        textOption2 = (TextView)findViewById(R.id.textOption2);
        textOption3 = (TextView)findViewById(R.id.textOption3);
        textOption4 = (TextView)findViewById(R.id.textOption4);

        textTimer.setVisibility(View.VISIBLE);
        textScore.setVisibility(View.VISIBLE);
        textQuestion.setVisibility(View.VISIBLE);
        textOption1.setVisibility(View.VISIBLE);
        textOption2.setVisibility(View.VISIBLE);
        textOption3.setVisibility(View.VISIBLE);
        textOption4.setVisibility(View.VISIBLE);


        isRunning=true;
    }

    public void answerChoose(View view){
        textCorrect = (TextView) findViewById(R.id.textCorrect);
        textScore = (TextView) findViewById(R.id.textScore);

        if(isRunning) {
            if (view.getTag().toString().equals(Integer.toString(locOfCorrect))) {

                textCorrect.setVisibility(View.VISIBLE);
                textCorrect.setText("Correct");
                score++;
                textScore.setText(score+"/"+noQuestion);
                Log.i("ANS", "is C00Rect");
                generateNextQuestion();
            } else {

                textCorrect.setVisibility(View.VISIBLE);
                textScore.setText(score+"/"+noQuestion);
                textCorrect.setText("Wrong");
                Log.i("ANS", "is WRONG");
                generateNextQuestion();
            }
        }

    }

    public void generateNextQuestion(){

        if(isRunning) {
            Random random = new Random();
            int no1 = random.nextInt(51);
            int no2 = random.nextInt(21);
            answerList.clear();

            textQuestion.setText(no1 + " + " + no2);

            locOfCorrect = random.nextInt(4);

            int incorrectAns;
            for (int i = 0; i < 4; i++) {
                if (i == locOfCorrect)
                    answerList.add(no1 + no2);
                else {
                    incorrectAns = random.nextInt(51 - 20) + 21;

                    while (incorrectAns == no1 + no2)
                        incorrectAns = random.nextInt(51 - 20) + 21;

                    answerList.add(incorrectAns);
                }
            }

            textOption1.setText(Integer.toString(answerList.get(0)));
            textOption2.setText(Integer.toString(answerList.get(1)));
            textOption3.setText(Integer.toString(answerList.get(2)));
            textOption4.setText(Integer.toString(answerList.get(3)));
        }
        noQuestion++;
    }

    public void tryAgain(View view){
        textTryAgain.setVisibility(View.INVISIBLE);
        score=0;noQuestion=0;
        textScore.setText("0/0");
        textCorrect.setVisibility(View.INVISIBLE);
        countDownTimer.start();
        isRunning=true;

    }
}
