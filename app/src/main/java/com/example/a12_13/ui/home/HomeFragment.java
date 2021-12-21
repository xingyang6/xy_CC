package com.example.a12_13.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_13.Adapter.home_Adapter;
import com.example.a12_13.R;
import com.example.a12_13.bean.home_banenr_bean;
import com.example.a12_13.bean.home_tab;
import com.example.a12_13.bean.honme_tuijain_bean;
import com.example.a12_13.bean.new_bean;
import com.example.a12_13.home.home_sear;
import com.example.a12_13.home.news_init;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.example.a12_13.util.kg;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {


    private SearchView homeSear;
    private Banner homeBanenr;
    private RecyclerView homeTuijainRecy;
    private RecyclerView homeRemenRecy;
    private TabLayout homeTab;
    private RecyclerView homeNewsRecy;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initdata();
    }

    private void initdata() {
        Netword.doGet("/prod-api/api/rotation/list?pageNum=1&pageSize=8&type=2", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<home_banenr_bean> banenr=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<home_banenr_bean>>(){
                    }.getType());
                    homeBanenr.setImages(banenr).setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object o, ImageView imageView) {
                            home_banenr_bean ban=(home_banenr_bean) o;
                            Netword.doImage(context,ban.getAdvImg(),imageView);
                        }
                    }).start();
                    homeBanenr.setOnBannerListener(new OnBannerListener() {
                        @Override
                        public void OnBannerClick(int i) {
                            news_init.news_id=banenr.get(i).getTargetId();
                            startActivity(new Intent(getActivity(),news_init.class));
                        }
                    }).start();
                }else{
                    Toast.makeText(getActivity(), "msg", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //推荐服务
        Comparator<honme_tuijain_bean> comparator=new Comparator<honme_tuijain_bean>() {
            @Override
            public int compare(honme_tuijain_bean o1, honme_tuijain_bean o2) {
               if (o1.getId()!=o2.getId()){
                   return o2.getId()-o1.getId();
               }else{
                   return o1.getId()-o2.getId();
               }
            }
        };
        Netword.doGet("/prod-api/api/service/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<honme_tuijain_bean> tuijian=new  Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<honme_tuijain_bean>>(){
                    }.getType());
                    Collections.sort(tuijian,comparator);
                    List<new_bean> news=new ArrayList<>();
                    homeTuijainRecy.setLayoutManager(new GridLayoutManager(getActivity(),5));
                    homeTuijainRecy.setAdapter(new home_Adapter(getActivity(),tuijian,news,0));
                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        //热门
            Netword.doGet("/prod-api/press/press/list?hot=Y", new OkResult() {
                @Override
                public void succes(JSONObject jsonObject) {
                    if (jsonObject.optInt("code")==200){
                        List<new_bean> news=new  Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<new_bean>>(){
                        }.getType());
                        List<honme_tuijain_bean> mtuijian=new ArrayList<>();
                        homeRemenRecy.setLayoutManager(new GridLayoutManager(getActivity(),2));
                        homeRemenRecy.setAdapter(new home_Adapter(getActivity(),mtuijian,news,1));
                    }else{
                        Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                    }
                }
            });

            //tab
        Netword.doGet("/prod-api/press/category/list", new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    List<home_tab> news=new  Gson().fromJson(jsonObject.optString("data"),new TypeToken<List<home_tab>>(){
                    }.getType());
                    for (int i=0;i<news.size();i++){
                        homeTab.addTab(homeTab.newTab().setText(news.get(i).getName()).setTag(news.get(i).getId()));
                    }
                    LinearLayout linearLayout = (LinearLayout) homeTab.getChildAt(0);
                    linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
                    linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),
                            R.drawable.tab_style));
                    linearLayout.setDividerPadding(40);
//                    LinearLayout linearLayout= (LinearLayout) homeTab.getChildAt(0);
//                    linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
//                    linearLayout.setDividerDrawable(ContextCompat.getDrawable(getActivity(),R.drawable.tab_style));
//                    linearLayout.setDividerPadding(8);

                    homeTab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                        @Override
                        public void onTabSelected(TabLayout.Tab tab) {
                            loada(tab.getTag());
                        }

                        @Override
                        public void onTabUnselected(TabLayout.Tab tab) {

                        }

                        @Override
                        public void onTabReselected(TabLayout.Tab tab) {

                        }
                    });
                    loada(news.get(0).getId());
                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });

        //搜索
        homeSear.setSubmitButtonEnabled(true);
        homeSear.setIconified(true);
        homeSear.setImeOptions(EditorInfo.IME_ACTION_PREVIOUS);
        homeSear.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (kg.isFastDoubleclick()){
                    return false;
                }else{
                    home_sear.string=query;
                    startActivity(new Intent(getActivity(),home_sear.class));
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }

    private void loada(Object id) {
                Netword.doGet("/prod-api/press/press/list?type=" + id, new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            List<new_bean> news=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<new_bean>>(){
                            }.getType());
                            List<honme_tuijain_bean> mtuijian=new ArrayList<>();
                            homeNewsRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
                            homeNewsRecy.setAdapter(new home_Adapter(getActivity(),mtuijian,news,2));
                        }else{
                            Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void initView(View view) {
        homeSear = view.findViewById(R.id.home_sear);
        homeBanenr = view.findViewById(R.id.home_banenr);
        homeTuijainRecy = view.findViewById(R.id.home_tuijain_recy);
        homeRemenRecy = view.findViewById(R.id.home_remen_recy);
        homeTab = view.findViewById(R.id.home_tab);
        homeNewsRecy = view.findViewById(R.id.home_news_recy);
    }
}