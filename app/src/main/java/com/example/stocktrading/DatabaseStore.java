package com.example.stocktrading;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseStore extends SQLiteOpenHelper {

    Cursor cursor;
    SQLiteOpenHelper database;
    public static final String database_name = "Stock_stores";
    public static final String table_name = "Data_store";
    public static final String coloumn1 = "Id";
    public static final String column2 = "Name";
    public static final String column3 = "Ch_price";
    public static final String column4 = "Change_price";
    public static final String column5 = "Ltp";
    public static final String column6 = "Symbol_name";
    public static final String column7 = "Quantity";
    public static final String column8 = "Status";






    private static final String CREATE_TABLE = "create table " + table_name + "(" + coloumn1
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + column2 + " TEXT NOT NULL, " + column3 + " TEXT NOT NULL ," + column4 + "  TEXT NOT NULL ," + column5 + " TEXT NOT NULL ," + column6 + " TEXT NOT NULL ," + column7 + " TEXT NOT NULL," + column8 + " TEXT NOT NULL );";


    public DatabaseStore(Context context) {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        onCreate(db);
    }

    public void insertIntoTheDataBase(String name, String ch_price, String change_price, String ltp,String symbol,String Quantity,String status) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseStore.column2, name);
        contentValue.put(DatabaseStore.column3, ch_price);
        contentValue.put(DatabaseStore.column4, change_price);
        contentValue.put(DatabaseStore.column5, ltp);
        contentValue.put(DatabaseStore.column6, symbol);
        contentValue.put(DatabaseStore.column7, Quantity);
        contentValue.put(DatabaseStore.column8, status);

        db.insert(table_name, null, contentValue);
        db.close();
    }


}
