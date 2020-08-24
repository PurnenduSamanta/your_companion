package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DbHelper db=new DbHelper(this);
        boolean a=db.chkDatabaseEmpty();
        if(a)
        {
            setContentView(R.layout.rc_view);
            Intent intent=new Intent(MainActivity.this,RcView.class);
            startActivity(intent);

        }
        else
        {
            Intent intent=new Intent(MainActivity.this,empty_activity.class);
            setContentView(R.layout.activity_empty_activity);
            startActivity(intent);

        }


    }
}