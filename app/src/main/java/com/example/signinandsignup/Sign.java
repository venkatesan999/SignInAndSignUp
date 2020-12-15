package com.example.signinandsignup;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class Sign extends AppCompatActivity {


    EditText sign_email_id;
    EditText signup_password;
    Button sign;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign);

        sign_email_id=findViewById(R.id.sign_email_id);
        signup_password=findViewById(R.id.signup_password);
        sign=findViewById(R.id.sign);

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                     getStringText();

                Intent intent = new Intent(Sign.this, UpdateActivity.class);
                startActivity(intent);

//                if(pass != null && pass.getEmail() != null ){
//
//                    Toast.makeText(Sign.this, pass.getEmail(), Toast.LENGTH_SHORT).show();
//                }else
//                    Toast.makeText(Sign.this, "Email or Password Invalid", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void getStringText() {

        Realm.init(Sign.this);

        Realm instance=Realm.getDefaultInstance();

        if(!instance.isInTransaction())
        {
            instance.beginTransaction();
        }

        GetAndSet getAndSet=instance.where(GetAndSet.class)
                .equalTo("email",sign_email_id.getText().toString())
                .findFirst();

        if(getAndSet != null){

            getAndSet.deleteFromRealm();
        }

        instance.commitTransaction();

//        if (getAndSet!=null) {
//            {
//                if (getAndSet.getPassword().equals(signup_password.getText().toString())) {
//                    //new Intent
//                } else {
//                    Toast.makeText(this, "Invalid Password", Toast.LENGTH_SHORT).show();
//                }
//            } else{
//                Toast.makeText(this, "User Not Found", Toast.LENGTH_SHORT).show();
//            }
//        }

        if(!instance.isClosed()){
            instance.close();
        }

    }

}
