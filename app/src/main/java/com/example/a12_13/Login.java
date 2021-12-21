package com.example.a12_13;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.example.a12_13.yindaoye.yindao;
import com.example.a12_13.zhihui.A_data;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    private ImageView imageView2;
    private EditText usr;
    private LinearLayout lear;
    private ImageView imageView;
    private EditText pass;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("登录");
        initView();
        initdata();
        initzd();//自动登录
    }

    private void initzd() {
//        //取端口
//        SharedPreferences sp=getSharedPreferences("sp", Activity.MODE_PRIVATE);
//        if (sp.getString("url","").isEmpty()){
//            SharedPreferences.Editor editor=sp.edit();
//            editor.putString("url","http://192.168.1.103:10001");
////            editor.putString("url","http://124.93.196.45:10001");
//            editor.commit();
//        }
//        //取账号密码
//        Netword.baseurl=sp.getString("url","");
//        SharedPreferences zh=getSharedPreferences("zh", Activity.MODE_PRIVATE);
//        if (zh.getString("username","").isEmpty()){
//            SharedPreferences.Editor meditor=zh.edit();
//            meditor.putString("username","test01 ");
//            meditor.putString("password","123456");
////            meditor.putString("username","text01");
////            meditor.putString("password","151407");
//            meditor.commit();
//        }

        //自动登录
//        JSONObject jsonObject=new JSONObject();
//        try {
//            jsonObject.put("username",zh.getString("username",null))
//                    .put("password",zh.getString("password",null));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        Netword.doPost("/prod-api/api/login", jsonObject.toString(), new OkResult() {
//            @Override
//            public void succes(JSONObject jsonObject) {
//                if (jsonObject.optInt("code")==200){
//                    Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
//                    Netword.token=jsonObject.optString("token");
//                    startActivity(new Intent(Login.this, MainActivity.class));
//                }else{
//                    startActivity(new Intent(Login.this, Login.class));
//                    Toast.makeText(Login.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }

    private void initdata() {
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Netword.token=null;
                if (usr.getText().toString().isEmpty()|pass.getText().toString().isEmpty()){
                    Toast.makeText(Login.this, "请输入账号|密码", Toast.LENGTH_SHORT).show();
                }else{
                    JSONObject jsonObject1=new JSONObject();
                    try {
                        jsonObject1.put("username",usr.getText().toString().trim())
                                .put("password",pass.getText().toString().trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Netword.doPost("/prod-api/api/login", jsonObject1.toString(), new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code")==200){
                                Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
                                Netword.token=jsonObject.optString("token");
                                SharedPreferences sharedPreferences=getSharedPreferences("zh", Activity.MODE_PRIVATE);
                                SharedPreferences.Editor editor=sharedPreferences.edit();
                                editor.putString("username",usr.getText().toString().trim());
                                editor.putString("password",pass.getText().toString().trim());
                                editor.commit();
                                finish();
                                startActivity(new Intent(Login.this,MainActivity.class));
                            }else{
                                Toast.makeText(Login.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
    }

    private void initView() {
        imageView2 = findViewById(R.id.imageView2);
        usr = findViewById(R.id.usr);
        lear = findViewById(R.id.lear);
        imageView = findViewById(R.id.imageView);
        pass = findViewById(R.id.pass);
        button2 = findViewById(R.id.button2);
    }
}