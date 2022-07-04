package com.jvillacorta.fudapp.util;

import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Firebase {
    private FirebaseDatabase database;
    private DatabaseReference reference;

    public Firebase(Context context){
        FirebaseApp.initializeApp(context);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
    }

    public FirebaseDatabase getDatabase() {
        return database;
    }

    public void setDatabase(FirebaseDatabase database) {
        this.database = database;
    }

    public DatabaseReference getReference() {
        return reference;
    }

    public void setReference(DatabaseReference reference) {
        this.reference = reference;
    }
}
