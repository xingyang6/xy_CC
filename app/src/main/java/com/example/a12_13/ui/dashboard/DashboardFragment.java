package com.example.a12_13.ui.dashboard;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_13.Adapter.all_recy_Adapter;
import com.example.a12_13.R;
import com.example.a12_13.bean.all_bean;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.example.a12_13.util.kg;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private TextView textView5;
    private TextView textView6;
    private TextView textView7;
    private RecyclerView allRecy;
    private SearchView allSear;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initdata();
    }

    private void initdata() {
        List<all_bean> all = new ArrayList<>();
        allRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        allRecy.setAdapter(new all_recy_Adapter(getActivity(), all, 0));
        textView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView5.setBackgroundColor(Color.rgb(255, 255, 255));
                textView6.setBackgroundColor(Color.rgb(235, 233, 233));
                textView7.setBackgroundColor(Color.rgb(235, 233, 233));
            }
        });
        textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView6.setBackgroundColor(Color.rgb(255, 255, 255));
                textView5.setBackgroundColor(Color.rgb(235, 233, 233));
                textView7.setBackgroundColor(Color.rgb(235, 233, 233));
            }
        });
        textView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView7.setBackgroundColor(Color.rgb(255, 255, 255));
                textView5.setBackgroundColor(Color.rgb(235, 233, 233));
                textView6.setBackgroundColor(Color.rgb(235, 233, 233));
            }
        });

        //搜索
        allSear.setSubmitButtonEnabled(true);
        allSear.setImeOptions(EditorInfo.IME_ACTION_PREVIOUS);
        allSear.setIconified(true);
        allSear.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (kg.isFastDoubleclick()){
                    return false;
                }else{
                    load(query);
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

    }

    private void load(String query) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity())
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        View view=LayoutInflater.from(getActivity()).inflate(R.layout.pop,null);
        builder.setView(view);
        Netword.doGet("/prod-api/api/service/list?serviceName=" + query, new OkResult() {
            @Override
            public void succes(JSONObject jsonObject) {
                if (jsonObject.optInt("code")==200){
                    TextView textView1=view.findViewById(R.id.tk_text);
                    RecyclerView recyclerView=view.findViewById(R.id.tk_recy);
                    List<all_bean> mall=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<all_bean>>(){
                    }.getType());
                    if (mall.size()==0){
                        textView1.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }else{
                        textView1.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
                        recyclerView.setAdapter(new all_recy_Adapter(getActivity(),mall,1));
                    }

                }else{
                    Toast.makeText(getActivity(), jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.show();
    }

    private void initView(View view) {
        textView5 = view.findViewById(R.id.textView5);
        textView6 = view.findViewById(R.id.textView6);
        textView7 = view.findViewById(R.id.textView7);
        allRecy = view.findViewById(R.id.all_recy);
        allSear = view.findViewById(R.id.all_sear);
    }
}