package com.example.finalproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class editAdapter extends BaseAdapter {

    private final Context context ;
    private final ArrayList<Products> products;
    private final int Resource;

    public editAdapter(Context context , int Resource , ArrayList<Products> products){

        this.context = context;
        this.products = products;
        this.Resource = Resource;

    }
    public void addItem(Products myporducts){
        this.products.add(myporducts) ;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Products getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
             View Vi = convertView;
            if (Vi == null){
                Vi = LayoutInflater.from(context).inflate(Resource ,null ,false);
            }

            TextView tv_id_data = (TextView) (Vi.findViewById(R.id.custom_tv_id_data));
            TextView tv_cat_data =(TextView) (Vi.findViewById(R.id.custom_tv_cat_data));
            TextView tv_TM_data = (TextView) (Vi.findViewById(R.id.custom_tv_TM_data));
            TextView tv_selling_data =(TextView) (Vi.findViewById(R.id.custom_tv_selling_price_data));
            TextView tv_purchase_data = (TextView)(Vi.findViewById(R.id.custom_tv_purchase_price_data));
            TextView tv_quantityperunit_data = (TextView)(Vi.findViewById(R.id.custom_tv_quantityperunit_data));
            TextView tv_available_quantity_data = (TextView)Vi.findViewById((R.id.custom_tv_available_quantity_data));

            TextView tv_id = (TextView)(Vi.findViewById(R.id.custom_tv_id));
            TextView tv_cat = (TextView)(Vi.findViewById(R.id.custom_tv_cat));
            TextView tv_TM = (TextView)(Vi.findViewById(R.id.custom_tv_TM));
            TextView tv_selling = (TextView)(Vi.findViewById(R.id.custom_tv_selling_price));
            TextView tv_purchase = (TextView)(Vi.findViewById(R.id.custom_tv_purchase_price));
            TextView tv_quantityperunit = (TextView)(Vi.findViewById(R.id.custom_tv_quantityperunit));
            TextView tv_available_quantity = (TextView)(Vi.findViewById(R.id.custom_tv_available_quantity));
            Button btn_edit = (Button)(Vi.findViewById(R.id.custom_edit_btn));


            Products p = getItem(position);

            tv_id_data.setText(p.getId());
            tv_cat_data.setText(p.getCategory());
            tv_TM_data.setText(p.getTrademark());
            tv_selling_data.setText(p.getSellingprice()+"");
            tv_purchase_data.setText(p.getPurchaseprice()+"");
            tv_quantityperunit_data.setText(p.getQuantityperunit());
            tv_available_quantity_data.setText(p.getAvailablequantity()+"");


        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Edit_Search_Result S = new Edit_Search_Result();
                S.gotoActivity();
            }
        });

        return Vi;
    }


}
