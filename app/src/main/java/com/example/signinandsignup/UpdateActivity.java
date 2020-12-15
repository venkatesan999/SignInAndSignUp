package com.example.signinandsignup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import io.realm.Realm;

public class UpdateActivity extends AppCompatActivity {


    EditText update_email_id,update_password;
    Button update_btton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        update_email_id=findViewById(R.id.update_email_id);
        update_password=findViewById(R.id.update_password);
        update_btton=findViewById(R.id.update_Button);

        update_btton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateData();
            }
        });

    }
    private void updateData(){
        Realm.init(UpdateActivity.this);
        Realm realm=Realm.getDefaultInstance();
        if (!realm.isInTransaction()){
            realm.beginTransaction();
        }

        GetAndSet getAndSet=realm.where(GetAndSet.class)
                .equalTo("email",update_email_id.getText().toString())
                .findFirst();
        if (getAndSet!=null){
            getAndSet.setPassword(update_password.getText().toString());

            realm.copyToRealmOrUpdate(getAndSet);
        }
        realm.commitTransaction();
        if (realm.isClosed()){
            realm.isClosed();
        }
    }

}