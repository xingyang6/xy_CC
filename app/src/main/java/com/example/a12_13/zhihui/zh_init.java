package com.example.a12_13.zhihui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.a12_13.R;
import com.example.a12_13.util.BaseActivity;

public class zh_init extends BaseActivity {
    public static int[] data={0,0,0,0};
    public static int zh_type;
    private TextView zhInitTitle;
    private ImageView zhInitImg;
    private TextView zhInitCount;
    private Button zhInitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zh_init);
        setTitle("养老院详情");
        initView();
        initdata();
    }

    private void initdata() {
        zhInitTitle.setText(A_data.zh_title.get(zh_type));
        zhInitCount.setText(A_data.zh_count.get(zh_type));
        zhInitImg.setImageResource(A_data.zh_img.get(zh_type));
        if (data[zh_type]==0){
            zhInitBtn.setText("预定");
        }else{
            zhInitBtn.setText("已预定");
        }
        zhInitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data[zh_type]==0){
                    zhInitBtn.setText("已预定");
                    data[zh_type]=1;
                }else{
                    zhInitBtn.setText("预定");
                    data[zh_type]=0;
                }
            }
        });

    }

    private void initView() {
        zhInitTitle = findViewById(R.id.zh_init_title);
        zhInitImg = findViewById(R.id.zh_init_img);
        zhInitCount = findViewById(R.id.zh_init_count);
        zhInitBtn = findViewById(R.id.zh_init_btn);
    }
}