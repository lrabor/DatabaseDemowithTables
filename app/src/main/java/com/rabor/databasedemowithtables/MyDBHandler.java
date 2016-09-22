/*
 * Copyright (c) 2016. All Rights Reserved.
 */

package com.rabor.databasedemowithtables;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastname";

    private SQLiteDatabase database;

    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_CONTACTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRSTNAME + " TEXT NOT NULL, " +
                COLUMN_LASTNAME + " TEXT NOT NULL" + ");";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    public MyDBHandler open() throws SQLException {
        database = getWritableDatabase();
        return this;
    }

 /*   public void close() {
        database.close();
    }*/

    // Add new row to the database
    public void addProduct(Contacts product) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, product.get_firstname());
        values.put(COLUMN_LASTNAME, product.get_lastname());
        SQLiteDatabase db =  getWritableDatabase();
        db.insert(TABLE_CONTACTS, null, values);

        close();
    }

    // Delete a product from the database
    public void deleteProduct (String firstname, String lastname) {

        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_CONTACTS + " WHERE " + COLUMN_FIRSTNAME + "=\"" + firstname + "\"" +
                " AND " + COLUMN_LASTNAME + "=\"" + lastname + "\";");
    }

    public Cursor readEntry() {
        // TODO Auto-generated method stub
        String[] allColumns = new String[] {
                COLUMN_ID, COLUMN_FIRSTNAME,
                COLUMN_LASTNAME
        };

        Cursor c = database.query(TABLE_CONTACTS, allColumns, null, null, null,
                null, null);

        if (c != null) {
            c.moveToFirst();
        }
        return c;

    }

    // print out the database as a string
    public List<Contacts> databaseToString() {
        List<Contacts> contacts = new ArrayList<Contacts>();
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_CONTACTS + " WHERE 1";

        // cursor point to a location in your results
        Cursor c = db.rawQuery(query, null);
        // move to the first row in your results
        c.moveToFirst();

        while(!c.isAfterLast()) {
            Contacts contact = cursorToContact(c);
            contacts.add(contact);
            c.moveToNext();
        }

        db.close();
        //return dbString;
        return contacts;
     }

    private Contacts cursorToContact(Cursor c) {
        Contacts product = new Contacts();
        product.set_id(c.getInt(0));
        product.set_firstname(c.getString(1));
        product.set_lastname(c.getString(2));
        return product;
    }

}
