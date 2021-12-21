package com.example.a12_13.personal;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12_13.R;
import com.example.a12_13.util.BaseActivity;

public class personal_4 extends BaseActivity {

    private EditText per4Edit;
    private TextView per4Sum;
    private Button per4Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_4);
        setTitle("意见反馈");
        initView();
        initdata();
    }

    private void initdata() {
        per4Edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                per4Sum.setText(s.length()+"/150");
            }
        });
        per4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (per4Edit.getText().toString().isEmpty()){
                    Toast.makeText(personal_4.this, "请输入", Toast.LENGTH_SHORT).show();
                }else{
                    finish();
                    Toast.makeText(personal_4.this, "提交成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        per4Edit = findViewById(R.id.per4_edit);
        per4Sum = findViewById(R.id.per4_sum);
        per4Btn = findViewById(R.id.per4_btn);
    }
}