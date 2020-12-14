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
    Button sign_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email_id);
        password = findViewById(R.id.password);
        sign_in = findViewById(R.id.sign_in);

        sign_in.setOnClickListener(new View.OnClickListener() {
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
        Realm.init(MainActivity.this);
        Realm realmInstance = Realm.getDefaultInstance();
        if (!realmInstance.isInTransaction()) {
            realmInstance.beginTransaction();
        }

        int primary = 0;
        if (realmInstance.where(GetAndSet.class) != null && realmInstance.where(GetAndSet.class).max("id") != null) {
            primary = realmInstance.where(GetAndSet.class).max("id").intValue() + 1;
        }

        GetAndSet row = realmInstance.createObject(GetAndSet.class, primary);
        row.setEmail(email.getText().toString());
        row.setPassword(password.getText().toString());
        realmInstance.commitTransaction();
        if (!realmInstance.isClosed()) {
            realmInstance.close();
        }

        return true;
    }

}
