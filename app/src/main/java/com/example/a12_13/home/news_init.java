package com.example.a12_13.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_13.Adapter.news_Adapter;
import com.example.a12_13.R;
import com.example.a12_13.bean.new_bean;
import com.example.a12_13.bean.news_init_bean;
import com.example.a12_13.bean.news_pl_bean;
import com.example.a12_13.news.news_pl;
import com.example.a12_13.util.BaseActivity;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class news_init extends BaseActivity {
    public static int news_id;
    private TextView newsInitTitle;
    private ImageView newsInitImg;
    private TextView newsInitCount;
    private RecyclerView newsInitRecy;
    private LinearLayout newsPl;
    private TextView plNum;
    private EditText editTextTextPersonName7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_init);
        setTitle("新闻详情");
        initView();
        initdata();

    }

    private void initdata() {
        Netword.doGet("/prod-api/press/press/" + news_id, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    news_init_bean init = new Gson().fromJson(jsonObject.optString("data"), news_init_bean.class);
                    Netword.doImage(news_init.this, init.getCover(), newsInitImg);
                    newsInitTitle.setText(init.getTitle());
                    newsInitCount.setText(Html.fromHtml(init.getContent()));
                    if (init.getCommentNum()==null){
                        plNum.setText("0");
                    }else{
                        String s=init.getCommentNum().toString().substring(0,init.getCommentNum().toString().length()-2);
                        plNum.setText(s+"");
                    }
                } else {
                    Toast.makeText(news_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Netword.doGet("/prod-api/press/press/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    List<new_bean> tj_news = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<new_bean>>() {
                    }.getType());
                    List<new_bean> newss = new ArrayList<>();

                    for (int a = 0; a < 4; a++) {
                        if (tj_news.get(a).getId() != news_id) {
                            newss.add(tj_news.get(a));
                        }
                    }
                    List<new_bean> n = new ArrayList<>();
                    List<news_pl_bean> pl = new ArrayList<>();
                    newsInitRecy.setLayoutManager(new LinearLayoutManager(news_init.this));
                    newsInitRecy.setAdapter(new news_Adapter(news_init.this, n, newss, pl, 1));
                } else {
                    Toast.makeText(news_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }

            }
        });

        newsPl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news_pl.pl_id=news_id;
                finish();
                startActivity(new Intent(news_init.this, news_pl.class));
            }
        });
        editTextTextPersonName7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                news_pl.pl_id=news_id;
                finish();
                startActivity(new Intent(news_init.this, news_pl.class));
            }
        });
    }

    private void initView() {
        newsInitTitle = findViewById(R.id.news_init_title);
        newsInitImg = findViewById(R.id.news_init_img);
        newsInitCount = findViewById(R.id.news_init_count);
        newsInitRecy = (RecyclerView) findViewById(R.id.news_init_recy);
        newsPl = (LinearLayout) findViewById(R.id.news_lear_pl);
        plNum = (TextView) findViewById(R.id.pl_num);
        editTextTextPersonName7 = findViewById(R.id.editTextTextPersonName7);
    }
}