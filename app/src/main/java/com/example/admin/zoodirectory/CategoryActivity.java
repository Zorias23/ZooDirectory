package com.example.admin.zoodirectory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    DatabaseHelper myDB;
    ListView lvmain;
    List<String> categoryList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        myDB = new DatabaseHelper(this);
        lvmain = findViewById(R.id.listViewCategory);
        //List<String> categoryList = new ArrayList<>();
        categoryList = myDB.getCategoriesAsList();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, categoryList);
        lvmain.setAdapter(arrayAdapter);
        AdapterView.OnItemClickListener mMessageClickedHandler = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                Log.d("CategoryActivity2", "onItemClick: position is  " + position + " and id is " + id);
                String category = categoryList.get(position);
                ArrayList<Animal> animalList = new ArrayList<>();
                animalList = myDB.getAnimalsFromCategoriesAsList(category);
                goToAnimalsActivity(v, animalList);
            }
        };
        lvmain.setOnItemClickListener(mMessageClickedHandler);
    }

    public void goToAnimalsActivity(View view, ArrayList<Animal> animalList) {
        Intent intent = new Intent(this, AnimalsActivity.class);
        intent.putParcelableArrayListExtra("animals", animalList);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Log.d("CategoryActivity", "onItemClick: you clicked position " + position);
        String category = categoryList.get(position);
        ArrayList<Animal> animalList = new ArrayList<>();
        animalList = myDB.getAnimalsFromCategoriesAsList(category);
        goToAnimalsActivity(view, animalList);

    }
}
