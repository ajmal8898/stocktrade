package com.example.stocktrading;

public class TradeActivityDatas {
    String ltp;
    String change_percent;
    String change;
    String company_name;
    String quantity;
    String symbol;
    String Status;


    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public TradeActivityDatas(String ltp, String change_price, String price, String company_name, String symbols, String quantitys, String state) {

        this.ltp = ltp;
        this.change_percent = change_price;
        this.change = price;
        this.company_name = company_name;
        this.symbol = symbols;
        this.quantity = quantitys;
        this.Status = state;

    }

    public void setStatus(String state) {
        this.Status = state;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }


    public void setLtp(String ltp) {
        this.ltp = ltp;
    }


    public void setChange_percent(String change_percent) {
        this.change_percent = change_percent;
    }


    public void setPrice(String price) {
        this.change = price;
    }


    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }


}
