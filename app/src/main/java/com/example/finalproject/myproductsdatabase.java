package com.example.finalproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class myproductsdatabase extends SQLiteAssetHelper {

    public static final String DB_NAME ="myproducts.db";
    public static final int DB_VERSION = 1;

    public static final String PRODUCTS_TB_NAME="products";
    public static final String PRODUCTS_ID ="id";
    public static final String PRODUCTS_CATEGORY="category";
    public static final String PRODUCTS_TRADE_MARK="trademark";
    public static final String PRODUCTS_SELLING_PRICE="sellingprice";
    public static final String PRODUCTS_PURCHASING_PRICE="purchaseprice";
    public static final String PRODUCTS_QUANTITY_PER_UNIT= "quantityperunit";
    public static final String PRODUCTS_AVAILABLE_QUANTITY="availablequantity";



    public myproductsdatabase(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
}
