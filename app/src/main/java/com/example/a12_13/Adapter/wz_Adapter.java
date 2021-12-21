package com.example.a12_13.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a12_13.R;
import com.example.a12_13.bean.wz_init_bean;
import com.example.a12_13.wz.wz_init;
import com.example.a12_13.wz.wz_init_1;

import java.util.List;

public class wz_Adapter extends RecyclerView.Adapter<wz_Adapter.LearViewHolder> {
    private Context context;
    private List<wz_init_bean> init;
    public wz_Adapter(Context context,List<wz_init_bean> init){
        this.context=context;
        this.init=init;
    }


    @NonNull
    @Override
    public LearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LearViewHolder(LayoutInflater.from(context).inflate(R.layout.wz_init_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull LearViewHolder holder, int position) {
                holder.wzBadTime.setText("违法时间："+init.get(position).getBadTime());
                holder.wzIllegalSites.setText("违法地点："+init.get(position).getIllegalSites());
                holder.wzDeductMarks.setText("记分："+init.get(position).getDeductMarks());
                holder.wzMoney.setText("罚款金额："+init.get(position).getMoney());
                holder.wzDisposeState.setText("处理状态："+init.get(position).getDisposeState());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        wz_init_1.cai_id=init.get(position).getId();
                        context.startActivity(new Intent(context, wz_init_1.class));
                    }
                });
    }

    @Override
    public int getItemCount() {
        return wz_init.a;
    }

    public class LearViewHolder extends RecyclerView.ViewHolder {
        private TextView wzBadTime;
        private TextView wzIllegalSites;
        private TextView wzDeductMarks;
        private TextView wzMoney;
        private TextView wzDisposeState;
        public LearViewHolder(@NonNull View itemView) {
            super(itemView);
            wzBadTime = itemView.findViewById(R.id.wz_badTime);
            wzIllegalSites = itemView.findViewById(R.id.wz_illegalSites);
            wzDeductMarks = itemView.findViewById(R.id.wz_deductMarks);
            wzMoney = itemView.findViewById(R.id.wz_money);
            wzDisposeState = itemView.findViewById(R.id.wz_disposeState);
        }
    }
}
