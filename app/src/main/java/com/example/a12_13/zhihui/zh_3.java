package com.example.a12_13.zhihui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.example.a12_13.R;
import com.example.a12_13.util.BaseActivity;

public class zh_3 extends BaseActivity {

    private RatingBar ratingBar;
    private Button button14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zh_3);
        setTitle("巡检记录");
        initView();
        initdata();
    }

    private void initdata() {
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(zh_3.this, "打分成功", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        ratingBar = findViewById(R.id.ratingBar);
        button14 = findViewById(R.id.button14);
    }
}