package com.example.a12_13.live;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12_13.R;
import com.example.a12_13.bean.user_bean;
import com.example.a12_13.personal.personal_1;
import com.example.a12_13.util.BaseActivity;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.google.gson.Gson;

import org.json.JSONObject;

public class dian_init extends BaseActivity {

    private TextView textView58;
    private TextView textView59;
    private TextView textView60;
    private TextView textView61;
    private TextView textView62;
    private TextView textView63;
    private EditText editTextTextPersonName3;
    private TextView textView64;
    private EditText editTextTextPersonName4;
    private TextView textView65;
    private EditText editTextTextPersonName6;
    private TextView textView67;
    private EditText editTextTextPersonName5;
    private Button button12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dian_init);
        setTitle("电费缴费");
        initView();
        initdatta();
    }

    private void initdatta() {
        if (!huhao.huhao.isEmpty()){
            editTextTextPersonName4.setText(huhao.huhao);
        }
        if (!huhao.huming.isEmpty()){
            editTextTextPersonName6.setText(huhao.huming);
        }
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Netword.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    user_bean user=new Gson().fromJson(jsonObject.optString("user"),user_bean.class);
                    textView62.setText(user.getScore()+"");
                }else{
                    Toast.makeText(dian_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        textView58 = findViewById(R.id.textView58);
        textView59 = findViewById(R.id.textView59);
        textView60 = findViewById(R.id.textView60);
        textView61 = findViewById(R.id.textView61);
        textView62 = findViewById(R.id.textView62);
        textView63 = findViewById(R.id.textView63);
        editTextTextPersonName3 = findViewById(R.id.editTextTextPersonName3);
        textView64 = findViewById(R.id.textView64);
        editTextTextPersonName4 = findViewById(R.id.editTextTextPersonName4);
        textView65 = findViewById(R.id.textView65);
        editTextTextPersonName6 = findViewById(R.id.editTextTextPersonName6);
        textView67 = findViewById(R.id.textView67);
        editTextTextPersonName5 = findViewById(R.id.editTextTextPersonName5);
        button12 = findViewById(R.id.button12);
    }
}