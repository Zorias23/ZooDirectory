package com.example.admin.zoodirectory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    List<Animal> animals = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDB = new DatabaseHelper(this);
        long returnVal = -1;
        //let's check if the database has rows, if not, we add them just once when the app is first run
        boolean hasRows = myDB.containsRows();
        if (!hasRows) {


            Animal a = new Animal("Zebra", "Striped");
            Animal b = new Animal("Tiger", "Striped");
            Animal c = new Animal("Eagle", "Arial");
            Animal d = new Animal("Penguin", "Aquatic");
            Animal e = new Animal("Bat", "Nocturnal");

            animals.add(a);
            animals.add(b);
            animals.add(c);
            animals.add(d);
            animals.add(e);
            //
            for (Animal g : animals) {
                returnVal = myDB.saveAnimal(g);
                if (returnVal > 0) {
                    Toast.makeText(MainActivity.this, "Successfully entered " + g.getName() + "!", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Something went wrong!", Toast.LENGTH_LONG).show();
                }
            }
        }
        //myDB.getAnimalsAsList(); //just want to see the logging so i know it's saved

    }

    public void goToCategoryActivity(View view) {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }
}
