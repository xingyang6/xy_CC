package com.example.a12_13.news;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_13.Adapter.news_Adapter;
import com.example.a12_13.R;
import com.example.a12_13.bean.new_bean;
import com.example.a12_13.bean.news_pl_bean;
import com.example.a12_13.home.news_init;
import com.example.a12_13.util.BaseActivity;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class news_pl extends AppCompatActivity {
    public static int pl_id;
    private EditText plCount;
    private Button plBtn;
    private RecyclerView plRecy;
    private TextView textView124;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pl);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("新闻列表");
        initView();
        initdata();

    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        startActivity(new Intent(news_pl.this,news_init.class));
        return super.onSupportNavigateUp();
    }

    private void initdata() {
        plBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (plCount.getText().toString().trim().isEmpty()) {
                    Toast.makeText(news_pl.this, "请输入", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(news_pl.this, "发表成功", Toast.LENGTH_SHORT).show();
                }
            }
        });

        pl_data();
        plBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (plCount.getText().toString().isEmpty()){
                    Toast.makeText(news_pl.this, "请输入", Toast.LENGTH_SHORT).show();
                }else{
                    JSONObject jsonObject=new JSONObject();
                    try {
                        jsonObject.put("content",plCount.getText().toString().trim())
                        .put("newsId", news_init.news_id);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Netword.doPost("/prod-api/press/pressComment", jsonObject.toString(), new OkResult() {
                        @Override
                        public void succes(JSONObject jsonObject) {
                            if (jsonObject.optInt("code")==200){
                                Toast.makeText(news_pl.this, "发表成功", Toast.LENGTH_SHORT).show();
                                pl_data();
                            }else {
                                Toast.makeText(news_pl.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                }
            }
        });
    }

    private void pl_data() {
        Netword.doGet("/prod-api/press/comments/list?newsId=" + news_init.news_id, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    List<news_pl_bean> pl = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<news_pl_bean>>() {
                    }.getType());
                    if (pl.size()==0){
                        textView124.setVisibility(View.VISIBLE);
                        plRecy.setVisibility(View.GONE);
                    }else{
                        textView124.setVisibility(View.GONE);
                        plRecy.setVisibility(View.VISIBLE);
                        List<new_bean> news = new ArrayList<>();
                        List<new_bean> newss = new ArrayList<>();
                        plRecy.setLayoutManager(new LinearLayoutManager(news_pl.this));
                        plRecy.setAdapter(new news_Adapter(news_pl.this, news, newss, pl, 2));
                    }

                } else {
                    Toast.makeText(news_pl.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initView() {
        plCount = findViewById(R.id.pl_count);
        plBtn = findViewById(R.id.pl_btn);
        plRecy = findViewById(R.id.pl_recy);
        textView124 = findViewById(R.id.textView124);
    }
}