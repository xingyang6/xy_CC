package com.example.a12_13.Adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_13.R;
import com.example.a12_13.bean.new_bean;
import com.example.a12_13.bean.news_pl_bean;
import com.example.a12_13.home.news_init;
import com.example.a12_13.util.Netword;

import java.util.List;

public class news_Adapter extends RecyclerView.Adapter<news_Adapter.LearViewHolder> {
    private Context context;
    private int type;
    private List<new_bean> news;
    private List<new_bean> newss;
    private List<news_pl_bean> pl;
    public news_Adapter(Context context,List<new_bean> news,List<new_bean> newss,List<news_pl_bean> pl,int a){
        this.context=context;
        this.type=a;
        this.news=news;
        this.newss=newss;
        this.pl=pl;
    }

    @NonNull
    @Override
    public LearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LearViewHolder learViewHolder=null;
        switch (type){
            case 0:
                learViewHolder=new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.news1_item,parent,false));
                break;
            case 1:
                learViewHolder=new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.news1_item,parent,false));
                break;
            case 2:
                learViewHolder=new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.news_pl_item,parent,false));
                break;
        }
        return learViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearViewHolder holder, int position) {
        switch (type){
            case 0:
                Netword.doImage(context,news.get(position).getCover(),holder.newsImg1);
                holder.newsTitle1.setText(news.get(position).getTitle());
                holder.newsCount1.setText(Html.fromHtml(news.get(position).getTitle()));
                holder.newsLike.setText(news.get(position).getLikeNum()+"");
                holder.newsKan.setText(news.get(position).getReadNum()+"");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        news_init.news_id=news.get(position).getId();
                        context.startActivity(new Intent(context,news_init.class));
                    }
                });
                break;
            case 1:
                Netword.doImage(context,newss.get(position).getCover(),holder.newsImg1);
                holder.newsTitle1.setText(newss.get(position).getTitle());
                holder.newsCount1.setText(Html.fromHtml(newss.get(position).getTitle()));
                holder.newsLike.setText(newss.get(position).getLikeNum()+"");
                holder.newsKan.setText(newss.get(position).getReadNum()+"");
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        news_init.news_id=newss.get(position).getId();
                        context.startActivity(new Intent(context,news_init.class));
                    }
                });
                break;
            case 2:
                holder.plName.setText(pl.get(position).getUserName());
                holder.plData.setText(pl.get(position).getCommentDate());
                holder.plCount.setText(pl.get(position).getContent());
                break;
        }


    }

    @Override
    public int getItemCount() {
        int a=0;
        switch (type){
            case 0:
                a=news.size();
                break;
            case 1:
                a=3;
                break;
            case 2:
                a=pl.size();
                break;
        }
        return a;
    }

    public class LearViewHolder extends RecyclerView.ViewHolder {
        private ImageView newsImg1;
        private TextView newsTitle1;
        private TextView newsCount1;
        private TextView newsLike;
        private TextView newsKan;

        private ImageView plImg;
        private TextView plName;
        private TextView plData;
        private TextView plCount;
        public LearViewHolder(@NonNull View itemView) {
            super(itemView);
            switch (type){
                case 0:
                newsImg1 = itemView.findViewById(R.id.news_img1);
                newsTitle1 = itemView.findViewById(R.id.news_title1);
                newsCount1 = itemView.findViewById(R.id.news_count1);
                newsLike = itemView.findViewById(R.id.news_like);
                newsKan = itemView.findViewById(R.id.news_kan);
                break;
                case 1:
                    newsImg1 = itemView.findViewById(R.id.news_img1);
                    newsTitle1 = itemView.findViewById(R.id.news_title1);
                    newsCount1 = itemView.findViewById(R.id.news_count1);
                    newsLike = itemView.findViewById(R.id.news_like);
                    newsKan = itemView.findViewById(R.id.news_kan);
                    break;
                case 2:
                    plImg = itemView.findViewById(R.id.pl_img);
                    plName = itemView.findViewById(R.id.pl_name);
                    plData = itemView.findViewById(R.id.pl_data);
                    plCount = itemView.findViewById(R.id.pl_count);
                    break;
            }
        }
    }
}
