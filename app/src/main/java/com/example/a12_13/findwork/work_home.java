package com.example.a12_13.findwork;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a12_13.Adapter.work_Adapter;
import com.example.a12_13.R;
import com.example.a12_13.bean.jl_bean;
import com.example.a12_13.bean.zw_item;
import com.example.a12_13.bean.zw_list_bean;
import com.example.a12_13.util.BaseActivity;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.example.a12_13.util.kg;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class work_home extends BaseActivity {
    public static String professionId;
    public static List<String> user_name = new ArrayList<>();
    public static List<Integer> user_id = new ArrayList<>();

    private List<Integer> data = new ArrayList<>();
    //    private int[] data={R.drawable.w1,R.drawable.w2,R.drawable.w3};
    private Banner workBanenr;
    private LinearLayout line1;
    private LinearLayout line2;
    private RecyclerView tuijainZwListRecy;
    public static RecyclerView zwListRecy;
    public static TextView text15;
    private SearchView workSear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_home);
        setTitle("找工作");
        initView();
        initdata();

    }

    private void initdj(String s) {
        Netword.doGet("/prod-api/api/job/post/list?name="+s, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    List<zw_list_bean> zwlist = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<zw_list_bean>>() {
                    }.getType());
                    if (zwlist.size()==0){
                        text15.setVisibility(View.VISIBLE);
                        zwListRecy.setVisibility(View.GONE);
                    }else{
                        text15.setVisibility(View.GONE);
                        zwListRecy.setVisibility(View.VISIBLE);
                    }
                    List<zw_item> zw = new ArrayList<>();
                    zwListRecy.setLayoutManager(new LinearLayoutManager(work_home.this));
                    List<jl_bean> jl=new ArrayList<>();
                    zwListRecy.setAdapter(new work_Adapter(work_home.this, zw, zwlist, jl,1));
                } else {
                    Toast.makeText(work_home.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initdata() {
        line2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(work_home.this,jl.class));
            }
        });
        workSear.setIconified(true);
        workSear.setImeOptions(EditorInfo.IME_ACTION_PREVIOUS);
        workSear.setSubmitButtonEnabled(true);
        workSear.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (kg.isFastDoubleclick()){
                    return false;
                }else {
                    //收齐小键盘
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);

                    //获取焦点
                    zwListRecy.setFocusable(true);
                    zwListRecy.setEnabled(true);
                    zwListRecy.setFocusableInTouchMode(true);
                    zwListRecy.requestFocus();
                    zwListRecy.requestFocusFromTouch();
                    initdj(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        data.add(R.drawable.w1);
        data.add(R.drawable.w2);
        data.add(R.drawable.w3);
        workBanenr.setImages(data).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object o, ImageView imageView) {
                Glide.with(context).load(o).into(imageView);
            }
        }).start();
        Netword.doGet("/prod-api/api/job/profession/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    List<zw_item> zw = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<zw_item>>() {
                    }.getType());
                    for (int a = 0; a < zw.size(); a++) {
                        user_name.add(zw.get(a).getProfessionName());
                        user_id.add(zw.get(a).getId());
                    }
                    List<zw_list_bean> zwlist = new ArrayList<>();
                    List<jl_bean> jl=new ArrayList<>();
                    tuijainZwListRecy.setLayoutManager(new GridLayoutManager(work_home.this, 3));
                    tuijainZwListRecy.setAdapter(new work_Adapter(work_home.this, zw, zwlist, jl,0));
                } else {
                    Toast.makeText(work_home.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Netword.doGet("/prod-api/api/job/post/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code") == 200) {
                    List<zw_list_bean> zwlist = new Gson().fromJson(jsonObject.optString("rows"), new TypeToken<List<zw_list_bean>>() {
                    }.getType());
                    List<zw_item> zw = new ArrayList<>();
                    zwListRecy.setLayoutManager(new LinearLayoutManager(work_home.this));
                    List<jl_bean> jl=new ArrayList<>();
                    zwListRecy.setAdapter(new work_Adapter(work_home.this, zw, zwlist, jl,1));
                } else {
                    Toast.makeText(work_home.this, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        line1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(work_home.this, gr.class));
            }
        });

    }

    private void initView() {
        workBanenr = findViewById(R.id.work_banenr);
        line1 = findViewById(R.id.line_1);
        line2 = findViewById(R.id.line_2);
        tuijainZwListRecy = findViewById(R.id.tuijain_zw_list_recy);
        zwListRecy = findViewById(R.id.zw_list_recy);
        text15 = findViewById(R.id.text15);
        workSear = findViewById(R.id.work_sear);
    }
}