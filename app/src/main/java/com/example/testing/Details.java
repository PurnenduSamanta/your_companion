package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Details extends AppCompatActivity {
    TextView details;
    Button edit;
    String str2;
    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        details=findViewById(R.id.details);
        edit=findViewById(R.id.edit);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key");
         str2 = intent.getStringExtra("message_keya");
         a=intent.getIntExtra("message_keyb",1);
        details.setText(str);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Details.this,field.class);
                String str=details.getText().toString();
                intent.putExtra("message_key1", str);
                intent.putExtra("message_key2", str2);
                DbHelper db=new DbHelper(Details.this);
                db.deleteData(a);
                startActivity(intent);
            }
        });


    }
}