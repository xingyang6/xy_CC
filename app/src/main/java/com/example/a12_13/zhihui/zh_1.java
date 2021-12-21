package com.example.a12_13.zhihui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12_13.R;
import com.example.a12_13.util.BaseActivity;
import com.example.a12_13.util.Netword;

public class zh_1 extends BaseActivity {
    public static int sum=0;
    private String[] sp1 = {"正常", "痴呆", "抑郁", "暴力"};
    private String[] sp2 = {"正常", "拐杖", "轮椅", "卧床"};
    private String[] sp3 = {"饮食", "洗澡", "穿衣", "修饰"};

    private String[] ssdp1 = {"", "需要专人看护,", "需要心理疏导,", "需要防止打砸,"};
    private String[] ssdp2 = {" ", "需要拐杖,", "需要轮椅,", "需要专人看护,"};
    private String[] ssdp3 = {"需要有人喂食,", "需要有人洗澡,", "需要有人穿衣,", "需要有人整理仪表,"};
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;
    private TextView textView81;
    private int money = 0;
    private LinearLayout zgJg;
    private TextView textView84;
    private Button button13;
    private EditText text1;
    private EditText text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zh_1);
        setTitle("健康评估");
        initView();
        initdata();
    }

    private void initdata() {
        Netword.doSpinner(zh_1.this, spinner1, sp1);
        Netword.doSpinner(zh_1.this, spinner2, sp2);
        Netword.doSpinner(zh_1.this, spinner3, sp3);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    textView81.setText("病人需要" + ssdp1[position] + textView81.getText().toString().trim());
                    money = money + position * 100;
                    textView84.setText(money+"");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position!=0){
                    textView81.setText("病人需要" + ssdp2[position] + textView81.getText().toString().trim());
                    money = money + position * 100;
                    textView84.setText(money+"");
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    textView81.setText("病人需要" + ssdp3[position] + textView81.getText().toString().trim());
                    money = money + position * 100;
                    textView84.setText(money+"");

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text1.getText().toString().trim().isEmpty()|text2.getText().toString().trim().isEmpty()){
                    Toast.makeText(zh_1.this, "请完善信息", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(zh_1.this, "提交成功", Toast.LENGTH_SHORT).show();
                    sum=1;
                    finish();
                }

            }
        });

    }

    private void initView() {
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        spinner3 = findViewById(R.id.spinner3);
        textView81 = findViewById(R.id.textView81);
        zgJg = findViewById(R.id.zg_jg);
        textView84 = findViewById(R.id.textView84);
        button13 = findViewById(R.id.button13);
        text1 = findViewById(R.id.text_1);
        text2 = findViewById(R.id.text_2);
    }
}