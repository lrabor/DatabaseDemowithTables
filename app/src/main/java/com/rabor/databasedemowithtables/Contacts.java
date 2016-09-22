/*
 * Copyright (c) 2016. All Rights Reserved.
 */

package com.rabor.databasedemowithtables;

public class Contacts {

    private int _id;
    private String firstname;
    private String lastname;

    // default constructor
    public Contacts() {

    }

    public Contacts(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public int get_id() {
        return _id;
    }

    public String get_firstname() {
        return firstname;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_firstname(String firstname) {
        this.firstname = firstname;
    }

    public String get_lastname() {
        return lastname;
    }

    public void set_lastname(String lastname) {
        this.lastname = lastname;
    }
}