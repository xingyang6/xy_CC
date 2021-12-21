package com.example.a12_13.home;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_13.Adapter.home_Adapter;
import com.example.a12_13.R;
import com.example.a12_13.bean.honme_tuijain_bean;
import com.example.a12_13.bean.new_bean;
import com.example.a12_13.util.BaseActivity;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class home_sear extends BaseActivity {
    public static String string;
    private RecyclerView searRecy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_sear);
        setTitle("搜索结果");

        initView();
        initdata();
    }

    private void initdata() {
        Netword.doGet("/prod-api/press/press/list?title="+string, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<new_bean> news=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<new_bean>>(){
                    }.getType());
                    List<honme_tuijain_bean> mtuijian=new ArrayList<>();
                    searRecy.setLayoutManager(new LinearLayoutManager(home_sear.this));
                    searRecy.setAdapter(new home_Adapter(home_sear.this,mtuijian,news,3));
                }else{
                    Toast.makeText(home_sear.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        searRecy = findViewById(R.id.sear_recy);
    }
}