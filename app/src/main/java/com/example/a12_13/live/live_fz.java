package com.example.a12_13.live;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12_13.R;
import com.example.a12_13.util.BaseActivity;

public class live_fz extends BaseActivity {

    private TextView wo;
    private TextView fm;
    private TextView fd;
    private TextView py;
    private EditText editTextTextPersonName2;
    private Button button11;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_fz);
        setTitle("户号管理");
        initView();
        initdata();
    }

    private void initdata() {
        wo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                huhao.fz=wo.getText().toString().trim();
                huhao.imageView13.setVisibility(View.GONE);
                huhao.textView43.setText(wo.getText().toString().trim());
                finish();
            }
        });
        fm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                huhao.fz=fm.getText().toString().trim();
                huhao.imageView13.setVisibility(View.GONE);
                huhao.textView43.setText(fm.getText().toString().trim());
                finish();
            }
        });
        fd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                huhao.fz=fd.getText().toString().trim();
                huhao.imageView13.setVisibility(View.GONE);
                huhao.textView43.setText(fd.getText().toString().trim());
                finish();
            }
        });
        py.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                huhao.fz=py.getText().toString().trim();
                huhao.imageView13.setVisibility(View.GONE);
                huhao.textView43.setText(py.getText().toString().trim());
                finish();
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextTextPersonName2.getText().toString().isEmpty()){
                    Toast.makeText(live_fz.this, "请输入自定义内容", Toast.LENGTH_SHORT).show();

                }else{
                    finish();
                    huhao.fz=editTextTextPersonName2.getText().toString().trim();
                    huhao.imageView13.setVisibility(View.GONE);
                    huhao.textView43.setText(editTextTextPersonName2.getText().toString());
                }
            }
        });

    }

    private void initView() {
        wo = findViewById(R.id.wo);
        fm = findViewById(R.id.fm);
        fd = findViewById(R.id.fd);
        py = findViewById(R.id.py);
        editTextTextPersonName2 = findViewById(R.id.editTextTextPersonName2);
        button11 = findViewById(R.id.button11);
    }
}