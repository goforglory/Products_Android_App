package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Main_page extends AppCompatActivity {

    CardView add ;
    CardView delete ;
    CardView search ;
    CardView edit ;
    CardView allincome ;
    CardView allpayments ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        add = (CardView)findViewById(R.id.main_add);
        edit = (CardView)findViewById(R.id.main_edit);
        allincome = (CardView)findViewById(R.id.main_allincome);
        allpayments = (CardView)findViewById(R.id.main_allpayments);
        search = (CardView)findViewById(R.id.main_search_product);
        delete = (CardView)findViewById(R.id.main_delete_product);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , Delete_Products.class);
                startActivity(intent);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , search_product.class);
                startActivity(intent);
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , Add.class);
                startActivity(intent);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , edit_product.class);
                startActivity(intent);
            }
        });

        allincome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , AllIncome.class);
                startActivity(intent);
            }
        });

        allpayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , AllPayments.class);
                startActivity(intent);
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.operations_menu , menu);
//
//
//        return true;
//
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        return super.onOptionsItemSelected(item);
//
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.operations_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.operations_add_product:
                Intent intent = new Intent(getBaseContext() , Add.class);
                startActivity(intent);
                finish();
                return  true;

            case  R.id.operations_delete_product:
                Intent intent1 = new Intent(getBaseContext() , Delete_Products.class);
                startActivity(intent1);
                finish();
                return  true;

            case R.id.operations_edit_product:
                Intent intent2 = new Intent(getBaseContext() , edit_product.class);
                startActivity(intent2);
                finish();
                return  true;

            case R.id.operations_allcost:
                Intent intent3 = new Intent(getBaseContext() , AllPayments.class);
                startActivity(intent3);
                finish();
                return  true;

            case R.id.operations_allincome:
                Intent intent4 = new Intent(getBaseContext() , AllIncome.class);
                startActivity(intent4);
                finish();
                return  true;

            case R.id.operations_home:
                Intent intent5 = new Intent(getBaseContext() , Main_page.class);
                startActivity(intent5);
                finish();
                return  true;

            case R.id.operations_signout:
                Intent intent6 = new Intent(getBaseContext() , MainActivity.class);
                startActivity(intent6);
                finish();
                onBackPressed();
                return  true;

            case R.id.main_search:
                Intent intent7 = new Intent(getBaseContext() , search_product.class);
                startActivity(intent7);
                finish();
                return  true;


        }

        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;
    }
}