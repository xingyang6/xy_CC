package com.example.a12_13.personal;

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
import com.example.a12_13.util.OkResult;

import org.json.JSONException;
import org.json.JSONObject;

public class personal_3 extends BaseActivity {

    private EditText oldpass;
    private EditText newpass;
    private Button button8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_3);
        setTitle("修改密码");
        initView();
        initdata();
    }

    private void initdata() {
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (oldpass.getText().toString().isEmpty()|newpass.getText().toString().isEmpty()){
                    Toast.makeText(personal_3.this, "请输入", Toast.LENGTH_SHORT).show();
                }else{
                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("oldPassword",oldpass.getText().toString().trim())
                                .put("newPassword",newpass.getText().toString().trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    Netword.doPut("/prod-api/api/common/user/resetPwd", jsonObject.toString(), new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code")==200){
                                finish();
                                SharedPreferences zh=getSharedPreferences("zh", Activity.MODE_PRIVATE);
                                SharedPreferences.Editor meditor=zh.edit();
                                meditor.putString("password",newpass.getText().toString().trim());
                                meditor.commit();

                                Toast.makeText(personal_3.this, "修改成功", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(personal_3.this,jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        oldpass = findViewById(R.id.oldpass);
        newpass = findViewById(R.id.newpass);
        button8 = findViewById(R.id.button8);
    }
}