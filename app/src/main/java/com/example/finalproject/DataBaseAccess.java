package com.example.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;

public class DataBaseAccess {

    String allcat, test = "";
    int i = 1;
    int l = 0, l1 = 0;
    int q = 0;
    int T = 0;
    int x = 0;
    int k = 0;

    String[] myCat;
    String[] myquantitystr1;
    String[] myquantitystr2;
    double[] myquantity;
    double[] allavailableQ;
    double[] allavailableQ1;
    double[] costsperunit;
    double[] incomeperunit;
    double[] myarr;
    double[] myarr1;


    private SQLiteDatabase database;
    SQLiteOpenHelper openHelper;
    private static DataBaseAccess instance;

    private DataBaseAccess(Context context) {
        this.openHelper = new myproductsdatabase(context);
    }

    public static DataBaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DataBaseAccess(context);
        }
        return instance;
    }

    public void Open() {
        // نفتح خط على قاعدة البيانات
        this.database = this.openHelper.getWritableDatabase();

    }

    public void Close() {
        // نغلق خط على قاعدة البيانات
        if (this.database != null) {
            this.database.close();
        }

    }

    public boolean InsertProduct(Products products) {
        ContentValues values = new ContentValues();
        values.put(myproductsdatabase.PRODUCTS_CATEGORY, products.getCategory());
        values.put(myproductsdatabase.PRODUCTS_PURCHASING_PRICE, products.getPurchaseprice());
        values.put(myproductsdatabase.PRODUCTS_QUANTITY_PER_UNIT, products.getQuantityperunit());
        values.put(myproductsdatabase.PRODUCTS_SELLING_PRICE, products.getSellingprice());
        values.put(myproductsdatabase.PRODUCTS_TRADE_MARK, products.getTrademark());
        values.put(myproductsdatabase.PRODUCTS_AVAILABLE_QUANTITY, products.getAvailablequantity());
//        values.put(myproductsdatabase.PRODUCTS_IMAGE, products.getImge());

        long result = database.insert(myproductsdatabase.PRODUCTS_TB_NAME, null, values);
        return result != -1;
    }

    public boolean UpdateProducts(Products products) {
        ContentValues values = new ContentValues();
        values.put(myproductsdatabase.PRODUCTS_CATEGORY, products.getCategory());
        values.put(myproductsdatabase.PRODUCTS_TRADE_MARK, products.getTrademark());
        values.put(myproductsdatabase.PRODUCTS_SELLING_PRICE, products.getSellingprice());
        values.put(myproductsdatabase.PRODUCTS_PURCHASING_PRICE, products.getPurchaseprice());
        values.put(myproductsdatabase.PRODUCTS_QUANTITY_PER_UNIT, products.getQuantityperunit());
        values.put(myproductsdatabase.PRODUCTS_AVAILABLE_QUANTITY, products.getAvailablequantity());
//        values.put(myproductsdatabase.PRODUCTS_IMAGE, products.getImge());


        String[] args = {String.valueOf(products.getId())};

        int result = database.update(myproductsdatabase.PRODUCTS_TB_NAME, values, "id=?", args);
        return result > 0;

    }

    public long getProductsCount() {
        return DatabaseUtils.queryNumEntries(database, myproductsdatabase.PRODUCTS_TB_NAME);
    }

    public boolean DeleteProduct(Products products) {
        String[] args = {String.valueOf(products.getId())};
//        int [] args1 ={products.getId()};
        int result = database.delete(myproductsdatabase.PRODUCTS_TB_NAME, "id=?", args);
        return result > 0;
    }

    public ArrayList<Products> GetAllProducts() {
        Cursor cursor = null;
        ArrayList<Products> products = new ArrayList<>();
        cursor = database.rawQuery(" SELECT * FROM " + myproductsdatabase.PRODUCTS_TB_NAME, null);

        if (cursor != null && cursor.moveToLast()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(myproductsdatabase.PRODUCTS_ID));
                String category = cursor.getString(cursor.getColumnIndex(myproductsdatabase.PRODUCTS_CATEGORY));
                String trademark = cursor.getString(cursor.getColumnIndex(myproductsdatabase.PRODUCTS_TRADE_MARK));
                double sellingprice = cursor.getDouble(cursor.getColumnIndex(myproductsdatabase.PRODUCTS_SELLING_PRICE));
                double purchaseprice = cursor.getDouble(cursor.getColumnIndex(myproductsdatabase.PRODUCTS_PURCHASING_PRICE));
                String quantityperunit = cursor.getString(cursor.getColumnIndex(myproductsdatabase.PRODUCTS_QUANTITY_PER_UNIT));
                double availablequantity = cursor.getDouble(cursor.getColumnIndex(myproductsdatabase.PRODUCTS_AVAILABLE_QUANTITY));

                Products products1 = new Products(id, category, trademark, sellingprice, purchaseprice, quantityperunit, availablequantity);
                products.add(products1);
            } while (cursor.moveToPrevious());
            cursor.close();
        }
        return products;
    }

    public ArrayList<Products> GetProducts(String categorysearch) {
        ArrayList<Products> products = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + myproductsdatabase.PRODUCTS_TB_NAME + " WHERE " + myproductsdatabase.PRODUCTS_CATEGORY + " LIKE ? ", new String[]{categorysearch + "%"});
        if (cursor != null && cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(myproductsdatabase.PRODUCTS_ID));
                String category = cursor.getString(cursor.getColumnIndex(myproductsdatabase.PRODUCTS_CATEGORY));
                String trademark = cursor.getString(cursor.getColumnIndex(myproductsdatabase.PRODUCTS_TRADE_MARK));
                double sellingprice = cursor.getDouble(cursor.getColumnIndex(myproductsdatabase.PRODUCTS_SELLING_PRICE));
                double purchaseprice = cursor.getDouble(cursor.getColumnIndex(myproductsdatabase.PRODUCTS_PURCHASING_PRICE));
                String quantityperunit = cursor.getString(cursor.getColumnIndex(myproductsdatabase.PRODUCTS_QUANTITY_PER_UNIT));
                double availablequantity = cursor.getDouble(cursor.getColumnIndex(myproductsdatabase.PRODUCTS_AVAILABLE_QUANTITY));


                Products products1 = new Products(id, category, trademark, sellingprice, purchaseprice, quantityperunit, availablequantity);
                products.add(products1);
            } while (cursor.moveToFirst());
            cursor.close();
        }
        return products;

    }

    public double getallslled() {
        Cursor c1 = null;
        double p1, all1 = 0;
        c1 = database.rawQuery(" SELECT * FROM " + myproductsdatabase.PRODUCTS_TB_NAME, null);
        if (c1 != null && c1.moveToLast()) {
            do {

                p1 = c1.getDouble(3);
                all1 += p1;
            } while (c1.moveToPrevious());
        }

        c1.close();
        return all1;
    }

    public int getAllcategory() {
        int j = GetUniqueCatNo();
        return j;
    }

    public double getcosts() {
        Cursor c1 = null;
        double p1, all1 = 0;
        c1 = database.rawQuery(" SELECT * FROM " + myproductsdatabase.PRODUCTS_TB_NAME, null);
        if (c1 != null && c1.moveToLast()) {
            do {

                p1 = c1.getDouble(4);
                all1 += p1;
            } while (c1.moveToPrevious());
        }

        c1.close();
        return all1;
    }

    public int GetAllCatNo() {
        Cursor c1 = null;
        c1 = database.rawQuery(" SELECT * FROM " + myproductsdatabase.PRODUCTS_TB_NAME, null);
        if (c1 != null && c1.moveToLast()) {
            do {  // ممكن نستخدم nested for ونلف على اول عنصر ونشوفه هل بيساوى اى عنصر اكثر من عنصر
                allcat = c1.getString(1);
                x++;
//                myarr = new String[x];
//                myarr[x] = allcat;
            } while (c1.moveToPrevious());
        }
        c1.close();
        return x;
    }

    public String[] mycatarr() {
        int k1 = 0;
        int j = GetAllCatNo();
        int h = j - 1;
        myCat = new String[h];
        Cursor c2;
        c2 = database.rawQuery(" SELECT * FROM " + myproductsdatabase.PRODUCTS_TB_NAME, null);

        do {
            if (c2 != null && c2.moveToNext()) {
                myCat[k1] = c2.getString(1);
                k1++;
            }
        } while (c2.moveToNext());

        LinkedHashSet<String> linkedHashSet = new LinkedHashSet<String>(Arrays.asList(myCat));// convert array from DB to unrepeated string list
        String[] notDupCat = linkedHashSet.toArray(new String[linkedHashSet.size()]);// convert unrepeated string list to array


        c2.close();
        return notDupCat;
    }



    public int GetUniqueCatNo() {
        String[] Categories = mycatarr();
        int mylegth = Categories.length;
        return mylegth;
    }

    public double[] getQuantityperunit() {
        int j = GetAllCatNo();
        myquantitystr1 = new String[j];
        myquantitystr2 = new String[j];
        Cursor c2;
        c2 = database.rawQuery(" SELECT * FROM " + myproductsdatabase.PRODUCTS_TB_NAME, null);

        do {
            if (c2 != null && c2.moveToNext()) {
                myquantitystr1[k] = c2.getString(5);
                k++;
            }
        } while (c2.moveToNext());

        for (int m = 0; m < j; m++) {
            myquantitystr2[m] = myquantitystr1[m].replaceAll("[a-z][A-Z]", "");
            myquantity[m] = Double.parseDouble(myquantitystr2[m]);
        }

        return myquantity;
    }

    public double getAllCosts() {
        double Allcosts = 0;
        int n = GetAllCatNo();
        allavailableQ = new double[n];
        costsperunit = new double[n];
        myarr = new double[n];
        Cursor c1 = null;
        double x1 = 0, x2 = 0, tot = 0;
        c1 = database.rawQuery(" SELECT * FROM " + myproductsdatabase.PRODUCTS_TB_NAME, null);
        if (c1 != null && c1.moveToLast()) {
            do {

                x1 = allavailableQ[l] = c1.getDouble(6);
                x2 = costsperunit[l] = c1.getDouble(4);
                tot = x1 * x2;
                myarr[l] = tot;
                l++;
            } while (c1.moveToPrevious());
        }
        c1.close();

        for (int e = 0; e < myarr.length; e++) {
            Allcosts += myarr[e];
        }
        return Allcosts;

    }

    public double AllIncomeTot() {
        double Allincome1 = 0;
        int n1 = GetAllCatNo();
        allavailableQ1 = new double[n1];
        incomeperunit = new double[n1];
        myarr1 = new double[n1];
        Cursor c2 = null;
        double x3 = 0, x4 = 0, tot1 = 0;
        c2 = database.rawQuery(" SELECT * FROM " + myproductsdatabase.PRODUCTS_TB_NAME, null);
        if (c2 != null && c2.moveToLast()) {
            do {
                x3 = allavailableQ1[l1] = c2.getDouble(6);
                x4 = incomeperunit[l1] = c2.getDouble(3);
                tot1 = x3 * x4;
                myarr1[l1] = tot1;
                l1++;
            } while (c2.moveToPrevious());
        }
        c2.close();

        for (int e1 = 0; e1 < myarr1.length; e1++) {
            Allincome1 += myarr1[e1];
        }
        return Allincome1;

    }

    public String[] GetSearchedTM(String Scat) {
        int O = 0;

        String S = null;
        String[] mySCAT;
        Cursor cursor1 = database.rawQuery("SELECT * FROM " + myproductsdatabase.PRODUCTS_TB_NAME + " WHERE " + myproductsdatabase.PRODUCTS_CATEGORY + " LIKE ? ", new String[]{Scat + "%"});
        if (cursor1 != null && cursor1.moveToNext()) {
            do {
                O++;
            } while (cursor1.moveToNext());

        }
        T = O;
        cursor1.close();


        mySCAT = ST(Scat);

        return mySCAT;

    }

    public String[] ST(String S) {
        int z =0;
        String[] MYS;
        MYS = new String[T];
        Cursor cursor2 ;
        cursor2 = database.rawQuery("SELECT * FROM " + myproductsdatabase.PRODUCTS_TB_NAME + " WHERE " + myproductsdatabase.PRODUCTS_CATEGORY + " LIKE ? ", new String[]{S + "%"});
        if (cursor2 != null && cursor2.moveToNext()) {
            do {

                    S = cursor2.getString(2);
                    MYS[z] = S;
                    z++;

            } while (cursor2.moveToNext());
            cursor2.close();
        }

        return MYS;
    }

    public ArrayList<Products> GetSearchProducts(String categorysearch , String Trademark){
        ArrayList<Products> products = new ArrayList<>();
//        Cursor cursor7 = database.rawQuery(" SELECT * FROM " + myproductsdatabase.PRODUCTS_TB_NAME, null);
        Cursor cursor7 = database.rawQuery(" SELECT * FROM " + myproductsdatabase.PRODUCTS_TB_NAME + " WHERE " + myproductsdatabase.PRODUCTS_CATEGORY + " LIKE ? " +  " AND " + myproductsdatabase.PRODUCTS_TRADE_MARK + " LIKE ? ", new String[]{ categorysearch + "%" , Trademark + "%"});
//        Cursor cursor7 = database.rawQuery("SELECT * FROM " + myproductsdatabase.PRODUCTS_TB_NAME + " WHERE " + myproductsdatabase.PRODUCTS_TRADE_MARK + " LIKE ? ", new String[]{Trademark + "%"});
        if (cursor7 != null && cursor7.moveToFirst()) {
            do {
                int id1 = cursor7.getInt(cursor7.getColumnIndex(myproductsdatabase.PRODUCTS_ID));
                String category1 = cursor7.getString(cursor7.getColumnIndex(myproductsdatabase.PRODUCTS_CATEGORY));
                String trademark1 = cursor7.getString(cursor7.getColumnIndex(myproductsdatabase.PRODUCTS_TRADE_MARK));
                double sellingprice1 = cursor7.getDouble(cursor7.getColumnIndex(myproductsdatabase.PRODUCTS_SELLING_PRICE));
                double purchaseprice1 = cursor7.getDouble(cursor7.getColumnIndex(myproductsdatabase.PRODUCTS_PURCHASING_PRICE));
                String quantityperunit1 = cursor7.getString(cursor7.getColumnIndex(myproductsdatabase.PRODUCTS_QUANTITY_PER_UNIT));
                double availablequantity1 = cursor7.getDouble(cursor7.getColumnIndex(myproductsdatabase.PRODUCTS_AVAILABLE_QUANTITY));

                Products products2 = new Products(id1, category1, trademark1, sellingprice1, purchaseprice1, quantityperunit1, availablequantity1);
                products.add(products2);
            } while (cursor7.moveToNext());
            cursor7.close();
        }
        return products;

    }




}