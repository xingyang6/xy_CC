package com.example.a12_13.findwork;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12_13.R;
import com.example.a12_13.bean.jl_bena;
import com.example.a12_13.bean.user_bean;
import com.example.a12_13.util.BaseActivity;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class gr extends BaseActivity {

    private ImageView jlImg;
    private EditText jlUserName;
    private EditText jlNickName;
    private RadioButton sex0;
    private RadioButton sex1;
    private TextView nickName;
    private EditText jlEmail;
    private EditText phonenumber;
    private TextView jlPositionId;
    private EditText jlExperience;
    private EditText jlMostEducation;
    private EditText jlExperience1;
    private EditText jlAddress;
    private EditText jlMoney;
    private EditText jlEducation;
    private EditText jlIndividualResume;
    private Button button3;
    private Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gr);
        setTitle("个人简历");
        initView();
        initdata();
    }

    private void initdata() {
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jlAddress.getText().toString().trim().isEmpty() | jlEducation.getText().toString().trim().isEmpty() | jlExperience.getText().toString().trim().isEmpty() | jlIndividualResume.getText().toString().isEmpty() | jlMoney.getText().toString().isEmpty() | jlMostEducation.getText().toString().isEmpty()) {
                    Toast.makeText(gr.this, "请完善信息", Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("address", jlAddress.getText().toString().trim())
                                .put("education", jlEducation.getText().toString().trim())
                                .put("experience", jlExperience.getText().toString().trim())
                                .put("individualResume", jlIndividualResume.getText().toString().trim())
                                .put("money", jlMoney.getText().toString().trim())
                                .put("mostEducation", jlMostEducation.getText().toString().trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Netword.doPost("/prod-api/api/job/resume", jsonObject.toString(), new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code") == 200) {
                                Toast.makeText(gr.this, "新建成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(gr.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });


        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (jlAddress.getText().toString().trim().isEmpty() | jlEducation.getText().toString().trim().isEmpty() | jlExperience.getText().toString().trim().isEmpty() | jlIndividualResume.getText().toString().isEmpty() | jlMoney.getText().toString().isEmpty() | jlMostEducation.getText().toString().isEmpty()) {
                    Toast.makeText(gr.this, "请完善信息", Toast.LENGTH_SHORT).show();
                } else {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("address", jlAddress.getText().toString().trim())
                                .put("education", jlEducation.getText().toString().trim())
                                .put("experience", jlExperience.getText().toString().trim())
                                .put("id", 3)
                                .put("individualResume", jlIndividualResume.getText().toString().trim())
                                .put("money", jlMoney.getText().toString().trim())
                                .put("mostEducation", jlMostEducation.getText().toString().trim());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Netword.doPut("/prod-api/api/job/resume", jsonObject.toString(), new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code") == 200) {
                                Toast.makeText(gr.this, "保存成功", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(gr.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }

        });

        Netword.doGet("/prod-api/api/common/user/getInfo", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    user_bean user = new Gson().fromJson(jsonObject.optString("user"), user_bean.class);
                    jlUserName.setText(user.getUserName());
                    jlNickName.setText(user.getNickName());
                    if (user.getSex().equals("0")) {
                        sex0.setChecked(true);
                    } else {
                        sex1.setChecked(true);
                    }
                    jlEmail.setText(user.getEmail());
                    phonenumber.setText(user.getPhonenumber());

                } else {
                    Toast.makeText(gr.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //求职信息
        Netword.doGet("/prod-api/api/job/resume/" + 3, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    jl_bena jl = new Gson().fromJson(jsonObject.optString("data"), jl_bena.class);
                    for (int a = 0; a < work_home.user_id.size(); a++) {
                        if (work_home.user_id.get(a).equals(jl.getPositionId())) {
                            jlPositionId.setText(work_home.user_name.get(a));
                        }
                    }
                    jlExperience.setText(jl.getExperience());
                    jlExperience1.setText(jl.getExperience());
                    jlMostEducation.setText(jl.getMostEducation());
                    jlAddress.setText(jl.getAddress());
                    jlMoney.setText("￥" + jl.getMoney() + "");
                    jlEducation.setText(jl.getEducation());
                    jlIndividualResume.setText(jl.getIndividualResume());

                } else {
                    Toast.makeText(gr.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        jlImg = findViewById(R.id.jl_img);
        jlUserName = findViewById(R.id.jl_userName);
        jlNickName = findViewById(R.id.jl_nickName);
        sex0 = findViewById(R.id.sex0);
        sex1 = findViewById(R.id.sex1);
        jlEmail = findViewById(R.id.jl_email);
        phonenumber = findViewById(R.id.phonenumber);
        jlPositionId = findViewById(R.id.jl_positionId);

        jlExperience = findViewById(R.id.jl_experience);
        jlMostEducation = findViewById(R.id.jl_mostEducation);
        jlExperience1 = findViewById(R.id.jl_experience1);
        jlAddress = findViewById(R.id.jl_address);
        jlMoney = findViewById(R.id.jl_money);
        jlEducation = findViewById(R.id.jl_education);
        jlIndividualResume = findViewById(R.id.jl_individualResume);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
    }
}