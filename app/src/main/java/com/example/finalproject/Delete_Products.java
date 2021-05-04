package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class Delete_Products extends AppCompatActivity {

    private Spinner catsp , TMsp;
//    String [] myscat;
    String myselectedcat;
    String myselectedTM;
    String [] editcat ;
    String [] myscat ;
    String [] MYSCATEGORY;
    String [] NEWTM;
    public static String DELETE_SEARCH_CATEGORY_KEY = "delcatkey";
    public static String DELETE_SEARCH_TRADEMARK_KEY= "delTMkey";
    ArrayAdapter<String> adapter , adapter1;

    Button btn_search , btn_menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__products2);
        catsp =(Spinner)findViewById(R.id.delete_search_by_cat);
        TMsp = (Spinner)findViewById(R.id.delete_search_by_TM);

        btn_menu = (Button)findViewById(R.id.delete_search_btn_main);
        btn_search =(Button)findViewById(R.id.delete_search_btn);

        btn_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , Main_page.class);
                startActivity(intent);
            }
        });
        DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(this);
        dataBaseAccess.Open();

        myscat = dataBaseAccess.mycatarr();
        int Z = myscat.length;

        editcat = new String[Z-1];

        for (int x=0 ; x < myscat.length-1 ; x++){
            String S = myscat[x];
            editcat[x] = S;
        }


        // Toast.makeText(getBaseContext() , editcat.length +"" , Toast.LENGTH_LONG).show();

        dataBaseAccess.Close();

        adapter = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_item,editcat);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catsp.setAdapter(adapter);

        catsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                myselectedcat = (String) parent.getSelectedItem();
                Toast.makeText(getBaseContext() , myselectedcat , Toast.LENGTH_LONG).show();
                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getBaseContext());
                dataBaseAccess.Open();
                MYSCATEGORY = dataBaseAccess.GetSearchedTM(myselectedcat);
                GetTM(MYSCATEGORY  , myselectedcat);
                dataBaseAccess.Close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(getBaseContext() , "Nothing selected" , Toast.LENGTH_LONG).show();
            }
        });

    }

    public void GetTM (String [] NewTM , String MySelCat ) {
        LinkedHashSet<String> linkedHashSet1 = new LinkedHashSet<String>(Arrays.asList(NewTM));// convert array from DB to unrepeated string list
        NEWTM = linkedHashSet1.toArray(new String[linkedHashSet1.size()]);// convert unrepeated string list to array


        adapter1 = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, NEWTM);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TMsp.setAdapter(adapter1);

        TMsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                myselectedTM = (String) parent.getSelectedItem();
                btn_search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getBaseContext(), delete_result.class);
                        intent.putExtra(DELETE_SEARCH_CATEGORY_KEY, MySelCat);
                        intent.putExtra(DELETE_SEARCH_TRADEMARK_KEY, myselectedTM);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(getBaseContext() , "Nothing selected" , Toast.LENGTH_LONG).show();
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