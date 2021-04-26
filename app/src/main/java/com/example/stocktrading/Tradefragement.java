package com.example.stocktrading;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


public class Tradefragement extends Fragment {

    View view;
    ArrayList<TradeActivityDatas> list = new ArrayList<TradeActivityDatas>();
    Cursor cursor;
    SQLiteOpenHelper database;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.trade_fragment, container, false);
        database = new DatabaseStore(view.getContext());

        list.clear();

        getvalues();

        callRecycleView(view,getContext(),list);

        return view;
    }




    public void getvalues() {
        cursor =  database.getReadableDatabase().rawQuery(" SELECT * FROM " + DatabaseStore.table_name, null);
        if (cursor != null) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                TradeActivityDatas listinformation = new TradeActivityDatas(String.valueOf(cursor.getString(4)), String.valueOf(cursor.getString(3)), String.valueOf(cursor.getString(2)), String.valueOf(cursor.getString(1)),String.valueOf(cursor.getString(5)),String.valueOf(cursor.getString(6)),String.valueOf(cursor.getString(7)));
                listinformation.setCompany_name(cursor.getString(1));
                listinformation.setChange_percent(cursor.getString(3));
                listinformation.setPrice(cursor.getString(2));
                listinformation.setLtp(cursor.getString(4));
                listinformation.setSymbol(cursor.getString(5));
                listinformation.setQuantity(cursor.getString(6));
                 listinformation.setStatus(cursor.getString(7));
                list.add(listinformation);
                cursor.moveToNext();
            }
        }
    }
    public void callRecycleView(View view, Context context, ArrayList<TradeActivityDatas> list)
    {
        RecyclerView recyclerView = view.findViewById(R.id.recycleSecond);
        TradeActivityRecycleAdapter recycleItemSecond = new TradeActivityRecycleAdapter(context,list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recycleItemSecond);


    }


}