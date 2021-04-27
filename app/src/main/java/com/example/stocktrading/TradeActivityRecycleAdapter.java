package com.example.stocktrading;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TradeActivityRecycleAdapter extends RecyclerView.Adapter<TradeActivityRecycleAdapter.AddStock> {

    View view;
    Context mcontext;
    ArrayList<TradeActivityDatas> listinformations;

    TradeActivityRecycleAdapter(Context context, ArrayList<TradeActivityDatas> list) {
        mcontext = context;
        listinformations = list;
    }

    @Override
    public AddStock onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trade_activity_recycle, parent, false);
        AddStock views = new AddStock(view);
        return views;
    }

    @Override
    public void onBindViewHolder(AddStock holder, int position) {
        String ltpc="₹"+(listinformations.get(position).ltp);
        holder.ltp.setText(ltpc);

        holder.name.setText(String.valueOf(listinformations.get(position).company_name));

        String chprice = "("+listinformations.get(position).change_percent+"%)";
        holder.change_percent.setText(chprice);

        holder.symbol.setText(listinformations.get(position).symbol);


        String qu = "Q : "+listinformations.get(position).quantity;
        holder.quantity.setText(qu);

        String ch = "+"+listinformations.get(position).change;
        holder.change.setText(ch);
        String status = listinformations.get(position).Status;

        if(status.equalsIgnoreCase("BUY")){
            holder.status.setText( listinformations.get(position).Status);
            holder.status.setTextColor(Color.parseColor("#178E1B"));
        }else{
            holder.status.setText( listinformations.get(position).Status);
            holder.status.setTextColor(Color.parseColor("#FF0000"));
        }
     //   holder.status.setText(listinformations.get(position).Status);

    }

    @Override
    public int getItemCount() {
        return listinformations.size();
    }

    public static class AddStock extends RecyclerView.ViewHolder {
        TextView name;
        TextView ltp;
        TextView change_percent;
        TextView change;
        CardView buy;
        TextView symbol;
        TextView quantity;
        TextView status;


        public AddStock(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.company_name);
            ltp = itemView.findViewById(R.id.ltp);
            change_percent = itemView.findViewById(R.id.change_per);
            change = itemView.findViewById(R.id.change);
            buy = itemView.findViewById(R.id.click_cardview);
            symbol = itemView.findViewById(R.id.symbol_name);
            quantity  = itemView.findViewById(R.id.quantity);
            status  = itemView.findViewById(R.id.staus);


        }
    }

}

