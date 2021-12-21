package com.example.a12_13.live;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.a12_13.R;
import com.example.a12_13.util.BaseActivity;

public class shui extends BaseActivity {

    private TextView textView52;
    private TextView textView53;
    private TextView textView54;
    private TextView textView55;
    private EditText jfDanwei;
    private TextView textView57;
    private EditText jfHuhao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shui);
        setTitle("水费缴费");
        initView();
        initdata();
    }

    private void initdata() {
        if (!huhao.huhao.isEmpty()){
            jfHuhao.setText(huhao.huhao);
        }
    }

    private void initView() {
        textView52 = findViewById(R.id.textView52);
        textView53 = findViewById(R.id.textView53);
        textView54 = findViewById(R.id.textView54);
        textView55 = findViewById(R.id.textView55);
        jfDanwei = findViewById(R.id.jf_danwei);
        textView57 = findViewById(R.id.textView57);
        jfHuhao = findViewById(R.id.jf_huhao);
    }
}