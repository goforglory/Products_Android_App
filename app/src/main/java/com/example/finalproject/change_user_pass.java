package com.example.finalproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class change_user_pass extends AppCompatActivity {

    EditText et_olduser , et_oldpass , et_newuser , et_newpass , et_confuser , et_confpass;
    Button btn_savechange;
    SharedPreferences sp;
    public static final String USFILE = "usfile" , USER_KEY="SUERPASS" , PASS_KEY = "SUPERPASS";
    public static final int RESULT_CODE=1;
    public String UN = "admin" , PS = "admin0000";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_user_pass);
        et_olduser = (EditText)findViewById(R.id.change_et_olduser);
        et_oldpass = (EditText)findViewById(R.id.change_et_oldpass);
        et_newuser = (EditText)findViewById(R.id.change_et_newuser);
        et_newpass = (EditText)findViewById(R.id.change_et_newpass);
        et_confuser = (EditText)findViewById(R.id.change_et_confuser);
        et_confpass = (EditText)findViewById(R.id.change_et_confpass);

        btn_savechange = (Button)findViewById(R.id.change_btn_savechange);



        btn_savechange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetUserPass();

            }
        });

    }

    public void GetUserPass()
    {
        SaveChanges();
        String olduser = et_olduser.getText().toString();
        String oldpass = et_oldpass.getText().toString();
        String newuser = et_newuser.getText().toString();
        String newusercof = et_confuser.getText().toString();
        String newpass = et_newpass.getText().toString();
        String newpassconf = et_confpass.getText().toString();
        if (olduser.equals(UN)){
            if(oldpass.equals(PS)){
                if(newuser.equals(newusercof)){
                    if(newuser.length()<6){
                        et_newuser.setError("please enter at least 6 characters");
                        et_confuser.setError("please enter at least 6 characters");
                    }else {
                        if(newpass.equals(newpassconf)){
                            if(newpass.length()<6){
                                et_confpass.setError("please enter at least 6 characters");
                                et_newpass.setError("please enter at least 6 characters");
                            }else{

                                sp = getSharedPreferences(USFILE, MODE_PRIVATE);
                                SharedPreferences.Editor NEWUSER_PASS = sp.edit();

                                NEWUSER_PASS.putString(USER_KEY,newuser);
                                NEWUSER_PASS.putString(PASS_KEY,newpass);
                                NEWUSER_PASS.apply();

                                 UN = sp.getString(USER_KEY , UN);
                                 PS = sp.getString(PASS_KEY , PS);

                                Intent intent = new Intent();
                                intent.putExtra("new_user_name", sp.getString(USER_KEY , "admin"));
                                intent.putExtra("new_pass_word", sp.getString(PASS_KEY , "admin0000"));
                                setResult(RESULT_CODE , intent);
                                finish();

                            }
                        }else{
                            et_confpass.setError("password not match");
                            et_newpass.setError("password not match");
                        }
                    }


                }else{
                    et_confuser.setError("username not match");
                    et_newuser.setError("username not match");
                }

            }else {
                et_oldpass.setError("invalid or wrong password");
            }

        }else{
            et_olduser.setError("Invalid or wrong username");
        }

    }

    private void SaveChanges (){
      SharedPreferences  mysp = getSharedPreferences(USFILE, MODE_PRIVATE);
        UN = mysp.getString(USER_KEY , "admin");
        PS = mysp.getString(PASS_KEY , "admin0000");

    }


}