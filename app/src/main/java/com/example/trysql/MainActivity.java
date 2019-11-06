package com.example.trysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    EditText name, number;
    Button add, show, delete;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name =  findViewById(R.id.name);
        number =  findViewById(R.id.number);
        add =  findViewById(R.id.add);
        show = findViewById(R.id.showid);
        delete =  findViewById(R.id.delete);

        final SQLiteDatabase db = openOrCreateDatabase("studentDB",Context.MODE_PRIVATE,null);
        db.execSQL("CREATE TABLE IF NOT EXISTS student(name VARCHAR, number VARCHAR)");

        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String name1 =  name.getText().toString().trim();
                String nummber1 = number.getText().toString().trim();

                if (name1.isEmpty() || nummber1.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "Enter the value", Toast.LENGTH_LONG).show();
                }
                else
                {
                    db.execSQL("INSERT INTO student VALUES ('" + name.getText() +"', '" + number.getText() +"')");
                    Toast.makeText(MainActivity.this, "Entry is done",Toast.LENGTH_LONG).show();
                }
            }
        });

        show.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Cursor C_1 = db.rawQuery("SELECT * FROM student where name = '" + name.getText() +"'",null);
                if (C_1.moveToFirst())
                {
                    Bundle bundle = new Bundle();
                   bundle.putString("name", C_1.getString(0));
                   bundle.putString("number", C_1.getString(1));

                    startActivity(new Intent(MainActivity.this, Main2Activity.class).putExtras(bundle));
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor c_1 = db.rawQuery("SELECT * FROM student WHERE name = '" + name.getText() + "'",null);

                if (c_1.moveToFirst())
                {
                    db.execSQL("DELETE FROM student WHERE name ='" + name.getText() + "'");
                    Toast.makeText(MainActivity.this,"Record Deleted",Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(MainActivity.this,"Invalid roll",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
