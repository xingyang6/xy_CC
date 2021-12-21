package com.example.a12_13.Adapter;

import android.content.Context;
import android.content.Intent;
import android.renderscript.Long3;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_13.R;
import com.example.a12_13.bean.jl_bean;
import com.example.a12_13.bean.zw_item;
import com.example.a12_13.bean.zw_list_bean;
import com.example.a12_13.findwork.work_home;
import com.example.a12_13.findwork.work_init_a;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class work_Adapter extends RecyclerView.Adapter<work_Adapter.LearViewHolder> {
    private Context context;
    private int type;
    private List<zw_item> zw;
    private List<zw_list_bean> zwlist;
    private List<jl_bean> jl;
    public work_Adapter(Context context,List<zw_item> zw,List<zw_list_bean> zwlist,List<jl_bean> jl,int a){
        this.context=context;
        this.type=a;
        this.zw=zw;
        this.zwlist=zwlist;
        this.jl=jl;
    }

    @NonNull
    @Override
    public LearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LearViewHolder learViewHolder=null;
        switch (type){
            case 0:
                learViewHolder=new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.work_zw_item,parent,false));
                break;
            case 1:
                learViewHolder=new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.zwlist_item,parent,false));
                break;
            case 2:
                learViewHolder=new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.jl_item,parent,false));
                break;
        }
        return learViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearViewHolder holder, int position) {
                switch (type){
                    case 0:
                        holder.textView13.setText(zw.get(position).getProfessionName());
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Netword.doGet("/prod-api/api/job/post/list?name="+zw.get(position).getProfessionName(), new OkResult() {
                                    @Override
                                    public void succes(JSONObject jsonObject) {
                                        if (jsonObject.optInt("code")==200){
                                            List<zw_list_bean> zwlist=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<zw_list_bean>>(){
                                            }.getType());
                                            if (zwlist.size()==0){
                                                work_home.text15.setVisibility(View.VISIBLE);
                                                work_home.zwListRecy.setVisibility(View.GONE);
                                            }else {
                                                work_home.text15.setVisibility(View.GONE);
                                                work_home.zwListRecy.setVisibility(View.VISIBLE);
                                                List<zw_item> zw=new ArrayList<>();
                                                List<jl_bean> jl=new ArrayList<>();
                                                work_home.zwListRecy.setLayoutManager(new LinearLayoutManager(context));
                                                work_home.zwListRecy.setAdapter(new work_Adapter(context,zw,zwlist,jl,1));
                                            }
                                        }else{
                                            Toast.makeText(context, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        });
                        break;
                    case 1:
                        holder.zwListName.setText(zwlist.get(position).getName());
                        holder.zwListObligation.setText(zwlist.get(position).getObligation());
                        holder.zwListAdress.setText(zwlist.get(position).getAddress());
                        holder.zwListMoney.setText("￥"+zwlist.get(position).getSalary());
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                work_init_a.int_id=zwlist.get(position).getId();
                                work_init_a.init_id=zwlist.get(position).getCompanyId();
                                context.startActivity(new Intent(context,work_init_a.class));
                            }
                        });
                        break;
                    case 2:
                        holder.jlCompanyName.setText(jl.get(position).getCompanyName());
                        holder.jlPostName.setText(jl.get(position).getPostName());
                        holder.jlMoney.setText(jl.get(position).getMoney()+"元");
                        holder.jlTime.setText(jl.get(position).getSatrTime());
                        break;

                }
    }

    @Override
    public int getItemCount() {
        int a=0;
        switch (type){
            case 0:
                a=zw.size();
                break;
            case 1:
                a=zwlist.size();
                break;
            case 2:
                a=jl.size();
                break;
        }
        return a;
    }

    public class LearViewHolder extends RecyclerView.ViewHolder {
        private TextView textView13;

        private TextView zwListName;
        private TextView zwListObligation;
        private TextView zwListAdress;
        private TextView zwListMoney;

        private TextView jlPostName;
        private TextView jlCompanyName;
        private TextView jlMoney;
        private TextView jlTime;
        public LearViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (type){
                case 0:
                    textView13 = itemView.findViewById(R.id.textView13);
                    break;
                case 1:
                    zwListName = itemView.findViewById(R.id.zw_list_name);
                    zwListObligation = itemView.findViewById(R.id.zw_list_obligation);
                    zwListAdress = itemView.findViewById(R.id.zw_list_adress);
                    zwListMoney = itemView.findViewById(R.id.zw_list_money);
                    break;
                case 2:
                    jlPostName = itemView.findViewById(R.id.jl_postName);
                    jlCompanyName = itemView.findViewById(R.id.jl_companyName);
                    jlMoney = itemView.findViewById(R.id.jl_money);
                    jlTime = itemView.findViewById(R.id.jl_time);
                    break;
            }
        }
    }
}
