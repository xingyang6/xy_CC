package com.example.a12_13.yindaoye;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a12_13.R;
import com.example.a12_13.util.BaseActivity;
import com.example.a12_13.util.Netword;

public class ip_yd extends BaseActivity {

    private EditText editTextTextPersonName;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip_yd);
        setTitle("端口设置");
        initView();
        initdata();
    }

    private void initdata() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextTextPersonName.getText().toString().trim().isEmpty()){
                    Toast.makeText(ip_yd.this, "请输入端口", Toast.LENGTH_SHORT).show();
                }else{
                    SharedPreferences sp=getSharedPreferences("sp", Activity.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("url","http://"+editTextTextPersonName.getText().toString().trim());
                    editor.commit();
                    Toast.makeText(ip_yd.this, "提交成功", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    private void initView() {
        editTextTextPersonName = findViewById(R.id.editTextTextPersonName);
        button = findViewById(R.id.button);
    }
}