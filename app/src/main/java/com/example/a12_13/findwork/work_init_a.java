package com.example.a12_13.findwork;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.a12_13.R;
import com.example.a12_13.bean.gs_init;
import com.example.a12_13.bean.work_init;
import com.example.a12_13.util.BaseActivity;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class work_init_a extends BaseActivity {
    public static int init_id;
    public static int int_id;


    private int workIntroductory_1;
    private TextView workName;
    private TextView workObligation;
    private TextView workAdress;
    private TextView workSalary;
    private TextView workContacts;
    private TextView workNeed;
    private TextView workIntroductory;
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_init);
        setTitle("公司信息");
        initView();
        initdata();
    }

    private void initdata() {
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("companyId",init_id)
                            .put("postId",workIntroductory_1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Netword.doPost("/prod-api/api/job/deliver",jsonObject.toString(), new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            Toast.makeText(work_init_a.this, "投递成功", Toast.LENGTH_SHORT).show();
                            button5.setText("已投递");
                            button5.setEnabled(false);
                        }else{
                            Toast.makeText(work_init_a.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        Netword.doGet("/prod-api/api/job/post/" + int_id, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    work_init init=new Gson().fromJson(jsonObject.optString("data"),work_init.class);
                    workName.setText("职位名称："+init.getName());
                    workObligation.setText("岗位职责："+init.getObligation());
                    workAdress.setText("公司地址："+init.getAddress());
                    workSalary.setText("薪资待遇："+init.getSalary()+"元");
                    workContacts.setText("联系人名称："+init.getContacts());
                    workNeed.setText("工作经验："+init.getNeed());
                    workIntroductory_1=init.getProfessionId();
                }else{
                    Toast.makeText(work_init_a.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        Netword.doGet("/prod-api/api/job/company/" + init_id, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    gs_init gsinit=new Gson().fromJson(jsonObject.optString("data"),gs_init.class);
                    workIntroductory.setText("公司简介："+gsinit.getIntroductory());
                }else{
                    Toast.makeText(work_init_a.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void initView() {
        workName = findViewById(R.id.work_name);
        workObligation = findViewById(R.id.work_obligation);
        workAdress = findViewById(R.id.work_adress);
        workSalary = findViewById(R.id.work_salary);
        workContacts = findViewById(R.id.work_contacts);
        workNeed = findViewById(R.id.work_need);
        workIntroductory = findViewById(R.id.work_introductory);
        button5 = findViewById(R.id.button5);
    }
}