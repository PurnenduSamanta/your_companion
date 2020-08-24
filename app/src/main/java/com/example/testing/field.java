package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class field extends AppCompatActivity {
    EditText addtitle,notes;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_field);
        addtitle=findViewById(R.id.addtitle);
        notes=findViewById(R.id.notes);
        save=findViewById(R.id.save);
        Intent intent = getIntent();
        String str = intent.getStringExtra("message_key1");
        notes.setText(str);
        String str1 = intent.getStringExtra("message_key2");
        addtitle.setText(str1);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(addtitle.getText().toString().equals(""))&&(!(notes.getText().toString().equals(""))))
                {
                    DbHelper db = new DbHelper(field.this);
                    long a = db.insertData(addtitle.getText().toString(), notes.getText().toString());
                    if (a!=-1) {
                        Toast.makeText(field.this, "Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(field.this, RcView.class);
                        startActivity(intent);
                    } else
                        {
                        Toast.makeText(field.this, "Something Wrong Happend", Toast.LENGTH_SHORT).show();
                        }
                }
                    else{
                        Toast.makeText(field.this, "You should fill up both the fields", Toast.LENGTH_SHORT).show();
                    }
            }
        });

    }
                        }