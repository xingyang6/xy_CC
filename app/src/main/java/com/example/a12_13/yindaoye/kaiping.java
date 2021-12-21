package com.example.a12_13.yindaoye;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a12_13.R;

public class kaiping extends AppCompatActivity {

    private ProgressBar progressBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaiping);
        getSupportActionBar().hide();

        initView();
        initdata();
    }

    private void initdata() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    startActivity(new Intent(kaiping.this,yindao.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        CountDownTimer timer=new CountDownTimer(4000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText(millisUntilFinished/1000+"");
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    private void initView() {
        progressBar = findViewById(R.id.progressBar);
        textView = findViewById(R.id.textView);
    }
}