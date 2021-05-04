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

public class Edit extends AppCompatActivity {

    TextInputEditText et_edit_old_cat , et_edit_old_TM , et_edit_old_sellingprice , et_edit_old_purchaseprice , et_edit_old_quantityperunit , et_edit_old_availablequantity;
    Button btn_save_changes ;
    int myid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        et_edit_old_cat = (TextInputEditText)findViewById(R.id.edit_old_et_cat);
        et_edit_old_TM = (TextInputEditText)findViewById(R.id.edit_old_et_trademark);
        et_edit_old_sellingprice = (TextInputEditText)findViewById(R.id.edit_old_et_sellingprice);
        et_edit_old_purchaseprice = (TextInputEditText)findViewById(R.id.edit_old_et_purchaseprice);
        et_edit_old_quantityperunit = (TextInputEditText)findViewById(R.id.edit_old_et_quantity_per_unit);
        et_edit_old_availablequantity = (TextInputEditText)findViewById(R.id.edit_old_et_available_quantity);
        btn_save_changes = (Button)findViewById(R.id.edit_old_btn_product_save);


        Intent intent = getIntent();

        if (intent.getExtras() != null){

            Products products = (Products) intent.getSerializableExtra("data");
            et_edit_old_cat.setText(products.getCategory());
            et_edit_old_TM.setText(products.getTrademark());
            et_edit_old_sellingprice.setText(String.valueOf(products.getSellingprice()));
            et_edit_old_purchaseprice.setText(String.valueOf(products.getPurchaseprice()));
            et_edit_old_quantityperunit.setText(products.getQuantityperunit());
            et_edit_old_availablequantity.setText(String.valueOf(products.getAvailablequantity()));
            myid = products.getId();

        }

        btn_save_changes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newcat = et_edit_old_cat.getText().toString();
                String newtm = et_edit_old_TM.getText().toString();
                double newselling = Double.parseDouble(et_edit_old_sellingprice.getText().toString());
                double newpurchase = Double.parseDouble(et_edit_old_purchaseprice.getText().toString());
                String newquantityperunit = et_edit_old_quantityperunit.getText().toString();
                double newavailablequantity = Double.parseDouble(et_edit_old_availablequantity.getText().toString());

                Toast.makeText(getBaseContext() , myid+"" , Toast.LENGTH_LONG).show();
                Products products = new Products(myid , newcat , newtm , newselling  , newpurchase , newquantityperunit , newavailablequantity);
                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getBaseContext());
                dataBaseAccess.Open();
                dataBaseAccess.UpdateProducts(products);
                dataBaseAccess.Close();
                Toast.makeText(getBaseContext(), "Updated successfully" , Toast.LENGTH_LONG).show();
                Intent intent1 = new Intent(getBaseContext() , Main_page.class);
                startActivity(intent1);


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