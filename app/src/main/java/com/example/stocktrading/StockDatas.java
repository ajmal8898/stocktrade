package com.example.stocktrading;

public class StockDatas {
    String ltp;
    String change_price;
    String change;
    String company_name;
    String symbol;
    public StockDatas(String ltp, String changePer, String change, String details, String symbol) {

        this.ltp = ltp;
        this.change_price = changePer;
        this.change = change;
        this.company_name = details;
        this.symbol = symbol;

    }
    public String getSymbol() {
        return symbol;
    }

    public String getLtp() {
        return ltp;
    }

    public String getChange_percent() {
        return change_price;
    }

    public String getChange() {
        return change;
    }

    public String getCompany_name() {
        return company_name;
    }


}

