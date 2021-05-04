package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Edit_Search_Result extends AppCompatActivity implements RecyclerAdapter.selectedPro {

    String Search_cat , search_TM ;
    RecyclerView myList;
    Button btn_main ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__search__result);
        myList =  findViewById(R.id.search_edit_lv);
        btn_main = (Button)findViewById(R.id.edit_btn_main);


        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , Main_page.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        Search_cat = intent.getStringExtra(edit_product.EDIT_CATEGORY_KEY);
        search_TM = intent.getStringExtra(edit_product.EDIT_TRADEMARK_KEY);

        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getBaseContext());
        dataBaseAccess.Open();
        ArrayList<Products> products = new ArrayList<>();
        products =  dataBaseAccess.GetSearchProducts(Search_cat , search_TM);
        dataBaseAccess.Close();

        Products p = products.get(0);
        int S = p.getId();
        Toast.makeText(getBaseContext() , S+"" , Toast.LENGTH_LONG).show();

//        ArrayList<Products> myp = new ArrayList<>();
//        Products p1 = new Products(1, "cheese" , "omkoo" , 55 , 88 , "1kg" , 100);
//        Products p2 = new Products(1, "cheese" , "omkoo" , 55 , 88 , "1kg" , 100);
//        Products p3 = new Products(1, "cheese" , "omkoo" , 55 , 88 , "1kg" , 100);
//
//
//        myp.add(p1);
//        myp.add(p2);
//        myp.add(p3);

        RecyclerAdapter adapter = new RecyclerAdapter(products , this , this);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        myList.setHasFixedSize(true);
        myList.setLayoutManager(lm);
        myList.setAdapter(adapter);


//        editAdapter adapter = new editAdapter(getBaseContext() , R.layout.custom_products , products);
//        myList.setAdapter(adapter);

    }

    public void gotoActivity(){
        Intent intent = new Intent(getBaseContext() , Edit.class);
        startActivity(intent);

    }

    @Override
    public void selectedPro(Products p1) {
        startActivity(new Intent(getBaseContext() , Edit.class).putExtra("data", p1));
    }
}