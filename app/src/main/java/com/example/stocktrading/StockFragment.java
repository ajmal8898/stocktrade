package com.example.stocktrading;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class StockFragment extends Fragment {

    View view;
    ArrayList<StockDatas> datalist = new ArrayList<StockDatas>();
    StockDatas stockDatas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.stock_fragment, container, false);
        try {
            volleyPost();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    public void volleyPost() throws JSONException {
        String postUrl = "https://laemobapp.angelbroking.com/AngelService/MoversNews/Movers/1.0.0";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JSONObject jsonobject = new JSONObject();
        try {


            JSONObject jsonobject_one = new JSONObject();
            JSONObject jsonobject_TWO = new JSONObject();

            jsonobject_one.put("appID", "f363c1745f5f63433a57e369a01c5752");
            jsonobject_TWO.put("category", "TOPGAINER");
            jsonobject_TWO.put("sessionID", "guest");
            jsonobject_TWO.put("usrID", "guest");
            jsonobject_one.put("data", jsonobject_TWO);
            jsonobject.put("request", jsonobject_one);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, postUrl, jsonobject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject jsonObject = response.getJSONObject("response").getJSONObject("data");
                        JSONArray arrays = jsonObject.getJSONArray("bse");
                        for (int j = 0; j < arrays.length(); j++) {

                            JSONObject datas = arrays.getJSONObject(j);

                            stockDatas = new StockDatas(datas.getString("ltp"), datas.getString("changePer"), datas.getString("change"), datas.getString("details"),datas.getString("symbolName"));

                            datalist.add(stockDatas);
                            addDatatoStockRecycle(view, datalist, getContext());

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                error.printStackTrace();
            }
        });


        requestQueue.add(jsonObjectRequest);

    }


    public void addDatatoStockRecycle(View view, ArrayList<StockDatas> stocklists, Context context) {

        RecyclerView recyclerView = view.findViewById(R.id.stock_recycleView);
        StockRecycleAdapter adapter = new StockRecycleAdapter(stocklists, context);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);


    }

}