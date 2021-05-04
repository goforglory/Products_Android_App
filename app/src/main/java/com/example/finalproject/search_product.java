package com.example.finalproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ScrollingTabContainerView;

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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;

public class search_product extends AppCompatActivity {

    private Spinner catsp , TMsp;
    Button btn_main , btn_search;
    String [] myscat;
    String [] editcat ;
    String [] MYSCATEGORY;
    String [] NEWTM;
    String myselectedcat;
    String myselectedTM;
    public static String SEARCH_CATEGORY_KEY = "catkey";
    public static String SEARCH_TRADEMARK_KEY= "TMkey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_product);

        btn_search = (Button)findViewById(R.id.search_btn);
        btn_main = (Button)findViewById(R.id.search_btn_main);
        catsp = (Spinner)findViewById(R.id.search_by_cat);
        TMsp =(Spinner)findViewById(R.id.search_by_TM);

        btn_main.setOnClickListener(new View.OnClickListener() {
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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this , android.R.layout.simple_spinner_item,editcat);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catsp.setAdapter(adapter);

        catsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                myselectedcat = (String) parent.getSelectedItem();
                DataBaseAccess dataBaseAccess = DataBaseAccess.getInstance(getBaseContext());
                dataBaseAccess.Open();
                MYSCATEGORY = dataBaseAccess.GetSearchedTM(myselectedcat);
                GetTM(MYSCATEGORY  , myselectedcat);
                dataBaseAccess.Close();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void GetTM (String [] NewTM , String MySelCat ){
        LinkedHashSet<String> linkedHashSet1 = new LinkedHashSet<String>(Arrays.asList(NewTM));// convert array from DB to unrepeated string list
        NEWTM = linkedHashSet1.toArray(new String[linkedHashSet1.size()]);// convert unrepeated string list to array


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(getBaseContext() , android.R.layout.simple_spinner_item , NEWTM );
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        TMsp.setAdapter(adapter1);

        TMsp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                myselectedTM = (String) parent.getSelectedItem();
                btn_search.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getBaseContext() , search_result.class);
                        intent.putExtra(SEARCH_CATEGORY_KEY , MySelCat);
                        intent.putExtra(SEARCH_TRADEMARK_KEY , myselectedTM);
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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