package com.example.a12_13.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_13.R;
import com.example.a12_13.bean.all_bean;
import com.example.a12_13.util.Netword;
import com.example.a12_13.util.OkResult;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lljjcoder.style.citylist.Toast.ToastUtils;

import org.json.JSONObject;

import java.util.List;

public class all_recy_Adapter extends RecyclerView.Adapter<all_recy_Adapter.LearVierHolder> {
    private String[] data={"车主服务","生活服务","便民服务"};
    private Context context;
    private int type;
    private List<all_bean> all;
    public all_recy_Adapter(Context context,List<all_bean> all,int a){
        this.context=context;
        this.type=a;
        this.all=all;
    }
    @NonNull
    @Override
    public LearVierHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LearVierHolder learVierHolder=null;
        switch (type){
            case 0:
                learVierHolder=new LearVierHolder(LayoutInflater.from(context).inflate(R.layout.all_item,parent,false));
                break;
            case 1:
                learVierHolder=new LearVierHolder(LayoutInflater.from(context).inflate(R.layout.all_nei_item,parent,false));
                break;
        }
        return learVierHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearVierHolder holder, int position) {
        switch (type){
            case 0:
                holder.textView8.setText(data[position]);
                Netword.doGet("/prod-api/api/service/list?serviceType=" + data[position], new OkResult() {
                    @Override
                    public void succes(JSONObject jsonObject) {
                        if (jsonObject.optInt("code")==200){
                            List<all_bean> all=new Gson().fromJson(jsonObject.optString("rows"),new TypeToken<List<all_bean>>(){
                            }.getType());
                            holder.neiRecy.setLayoutManager(new GridLayoutManager(context,3));
                            holder.neiRecy.setAdapter(new all_recy_Adapter(context,all,1));

                        }else{
                            Toast.makeText(context, jsonObject.optString("msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case 1:
                holder.allNeiText.setText(all.get(position).getServiceName());
                Netword.doImage(context,all.get(position).getImgUrl(),holder.allNeiImg);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "点击了"+all.get(position).getServiceName(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
        }

    }

    @Override
    public int getItemCount() {
        int a=0;
        switch (type){
            case 0:
                a=3;
                break;
            case 1:
                a=all.size();
                break;
        }
        return a;
    }

    public class LearVierHolder extends RecyclerView.ViewHolder {
        private TextView textView8;
        private RecyclerView neiRecy;

        private ImageView allNeiImg;
        private TextView allNeiText;
        public LearVierHolder(@NonNull View itemView) {
            super(itemView);
            switch (type){
                case 0:
                    textView8 = itemView.findViewById(R.id.textView8);
                    neiRecy = itemView.findViewById(R.id.nei_recy);
                    break;
                case 1:
                    allNeiImg = itemView.findViewById(R.id.all_nei_img);
                    allNeiText = itemView.findViewById(R.id.all_nei_text);
                    break;
            }
        }
    }
}
