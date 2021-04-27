package com.example.stocktrading;

public class TradeActivityDatas {
    String ltp;
    String change_percent;
    String change;
    String company_name;
    String quantity;
    String symbol;
    String Status;


    public TradeActivityDatas(String ltp, String change_price, String price, String company_name, String symbols, String quantitys, String state) {

        this.ltp = ltp;
        this.change_percent = change_price;
        this.change = price;
        this.company_name = company_name;
        this.symbol = symbols;
        this.quantity = quantitys;
        this.Status = state;

    }




}
