package com.example.finalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class AllPayments extends AppCompatActivity {

    TextInputEditText et_noofcat , et_noofpro , et_costs;
    Button btn_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_payments);

        et_noofcat = (TextInputEditText)findViewById(R.id.allipurchased_no_cat);
        et_costs = (TextInputEditText)findViewById(R.id.allpurchased_money);
        et_noofpro = (TextInputEditText)findViewById(R.id.allpurchased_no_products);
        btn_main = (Button)findViewById(R.id.allpurchased_btn_main);

        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getBaseContext());
        dataBaseAccess.Open();

        double costs = dataBaseAccess.getAllCosts();
        int noofcat = dataBaseAccess.getAllcategory()-1;

        et_costs.setText("All costs is :" + costs);
        et_noofcat.setText(" no of cat : " + noofcat);
        dataBaseAccess.Close();

        btn_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , Main_page.class);
                startActivity(intent);
            }
        });
    }


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