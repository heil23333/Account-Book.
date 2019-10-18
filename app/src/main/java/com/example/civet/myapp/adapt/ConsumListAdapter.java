package com.example.civet.myapp.adapt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.civet.myapp.R;
import com.example.civet.myapp.bean.Consumption;

import java.util.List;

public class ConsumListAdapter extends RecyclerView.Adapter<ConsumListAdapter.ConsumptionViewHolder> {

    Context context;
    List<Consumption> consumptionList;

    public ConsumListAdapter(Context context, List<Consumption> consumptionList) {
        this.context = context;
        this.consumptionList = consumptionList;
    }

    @NonNull
    @Override
    public ConsumptionViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new ConsumptionViewHolder(inflater.inflate(R.layout.consumption_item_layout, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ConsumptionViewHolder consumptionViewHolder, int i) {
        consumptionViewHolder.classification.setText(consumptionList.get(i).getClassification());
        consumptionViewHolder.tag.setText(consumptionList.get(i).getTag());
        consumptionViewHolder.money.setText(String.valueOf(consumptionList.get(i).getMoney()));
        consumptionViewHolder.time.setText(String.valueOf(consumptionList.get(i).getTime()));
    }

    @Override
    public int getItemCount() {
        return consumptionList == null ? 0 : consumptionList.size();
    }

    class ConsumptionViewHolder extends RecyclerView.ViewHolder {

        TextView tag, classification, money, time;

        public ConsumptionViewHolder(@NonNull View itemView) {
            super(itemView);
            tag = itemView.findViewById(R.id.tag);
            classification = itemView.findViewById(R.id.classification);
            money = itemView.findViewById(R.id.money);
            time = itemView.findViewById(R.id.time);
        }
    }
}
