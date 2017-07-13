package com.rabor.databasedemowithtables;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // define widget variables
    private EditText firstNameEditText, lastNameEditText;
    private Button addButton;
    private Button deleteButton;
    private Button showAllButton;
    private Button deleteAllButton;

    // define database handler
    MyDBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate the MyDBHandler constructor
        dbHandler = new MyDBHandler(this);

        // get reference to the widgets
        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        addButton = (Button) findViewById(R.id.addButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);
        showAllButton = (Button) findViewById(R.id.showAllButton);
        deleteAllButton = (Button) findViewById(R.id.deleteAllButton);
    }

    // add a contact to the database
    public void addClick(View view) {
        Contacts contacts = new Contacts(firstNameEditText.getText().toString(), lastNameEditText.getText().toString());
        dbHandler.addContact(contacts);

        // clear the text fields
        firstNameEditText.setText("");
        lastNameEditText.setText("");
        // request focus on the firstname
        firstNameEditText.requestFocus();
    }

    // delete items
    public void deleteClick(View view) {
        String firstname = firstNameEditText.getText().toString();
        String lastname = lastNameEditText.getText().toString();
        dbHandler.deleteContact(firstname, lastname);

        // clear the text fields
        firstNameEditText.setText("");
        lastNameEditText.setText("");
        // request focus on the firstname
        firstNameEditText.requestFocus();
    }

    public void displayClick(View view) {
        // open up a new screen
        Intent i = new Intent(this, DisplayListActivity.class);
        startActivity(i);
    }

    // delete all items
    public void deleteAllClick(View view) {
        dbHandler.deleteAllContacts();

        // clear the text fields
        firstNameEditText.setText("");
        lastNameEditText.setText("");
        // request focus on the firstname
        firstNameEditText.requestFocus();
    }
}
