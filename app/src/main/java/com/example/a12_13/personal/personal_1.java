package com.example.a12_13.personal;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.a12_13.R;
import com.example.a12_13.bean.user_bean;
import com.example.a12_13.util.BaseActivity;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class personal_1 extends BaseActivity {

    private String img_url="0";
    private ImageView personal1Img;
    private EditText personal1NickName;
    private RadioButton sex0;
    private RadioButton sex1;
    private EditText personal1Phone;
    private TextView personal1Idcard;
    private Button button7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_1);
        setTitle("个人中心");

        initView();
        initdata();
        initclick();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==0&&resultCode==RESULT_OK){
            Bundle extras =data.getExtras();
            Bitmap bitmap=(Bitmap)extras.get("data");
            personal1Img.setImageBitmap(bitmap);
        }
//        //上传图片，请求图片链接
//        JSONObject jsonObject=new JSONObject();
//        try {
//            jsonObject.put("file",personal1Img);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        Netword.doPost("/prod-api/common/upload", jsonObject.toString(), new OkResult() {
//            @Override
//            public void succes(JSONObject jsonObject) {
//                if (jsonObject.optInt("code")==200){
//                    Toast.makeText(personal_1.this, jsonObject.optString("url"), Toast.LENGTH_SHORT).show();
//                }else{
//                    Toast.makeText(personal_1.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
    }

    private void initclick() {
        personal1Img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent take=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (take.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(take,0);
                }

            }
        });
    }

    private void initdata() {
        Netword.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    user_bean user=new Gson().fromJson(jsonObject.optString("user"),user_bean.class);
                    Netword.doImage(personal_1.this,"/prod-api"+user.getAvatar(),personal1Img);
                    personal1NickName.setText(user.getNickName());
                    if (user.getSex().equals("0")){
                        sex0.setChecked(true);
                    }else{
                        sex1.setChecked(true);
                    }
                    personal1Phone.setText(user.getPhonenumber());
                    String s=user.getIdCard().substring(0,3);
                    s=s+"************";
                    s=s+user.getIdCard().substring(user.getIdCard().length()-4,user.getIdCard().length());
                    personal1Idcard.setText(s);

                }else{
                    Toast.makeText(personal_1.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (personal1NickName.getText().toString().isEmpty()|personal1Phone.getText().toString().isEmpty()){
                    Toast.makeText(personal_1.this, "请完善信息", Toast.LENGTH_SHORT).show();
                }else{
                    String a;
                    if (sex0.isChecked()){
                        a="0";
                    }else{
                        a="1";
                    }

                    JSONObject jsonObject=new JSONObject();
                    if (img_url.equals("0")){
                        try {
                            jsonObject.put("nickName",personal1NickName.getText().toString().trim())
                                    .put("phonenumber",personal1Phone.getText().toString().trim())
                                    .put("sex",a);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }else{

                    }
                    Netword.doPut("/prod-api/api/common/user", jsonObject.toString(), new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code")==200){
                                Toast.makeText(personal_1.this, "修改成功", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(personal_1.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


                }
            }
        });

    }

    private void initView() {
        personal1Img = findViewById(R.id.personal_1_img);
        personal1NickName = findViewById(R.id.personal1_nickName);
        sex0 = findViewById(R.id.sex_0);
        sex1 = findViewById(R.id.sex_1);
        personal1Phone = findViewById(R.id.personal1_phone);
        personal1Idcard = findViewById(R.id.personal1_idcard);
        button7 = findViewById(R.id.button7);
    }
}