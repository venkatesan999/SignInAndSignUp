package com.example.signinandsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    Button sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email_id);
        password = findViewById(R.id.password);
        sign_up = findViewById(R.id.signup_Button);

        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (save()) {
                    Intent intent = new Intent(MainActivity.this, Sign.class);
                    startActivity(intent);
                }
            }
        });

    }

    private boolean save() {

        //Inittialisation of fthe Realm and getDefaultInstance____________________________________//
        Realm.init(MainActivity.this);
        Realm realmInstance = Realm.getDefaultInstance();

        //Transactions__________________________________________//
        if (!realmInstance.isInTransaction()) {
            realmInstance.beginTransaction();
        }

       //move to next table_id Querry___________________________________________________//
        int primary = 0;
        if (realmInstance.where(GetAndSet.class) != null && realmInstance.where(GetAndSet.class).max("id") != null) {
            primary = realmInstance.where(GetAndSet.class).max("id").intValue() + 1;
        }

        //Create table structure and enter the data values___________________________________________________________________//
        GetAndSet row = realmInstance.createObject(GetAndSet.class, primary);
        row.setEmail(email.getText().toString());
        row.setPassword(password.getText().toString());

        //commitTrasaction_______________________________________//
        realmInstance.commitTransaction();
        //close____________________________________//
        if (!realmInstance.isClosed()) {
            realmInstance.close();
        }

        return true;
    }

}
