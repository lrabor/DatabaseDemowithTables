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

public class MyDBHandler extends SQLiteOpenHelper {

    // define database variables
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastname";

    // define sqlite database variable
    private SQLiteDatabase database;

    // constructor
    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // responsible for creating a table for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_CONTACTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_FIRSTNAME + " TEXT NOT NULL, " +
                COLUMN_LASTNAME + " TEXT NOT NULL" + ");";
        db.execSQL(query);

    }

    // responsible for making updates to an existing table
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // delete the entire table if it exists
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // recreate the table with the new properties
        onCreate(db);
    }

    public MyDBHandler open() throws SQLException {
        database = getWritableDatabase();   // get reference to the database
        return this;
    }

    // Add new row to the database
    public void addContact(Contacts contact) {
        // content values is built into Android that allows you to add several values in one statement
        ContentValues values = new ContentValues();
        values.put(COLUMN_FIRSTNAME, contact.get_firstname());
        values.put(COLUMN_LASTNAME, contact.get_lastname());

        // call the open method to get reference to the database
        open();
        database.insert(TABLE_CONTACTS, null, values);

        // once your are done with database, then close it out to give memory back
        close();
    }

    // Delete a contact from the database
    public void deleteContact (String firstname, String lastname) {

        // call the open method to get reference to the database
         open();
        // delete the row with matching firstname and lastname
        database.execSQL("DELETE FROM " + TABLE_CONTACTS + " WHERE " + COLUMN_FIRSTNAME + "=\"" + firstname + "\"" +
                " AND " + COLUMN_LASTNAME + "=\"" + lastname + "\";");
    }

    // Delete all contacts from the database
    public void deleteAllContacts () {

        // call the open method to get reference to the database
        open();
        // delete all records from the database
        database.execSQL("DELETE FROM " + TABLE_CONTACTS + ";");
    }

    public Cursor readEntry() {
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
}
