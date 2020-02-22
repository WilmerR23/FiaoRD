package com.example.fiaoRD;

import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebase {

    private DatabaseReference mDatabase;

    public DatabaseReference getInstance () {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance().getReference();
        }

        return mDatabase;
    }

    public int Save(ViewModel vm) {

      //  getInstance()

       return 0;
    }

}
