package com.example.a12_13.personal;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_13.R;
import com.example.a12_13.util.BaseActivity;

public class personal_2 extends BaseActivity {

    private TextView zf0;
    private TextView zf1;
    private RecyclerView zfRecy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_2);
        setTitle("订单列表");
        initView();
        initdata();
    }

    private void initdata() {
        zf0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zf0.setBackgroundColor(Color.rgb(63,81,181));
                zf0.setTextColor(Color.rgb(255,255,255));
                zf1.setBackgroundColor(Color.rgb(255,255,255));
                zf1.setTextColor(Color.rgb(63,81,181));
            }
        });
        zf1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zf1.setBackgroundColor(Color.rgb(63,81,181));
                zf1.setTextColor(Color.rgb(255,255,255));
                zf0.setBackgroundColor(Color.rgb(255,255,255));
                zf0.setTextColor(Color.rgb(63,81,181));
            }
        });
    }

    private void initView() {
        zf0 = findViewById(R.id.zf_0);
        zf1 = findViewById(R.id.zf_1);
        zfRecy = findViewById(R.id.zf_recy);
    }
}