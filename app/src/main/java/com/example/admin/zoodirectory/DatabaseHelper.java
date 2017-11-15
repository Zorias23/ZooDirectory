package com.example.admin.zoodirectory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 11/13/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Animal.db";
    public static final String TABLE_NAME = "Animals";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_CATEGORY = "CATEGORY";
    public static final int DATABASE_VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_NAME + " TEXT PRIMARY KEY," + COLUMN_CATEGORY + " TEXT)";
             sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override //called whenever we change the database version, not first creation where we originally set it, but if we change it again
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
    public long saveAnimal(Animal animal)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, animal.getName());
        contentValues.put(COLUMN_CATEGORY, animal.getCategory());

       long row =  db.insert(TABLE_NAME, null, contentValues);  //returns row number, returns -1 if failed
        return row;
    }
    public List<Animal> getAnimalsAsList()
    {
        List<Animal> animalList = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(QUERY, null);//because we're doing Select *, I can pass null, if not, i have to have column names for second argument
        //cursor returns each row of data so I have to loop through results

        if(cursor.moveToFirst())
        {
            do {
                Animal animal = new Animal(cursor.getString(0), cursor.getString(1));
                Log.d("DatabaseHelper", "getAnimalsAsList: " + cursor.getString(0) + " " + cursor.getString(1));
                animalList.add(animal);
            }while(cursor.moveToNext());

        }
        return animalList;
    }
    public boolean containsRows()
    {
        boolean containsRows = false;

        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(QUERY, null);//because we're doing Select *, I can pass null, if not, i have to have column names for second argument
        //cursor returns each row of data so I have to loop through results

        if(cursor.moveToFirst())
        {
            do {
                containsRows = true;
                break;
            }while(cursor.moveToNext());

        }
        return containsRows;
    }
    public List<String> getCategoriesAsList()
    {
        List<String> categoryList = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(QUERY, null);//because we're doing Select *, I can pass null, if not, i have to have column names for second argument
        //cursor returns each row of data so I have to loop through results

        if(cursor.moveToFirst())
        {
            do {
                String s =  cursor.getString(1);
                Log.d("DatabaseHelper", "getCategoriesAsList: " + cursor.getString(1));
                //we don't want duplicate categories, more than one animal can be in a category
                if (!categoryList.contains(s))
                {
                    categoryList.add(s);
                }

            }while(cursor.moveToNext());

        }
        return categoryList;
    }
    public ArrayList<Animal> getAnimalsFromCategoriesAsList(String category)
    {
        ArrayList<Animal> animalList = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE CATEGORY = '" + category + "' ";
        Cursor cursor = database.rawQuery(QUERY, null);//because we're doing Select *, I can pass null, if not, i have to have column names for second argument
        //cursor returns each row of data so I have to loop through results

        if(cursor.moveToFirst())
        {
            do {
                Animal a = new Animal(cursor.getString(0), cursor.getString(1));
                Log.d("DatabaseHelper", "getAnimalsFromCategoriesAsList: " + cursor.getString(0) + " " + cursor.getString(1));
                animalList.add(a);
            }while(cursor.moveToNext());

        }
        return animalList;
    }
    public Cursor getAnimalsAsCursor()
    {
        List<Animal> personList = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String QUERY = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = database.rawQuery(QUERY, null);//because we're doing Select *, I can pass null, if not, i have to have column names for second argument
        //cursor returns each row of data so I have to loop through results
        return cursor;
    }
}
