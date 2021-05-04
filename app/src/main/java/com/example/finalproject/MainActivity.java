package com.example.finalproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public  String USERNAME = "admin" ;
    public  String PASSWORD = "admin0000" ;
    public static final int REQUEST_CODE=1;
    private static final String SHARED_FILE = "myfile";
    private static final String USER_F="myuser";
    private static final String PASS_F="mypass";
    SharedPreferences sp ;
    public static final String MYFILE= "myfileforpass" , KEY_USER = "USER" , KEY_PASS = "PASS";
    String U , P;

    EditText et_pass , et_user;
    Button btn_log , btn_change;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        et_pass = (EditText)findViewById(R.id.main_et_pass);
        et_user = (EditText)findViewById(R.id.main_et_user);

        btn_log = (Button)findViewById(R.id.main_btn_login);
        btn_change = (Button)findViewById(R.id.main_btn_change);


        btn_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               check();
            }
        });

        btn_change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext() , change_user_pass.class);
                startActivityForResult(intent , REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_CODE){
            String NEWUSER = data.getStringExtra("new_user_name" );
            String NEWPASS = data.getStringExtra("new_pass_word" );

            USERNAME = NEWUSER;
            PASSWORD = NEWPASS;

            SaveUser();
        }
    }

    public void check(){

        DisplayUser();
        String user = et_user.getText().toString();
        String pass = et_pass.getText().toString();


        if(user.equals(U) ){
            if(pass.equals(P)){
                // الانتقال الى الاكتيفتى الخاصة بالوجهه الاولى للبرنامج
                Intent intent = new Intent(getBaseContext() , Main_page.class);
                startActivity(intent);
            }else{
                et_pass.setError("invalid or wrong pass");
            }
        }
        else {
            et_user.setError("invalid or wrong user");
        }
    }

    private void DisplayUser(){
        SharedPreferences sp = getSharedPreferences(SHARED_FILE , MODE_PRIVATE);
        String s = sp.getString(USER_F , "admin");
        String p = sp.getString(PASS_F , "admin0000");

        U = s;
        P = p;
    }

    private void SaveUser(){
        SharedPreferences sp = getSharedPreferences(SHARED_FILE , MODE_PRIVATE);

        SharedPreferences.Editor editor1 = sp.edit();
        editor1.putString(USER_F,USERNAME);
        editor1.putString(PASS_F,PASSWORD);
        editor1.apply();
    }
}