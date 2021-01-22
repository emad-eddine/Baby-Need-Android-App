package com.kichou.imad.babyneeds.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.kichou.imad.babyneeds.R;
import com.kichou.imad.babyneeds.model.ItemModel;
import com.kichou.imad.babyneeds.util.Util;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private Context context ;

    public DatabaseHandler(@Nullable Context context) {
        super(context, Util.DATABASE_NAME, null, Util.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE_QUERY = "CREATE TABLE " + Util.TABLE_NAME
                +" ( " + Util.ITEM_ID + " INTEGER PRIMARY KEY , "
                + Util.ITEM_NAME + " VARCHAR(255) , "
                + Util.ITEM_QTE + " INTEGER , "
                + Util.ITEM_COLOR + " VARCHAR(255) , "
                + Util.ITEM_SIZE + " INTEGER )";

        db.execSQL(CREATE_TABLE_QUERY);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String DROP_TABLE = String.valueOf(R.string.drop_table);
        db.execSQL(DROP_TABLE , new String[]{Util.DATABASE_NAME});
        onCreate(db);

    }

    // add item to db

    public void addItemToDb(ItemModel item)
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Util.ITEM_NAME,item.getItemName());
        values.put(Util.ITEM_QTE ,item.getItemQte());
        values.put(Util.ITEM_COLOR , item.getItemColor());
        values.put(Util.ITEM_SIZE, item.getItemSize());

        db.insert(Util.TABLE_NAME,null,values);
        db.close();

    }

    // get item from db

    public ItemModel getItemFromDb(int itemId)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        ItemModel item ;

        Cursor cursor = db.query(Util.TABLE_NAME,
                new String[]{Util.ITEM_ID,Util.ITEM_NAME,Util.ITEM_QTE,Util.ITEM_COLOR,Util.ITEM_SIZE},
                Util.ITEM_ID + "=?",new String[]{String.valueOf(itemId)},
                null,null,null);

        if(cursor != null)
            cursor.moveToFirst();

        item = new ItemModel();
        item.setItemId(Integer.parseInt(cursor.getString(0)));
        item.setItemName(cursor.getString(1));
        item.setItemQte(Integer.parseInt(cursor.getString(2)));
        item.setItemColor(cursor.getString(3));
        item.setItemSize(Integer.parseInt(cursor.getString(4)));

        return item;

    }

    // get all items

    public List<ItemModel> getAllItem()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        List<ItemModel> items = new ArrayList<>();

        String GET_ALL_ITEM = "SELECT * FROM " + Util.TABLE_NAME;
        Cursor cursor = db.rawQuery(GET_ALL_ITEM,null);
        if(cursor.moveToFirst())
        {
            do{
                ItemModel item = new ItemModel();
                item.setItemId(Integer.parseInt(cursor.getString(0)));
                item.setItemName(cursor.getString(1));
                item.setItemQte(Integer.parseInt(cursor.getString(2)));
                item.setItemColor(cursor.getString(3));
                item.setItemSize(Integer.parseInt(cursor.getString(4)));

                items.add(item);
            }while(cursor.moveToNext());
        }

        return items;

    }

    public int upgradeItem(ItemModel item)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(Util.ITEM_NAME,item.getItemName());
        values.put(Util.ITEM_QTE ,item.getItemQte());
        values.put(Util.ITEM_COLOR , item.getItemColor());
        values.put(Util.ITEM_SIZE, item.getItemSize());


        return db.update(Util.TABLE_NAME,values,Util.ITEM_ID + "=?",
                new String[]{String.valueOf(item.getItemId())});

    }

// delete item
    public void deleteItem(ItemModel item)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(Util.TABLE_NAME,Util.ITEM_ID + "=?",
                new String[]{String.valueOf(item.getItemId())});
    }
// get count

    public int getCoun()
    {
        String GET_COUNT = "SELECT * FROM " + Util.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_COUNT,null);
        return cursor.getCount();
    }


}
