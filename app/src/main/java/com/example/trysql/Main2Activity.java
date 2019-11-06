package com.example.trysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    TextView name01, number02;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        name01 = findViewById(R.id.nameid2);
        number02 = findViewById(R.id.numberid2);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        name01.setText(extras.getString("name"));
        number02.setText(extras.getString("number"));
    }
}
