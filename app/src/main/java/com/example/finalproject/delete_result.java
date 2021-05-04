package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class delete_result extends AppCompatActivity  {

    RecyclerView rv;
    Button btn_main;

    String search_cat , search_TM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_result);
        rv = (RecyclerView)findViewById(R.id.delete_recycler);
        btn_main = (Button)findViewById(R.id.delete_search_btn_main);

        Intent intent1 = getIntent();
        search_cat = intent1.getStringExtra(Delete_Products.DELETE_SEARCH_CATEGORY_KEY);
        search_TM = intent1.getStringExtra(Delete_Products.DELETE_SEARCH_TRADEMARK_KEY);

        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(this);
        dataBaseAccess.Open();
        ArrayList<Products> products;
        products = dataBaseAccess.GetSearchProducts(search_cat,search_TM);
        dataBaseAccess.Close();

        DeleteRcyclerAdapter adapter = new DeleteRcyclerAdapter(products , this);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(lm);
        rv.setAdapter(adapter);



        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , Main_page.class);
                startActivity(intent);
            }
        });

    }


//    @Override
//    public void Mypro (Products p1) {
////        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(this);
//////        int S = p1.getId();
//////        Toast.makeText(getBaseContext() ,S+"" , Toast.LENGTH_LONG ).show();
////        dataBaseAccess.Open();
////        dataBaseAccess.DeleteProduct(p1);
////        dataBaseAccess.Close();
//        Toast.makeText(getBaseContext() , "Deleted successfully" , Toast.LENGTH_LONG).show();
//        Intent intent = new Intent(getBaseContext() , Main_page.class);
//        startActivity(intent);
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