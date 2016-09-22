/*
 * Copyright (c) 2016. All Rights Reserved.
 */

package com.rabor.databasedemowithtables;

public class Contacts {

    // define variables for the columns
    private int _id;
    private String firstname;
    private String lastname;

    // default constructor
    public Contacts() {

    }

    // constructor
    public Contacts(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // getters
    public int get_id() {
        return _id;
    }

    public String get_firstname() {
        return firstname;
    }

    public String get_lastname() {
        return lastname;
    }

    // setters
    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_firstname(String firstname) {
        this.firstname = firstname;
    }

    public void set_lastname(String lastname) {
        this.lastname = lastname;
    }
}