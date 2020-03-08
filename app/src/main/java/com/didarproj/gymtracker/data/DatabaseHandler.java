package com.didarproj.gymtracker.data;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.didarproj.gymtracker.model.Item;
import com.didarproj.gymtracker.util.Constants;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {
    private final Context context;

    public DatabaseHandler(@Nullable Context context) {
        super(context, Constants.DB_NAME, null, Constants.DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GYM_TABLE= "CREATE TABLE " + Constants.TABLE_NAME + "("
                + Constants.KEY_ID + "INTERGER PRIMARY KEY,"
                + Constants.KEY_GYM_TYPE + " TEXT,"
                + Constants.KEY_GYM_WORKOUT + " TEXT,"
                + Constants.KEY_SET_REP + " INTEGER,"
                + Constants.KEY_GYM_WEIGHTS + " INTEGER,"
                + Constants.KEY_DATE_NAME + " LONG);";

        db.execSQL(CREATE_GYM_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);

        onCreate(db);
    }
    //CRUD Operations
    public  void addItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_GYM_TYPE, item.getGymType());
        values.put(Constants.KEY_GYM_WORKOUT, item.getGymWorkOut());
        values.put(Constants.KEY_SET_REP, item.getGymSet());
        values.put(Constants.KEY_GYM_WEIGHTS, item.getGymWeights());
        values.put(Constants.KEY_DATE_NAME, item.getDateGymAdded());

        //Insert the row
        db.insert(Constants.TABLE_NAME, null, values);
        Log.d("DBHandler", "added Item: ");
    }
    //Get an Item
    public  Item getItem(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(Constants.TABLE_NAME,
                new String[] {Constants.KEY_ID,
                        Constants.KEY_GYM_TYPE,
                        Constants.KEY_GYM_WORKOUT,
                        Constants.KEY_SET_REP,
                        Constants.KEY_GYM_WEIGHTS,
                        Constants.KEY_DATE_NAME},
                Constants.KEY_ID + "=?",
                new String[] {String.valueOf(id)}, null, null,null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Item item = new Item();
        if (cursor !=null) {
            item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
            item.setGymType(cursor.getString(cursor.getColumnIndex(Constants.KEY_GYM_TYPE)));
            item.setGymWorkOut(cursor.getString(cursor.getColumnIndex(Constants.KEY_GYM_WORKOUT)));
            item.setGymSet(cursor.getInt(cursor.getColumnIndex(Constants.KEY_SET_REP)));
            item.setGymWeights(cursor.getInt(cursor.getColumnIndex(Constants.KEY_GYM_WEIGHTS)));

            //convert Timestamp to something readable
            DateFormat dateFormat = DateFormat.getDateInstance();
            String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME)))
                   .getTime());

            item.setDateGymAdded(formattedDate);

        }
        return  item;
    }
    //Get all Items
    public List<Item> getAllItems() {
        SQLiteDatabase db = this.getReadableDatabase();

        List<Item> itemList = new ArrayList<>();

        Cursor cursor = db.query(Constants.TABLE_NAME,
                new String[]{Constants.KEY_ID,
                        Constants.KEY_GYM_TYPE,
                        Constants.KEY_GYM_WORKOUT,
                        Constants.KEY_SET_REP,
                        Constants.KEY_GYM_WEIGHTS,
                        Constants.KEY_DATE_NAME},
                null, null, null, null,
                Constants.KEY_DATE_NAME + " DESC");

        if (cursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                item.setGymType(cursor.getString(cursor.getColumnIndex(Constants.KEY_GYM_TYPE)));
                item.setGymWorkOut(cursor.getString(cursor.getColumnIndex(Constants.KEY_GYM_WORKOUT)));
                item.setGymSet(cursor.getInt(cursor.getColumnIndex(Constants.KEY_SET_REP)));
                item.setGymWeights(cursor.getInt(cursor.getColumnIndex(Constants.KEY_GYM_WEIGHTS)));

                //convert Timestamp to something readable
                DateFormat dateFormat = DateFormat.getDateInstance();
                String formattedDate = dateFormat.format(new Date(cursor.getLong(cursor.getColumnIndex(Constants.KEY_DATE_NAME)))
                        .getTime());
                item.setDateGymAdded(formattedDate);

                //Add to arraylist
                itemList.add(item);
            } while (cursor.moveToNext());
        }
        return itemList;
    }
    //Todo: Add updateItem
    public int updateItem(Item item) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Constants.KEY_GYM_TYPE, item.getGymType());
        values.put(Constants.KEY_GYM_WORKOUT, item.getGymWorkOut());
        values.put(Constants.KEY_SET_REP, item.getGymSet());
        values.put(Constants.KEY_GYM_WEIGHTS, item.getGymWeights());
        values.put(Constants.KEY_DATE_NAME, java.lang.System.currentTimeMillis());

        //updates the row
        return db.update(Constants.TABLE_NAME, values,
                Constants.KEY_ID + "=?",
                new String[]{String.valueOf(item.getId())});
    }
    //Todo: Add Delete Item
    public void deleteItem(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Constants.TABLE_NAME,
                Constants.KEY_ID + "=?",
                new String[]{String.valueOf(id)});

        //close
        db.close();

    }
    //Todo: getItemCount
    public int getItemsCount() {
        String countQuery = "SELECT * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }
}


