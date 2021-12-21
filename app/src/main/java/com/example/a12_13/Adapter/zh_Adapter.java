package com.example.a12_13.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.text.HtmlCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_13.R;
import com.example.a12_13.live.dian_init;
import com.example.a12_13.live.live_news_init;
import com.example.a12_13.live.shui;
import com.example.a12_13.util.Netword;
import com.example.a12_13.zhihui.A_data;
import com.example.a12_13.zhihui.zh_init;

public class zh_Adapter extends RecyclerView.Adapter<zh_Adapter.learViewHolder> {
    private Context context;
    private int type;

    public zh_Adapter(Context context,int a){
        this.context=context;
        this.type=a;
    }

    @NonNull
    @Override
    public learViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        learViewHolder learViewHolder=null;
        switch (type){
            case 0:
                learViewHolder=new learViewHolder(LayoutInflater.from(context).inflate(R.layout.zh_news_item,parent,false));
                break;
            case 1:
                learViewHolder=new learViewHolder(LayoutInflater.from(context).inflate(R.layout.zh_news_item,parent,false));
                break;
        }
        return learViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull learViewHolder holder, int position) {
        switch (type){
            case 0:
                holder.zhImg.setImageResource(A_data.zh_img.get(position));
                holder.zhTitle.setText(A_data.zh_title.get(position));
                holder.zhCount.setText(A_data.zh_count.get(position));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        zh_init.zh_type=position;
                        context.startActivity(new Intent(context,zh_init.class));
                    }
                });
                break;
            case 1:
                holder.zhImg.setImageResource(A_data.zh_img.get(position+2));
                holder.zhTitle.setText(A_data.zh_title.get(position+2));
                holder.zhCount.setText(A_data.zh_count.get(position+2));
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        zh_init.zh_type=position+2;
                        context.startActivity(new Intent(context,zh_init.class));
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
                a=2;
                break;
            case 1:
                a=2;
                break;
        }
        return a;
    }

    public class learViewHolder extends RecyclerView.ViewHolder {
        private ImageView zhImg;
        private TextView zhTitle;
        private TextView zhCount;
        public learViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (type){
                case 0:
                    zhImg = itemView.findViewById(R.id.zh_img);
                    zhTitle = itemView.findViewById(R.id.zh_title);
                    zhCount = itemView.findViewById(R.id.zh_count);
                    break;
                case 1:
                    zhImg = itemView.findViewById(R.id.zh_img);
                    zhTitle = itemView.findViewById(R.id.zh_title);
                    zhCount = itemView.findViewById(R.id.zh_count);
                    break;
            }
        }
    }
}
