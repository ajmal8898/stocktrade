package com.example.stocktrading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class StockRecycleAdapter extends RecyclerView.Adapter<StockRecycleAdapter.AddStock> {
    View view;
    Context context;
    ArrayList<StockDatas> stocklist = new ArrayList<StockDatas>();
    public StockRecycleAdapter(ArrayList<StockDatas> list, Context context) {
        stocklist = list;
        this.context = context;
    }

    @Override
    public AddStock onCreateViewHolder(ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stocks_recyclerview, parent, false);
        AddStock stock = new AddStock(view);
        return stock;
    }

    @Override
    public void onBindViewHolder(AddStock holder, int position) {
        holder.comp_name.setText(stocklist.get(position).company_name);

        String ltpc="₹"+(stocklist.get(position).ltp);
        holder.ltp.setText(ltpc);

        String changeper="("+(stocklist.get(position).change_percent)+"%)";
        holder.change_per.setText(changeper);

        String chng="+"+ stocklist.get(position).change;
        holder.change.setText(chng);

        holder.symbol_name.setText(stocklist.get(position).symbol);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(v.getContext());
                view = LayoutInflater.from(v.getContext()).inflate(R.layout.activity_bootomsheet, null);
                EditText stockQuantity = view.findViewById(R.id.quantity_bottomsheet);
                TextView ltp = view.findViewById(R.id.ltp_botomsheet);
                TextView cmpny_name = view.findViewById(R.id.cmp_name);
                ltp.setText(holder.ltp.getText().toString());
                cmpny_name.setText(holder.comp_name.getText().toString());
                Button buy = view.findViewById(R.id.buy);
                Button sell = view.findViewById(R.id.sell);
                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();

                buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        StockDatas model = stocklist.get(position);
                        DatabaseStore database = new DatabaseStore(v.getContext());
                        bottomSheetDialog.show();

                        if (stockQuantity.getText().toString().isEmpty()) {
                            Toast.makeText(v.getContext(), v.getResources().getString(R.string.blank), Toast.LENGTH_LONG).show();

                        } else {
                            String name = model.getCompany_name();
                            String change = model.getChange();
                            String change_percent = model.getChange_percent();
                            String ltp = model.getLtp();
                            String symbol = model.getSymbol();
                            String quantities = stockQuantity.getText().toString();
                            String status=v.getResources().getString(R.string.buy);
                            database.insertIntoTheDataBase(name, change, change_percent, ltp, symbol, quantities,status);
                            bottomSheetDialog.dismiss();
                            Toast.makeText(view.getContext(), v.getResources().getString(R.string.success), Toast.LENGTH_LONG);

                        }

                    }
                });


                sell.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DatabaseStore database = new DatabaseStore(v.getContext());
                        StockDatas stock = stocklist.get(position);
                        if (stockQuantity.getText().toString().isEmpty()) {
                            Toast.makeText(v.getContext(),v.getResources().getString(R.string.blank), Toast.LENGTH_LONG).show();

                        } else {
                            String name = stock.getCompany_name();
                            String change = stock.getChange();
                            String change_percent = stock.getChange_percent();
                            String ltp = stock.getLtp();
                            String symbol = stock.getSymbol();
                            String quantities = stockQuantity.getText().toString();
                            String status=v.getResources().getString(R.string.sell);
                            database.insertIntoTheDataBase(name, change, change_percent, ltp, symbol, quantities,status);
                            bottomSheetDialog.dismiss();

                        }

                    }
                });


            }
        });

    }

    @Override
    public int getItemCount() {
        return stocklist.size();
    }

    public static class AddStock extends RecyclerView.ViewHolder {
        TextView comp_name;
        TextView ltp;
        TextView change_per;
        TextView change;
        CardView cardView;
        TextView symbol_name;


        public AddStock(View itemView) {
            super(itemView);

            comp_name = itemView.findViewById(R.id.company_name);
            ltp = itemView.findViewById(R.id.ltp);
            change_per = itemView.findViewById(R.id.change_per);
            change = itemView.findViewById(R.id.change);
            cardView = itemView.findViewById(R.id.click_cardview);
            symbol_name = itemView.findViewById(R.id.symbol_name);
        }
    }

}



