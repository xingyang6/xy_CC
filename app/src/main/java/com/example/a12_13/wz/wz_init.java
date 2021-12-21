package com.example.a12_13.wz;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_13.Adapter.wz_Adapter;
import com.example.a12_13.R;
import com.example.a12_13.bean.wz_init_bean;
import com.example.a12_13.util.BaseActivity;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.List;

public class wz_init extends BaseActivity {
    public static int a = 5;
    private RecyclerView zwInitRecy;
    private Button more;
    private TextView textView41;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wz_init);
        setTitle("违章记录");
        initView();
        initdata();

    }

    private void initdata() {
        Netword.doGet("/prod-api/api/traffic/illegal/list?licencePlate="+wz_home.hp, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    List<wz_init_bean> init = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<wz_init_bean>>() {
                    }.getType());
                    if (init.size() == 0) {
                        textView41.setVisibility(View.VISIBLE);
                        zwInitRecy.setVisibility(View.GONE);
                        more.setVisibility(View.GONE);
                    } else {
                        textView41.setVisibility(View.GONE);
                        zwInitRecy.setVisibility(View.VISIBLE);
                        zwInitRecy.setLayoutManager(new LinearLayoutManager(wz_init.this));
                        zwInitRecy.setAdapter(new wz_Adapter(wz_init.this, init));
                    }

                } else {
                    Toast.makeText(wz_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Netword.doGet("/prod-api/api/traffic/illegal/list", new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code") == 200) {
                            more.setVisibility(View.INVISIBLE);
                            List<wz_init_bean> init = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<wz_init_bean>>() {
                            }.getType());
                            a = init.size();
                            zwInitRecy.setLayoutManager(new LinearLayoutManager(wz_init.this));
                            zwInitRecy.setAdapter(new wz_Adapter(wz_init.this, init));
                        } else {
                            Toast.makeText(wz_init.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void initView() {
        zwInitRecy = findViewById(R.id.zw_init_recy);
        more = findViewById(R.id.more);
        textView41 = findViewById(R.id.textView41);
    }
}