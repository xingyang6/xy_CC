package com.example.a12_13.findwork;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_13.Adapter.work_Adapter;
import com.example.a12_13.R;
import com.example.a12_13.bean.jl_bean;
import com.example.a12_13.bean.zw_item;
import com.example.a12_13.bean.zw_list_bean;
import com.example.a12_13.util.BaseActivity;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class jl extends BaseActivity {

    private RecyclerView jlRecy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jl);
        setTitle("投递纪录");
        initView();
        initdata();
    }

    private void initdata() {
        Netword.doGet("/prod-api/api/job/deliver/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<jl_bean> jl=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<jl_bean>>(){
                    }.getType());
                    List<zw_item> zw=new ArrayList<>();
                    List<zw_list_bean> zwlist=new ArrayList<>();
                    jlRecy.setLayoutManager(new LinearLayoutManager(jl.this));
                    jlRecy.setAdapter(new work_Adapter(jl.this,zw,zwlist,jl,2));
                }else{
                    Toast.makeText(jl.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        jlRecy = findViewById(R.id.jl_recy);
    }
}