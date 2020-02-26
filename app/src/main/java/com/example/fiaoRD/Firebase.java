package com.example.fiaoRD;

import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import com.example.fiaoRD.Interfaces.OnFragmentInteractionListener;
import com.example.fiaoRD.Interfaces.OnFragmentListener;
import com.example.fiaoRD.ui.Utility;
import com.example.fiaoRD.ui.register.RegisterViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Firebase {

    private DatabaseReference mDatabase;
    private String CadenaOutput = "";

    public DatabaseReference getInstance () {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance().getReference();
        }

        return mDatabase;
    }

    public String Save(ViewModel vm, String id,String parent, final String mensaje) {
        getInstance().child(parent).child(Utility.encodeForFirebaseKey(id)).setValue(vm, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                CadenaOutput = databaseError != null ? "Ha ocurrido un error: " + databaseError.getMessage() : mensaje;
            }
        });
        return CadenaOutput;
    }

    public void Obtener(String id, String child, final Class clase, final OnFragmentInteractionListener dataFound, final OnFragmentListener listener) {

        Query query = getInstance().child(child).child(id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    dataFound.onDataFound(dataSnapshot.getValue(clase),listener);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                    String error = databaseError.getMessage();
            }
        });
    }


    public String UpdateKey(String id, String parent, String key, Object value, @Nullable final String mensaje) {
        getInstance().child(parent).child(Utility.encodeForFirebaseKey(id)).child(key).setValue(value, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                CadenaOutput = databaseError != null ? "Ha ocurrido un error: " + databaseError.getMessage() : mensaje;
            }
        });
        return CadenaOutput;
    }
}
