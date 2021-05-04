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

public class Add extends AppCompatActivity {
    TextInputEditText et_add_cat , et_add_TM , et_add_sellingprice , et_add_purchaseprice , et_add_quantityperunit , et_add_availablequantity;
    Button btn_add_save , btn_add_main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        et_add_cat = (TextInputEditText)findViewById(R.id.add_et_cat);
        et_add_TM = (TextInputEditText)findViewById(R.id.add_et_trademark);
        et_add_sellingprice = (TextInputEditText)findViewById(R.id.add_et_sellingprice);
        et_add_purchaseprice = (TextInputEditText)findViewById(R.id.add_et_purchaseprice);
        et_add_quantityperunit = (TextInputEditText)findViewById(R.id.add_et_quantity_per_unit);
        et_add_availablequantity = (TextInputEditText)findViewById(R.id.add_et_available_quantity);
        btn_add_save = (Button)findViewById(R.id.Add_btn_product_save);
        btn_add_main = (Button)findViewById(R.id.Add_btn_main);

        btn_add_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double NewSellingPrice = 0;
                double NewPurchasePrice = 0;
                double NewAvailablequantity = 0;
                String Newcat = et_add_cat.getText().toString();
                String NewTM = et_add_TM.getText().toString();
                NewSellingPrice = Double.parseDouble(et_add_sellingprice.getText().toString());
                NewPurchasePrice = Double.parseDouble(et_add_purchaseprice.getText().toString());
                String NewQuantityperunit = et_add_quantityperunit.getText().toString();
                NewAvailablequantity = Double.parseDouble(et_add_availablequantity.getText().toString());
                Products products = new Products(Newcat, NewTM, NewSellingPrice, NewPurchasePrice, NewQuantityperunit, NewAvailablequantity);

                if (Newcat == null ){
                    et_add_cat.setError("Invalid input");
                }else {
                    if (NewSellingPrice == 0){
                        et_add_sellingprice.setError("Invalid input");
                    }else {
                        if (NewPurchasePrice == 0){
                            et_add_purchaseprice.setError("Invalid input");
                        }else {
                            if(NewQuantityperunit == null ){
                                et_add_quantityperunit.setError("Invalid input");
                            }else {
                                    if (NewAvailablequantity == 0){
                                        et_add_availablequantity.setError("Invalid input");
                                    }else {
                                        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getBaseContext());
                                        dataBaseAccess.Open();
                                        if (dataBaseAccess.InsertProduct(products)) {
                                            Toast.makeText(getBaseContext(), "New Product Added", Toast.LENGTH_LONG).show();
                                        } else {
                                            Toast.makeText(getBaseContext(), "Invalid or Error ", Toast.LENGTH_LONG).show();
                                        }
                                    }
                            }
                        }
                    }
                }
            }
        });
        btn_add_main.setOnClickListener(new View.OnClickListener() {
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