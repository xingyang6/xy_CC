package com.example.a12_13.yindaoye;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.a12_13.Login;
import com.example.a12_13.MainActivity;
import com.example.a12_13.R;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.example.a12_13.zhihui.A_data;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class yindao extends AppCompatActivity {
    private List<Integer> data = new ArrayList<>();
    private int curr = 0;
    private ViewPager viewpager;
    private LinearLayout picture;
    private Button ip;
    private Button goHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yindao);
        getSupportActionBar().hide();
        A_data.initdata();
        initView();
        initdata();
        initclick();
    }

    private void initclick() {
        ip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(yindao.this,ip_yd.class));
            }
        });
        goHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取端口
                SharedPreferences sp=getSharedPreferences("sp", Activity.MODE_PRIVATE);
                if (sp.getString("url","").isEmpty()){
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putString("url","http://124.93.196.45:10001");
                    editor.commit();
                }
                //取账号密码
                Netword.baseurl=sp.getString("url","");
                SharedPreferences zh=getSharedPreferences("zh", Activity.MODE_PRIVATE);
                if (zh.getString("username","").isEmpty()){
                    SharedPreferences.Editor meditor=zh.edit();
                    meditor.putString("username","text01");
                    meditor.putString("password","151407");
                    meditor.commit();
                }

                //登录
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("username",zh.getString("username",null))
                            .put("password",zh.getString("password",null));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Netword.doPost("/prod-api/api/login", jsonObject.toString(), new OkResult() {
                    @Override
                      public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            finish();
//                            Toast.makeText(yindao.this, "zh="+zh.getString("username",null), Toast.LENGTH_SHORT).show();
//                            Toast.makeText(yindao.this, "mm="+zh.getString("password",null), Toast.LENGTH_SHORT).show();
//                            Toast.makeText(yindao.this, "baseurl"+Netword.baseurl, Toast.LENGTH_SHORT).show();
                            Toast.makeText(yindao.this, "登录成功", Toast.LENGTH_SHORT).show();
                            Netword.token=jsonObject.optString("token");
                            finish();
                            startActivity(new Intent(yindao.this, MainActivity.class));
                        }else{
                            startActivity(new Intent(yindao.this, Login.class));
                        }
                    }
                });

            }
        });
    }

    private void initdata() {
        data.add(R.drawable.yind_1);
        data.add(R.drawable.yind_2);
        data.add(R.drawable.yind_3);
        data.add(R.drawable.yind_4);
        data.add(R.drawable.yind_5);
        ArrayList<ImageView> imageViews = new ArrayList<>();
        for (int img : data) {
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(img);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
        }
        viewpager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return data.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView((View) object);
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                ImageView imageView = imageViews.get(position);
                container.addView(imageView);
                return imageView;
            }
        });

        //设置小圆点
        for (int i = 0; i < imageViews.size(); i++) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(20, 20);
            View view = new View(this);
            view.setBackgroundResource(R.drawable.picture);
            view.setEnabled(false);
            if (i != 0) {
                layoutParams.leftMargin = 10;
            } else view.setEnabled(false);
            {
                picture.addView(view, layoutParams);
            }
        }
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == imageViews.size() - 1) {
                    ip.setVisibility(View.VISIBLE);
                    goHome.setVisibility(View.VISIBLE);
                }else{
                    ip.setVisibility(View.GONE);
                    goHome.setVisibility(View.GONE);
                }
                picture.getChildAt(curr).setEnabled(false);
                picture.getChildAt(position).setEnabled(true);
                curr=position;
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void initView() {
        viewpager = findViewById(R.id.viewpager);
        picture = findViewById(R.id.picture);
        ip = findViewById(R.id.ip);
        goHome = findViewById(R.id.go_home);
    }
}