package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class AllIncome extends AppCompatActivity {

    TextInputEditText txticome , txtnoofproducts , txtnoofcat;
    Button btn_main;
    int x =0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_income);

        txticome = (TextInputEditText)findViewById(R.id.allincome_money);
        txtnoofproducts = (TextInputEditText)findViewById(R.id.allincome_no_products);
        txtnoofcat = (TextInputEditText)findViewById(R.id.allincome_no_cat);
        btn_main = (Button)findViewById(R.id.allincome_btn_main);

        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getBaseContext());
        dataBaseAccess.Open();
        double income = dataBaseAccess.AllIncomeTot();
        int noofcat = dataBaseAccess.getAllcategory()-1;
//        String [] mystr = dataBaseAccess.mycatarr();
//        x = dataBaseAccess.getAllcategory();
//        for (int z= 0 ; z < x ; z++) {
//            switch (z){
//                case 0 :
//                    txticome.setText(mystr[0]);
//                    break;
//                case 1 :
//                    txticome2.setText(mystr[1]);
//                    break;
//                case 2 :
//                    txtnoofcat.setText(mystr[2]);
//                    break;
//                case 3 :
//                    txtnoofproducts.setText(mystr[3]);
//                    break;
//
//            }
//
////            String s = mystr[z];
////            Toast.makeText(getBaseContext(), s, Toast.LENGTH_SHORT).show();
//        }
        txticome.setText(income+" LE");
        txtnoofcat.setText(noofcat+" cat");
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