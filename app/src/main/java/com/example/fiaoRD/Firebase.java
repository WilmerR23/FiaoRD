package com.example.fiaoRD;

import android.os.Handler;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModel;

import com.example.fiaoRD.Interfaces.OnFragmentInteractionListener;
import com.example.fiaoRD.Interfaces.OnFragmentListener;
import com.example.fiaoRD.Models.BaseModel;
import com.example.fiaoRD.ui.BaseFragment;
import com.example.fiaoRD.ui.Utility;
import com.example.fiaoRD.ui.register.RegisterViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Firebase {

    private DatabaseReference mDatabase;
    private String CadenaOutput = "";

    public DatabaseReference getInstance () {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance().getReference();
        }

        return mDatabase;
    }

    public String Save(BaseModel vm, String id, String parent, final String mensaje) {
        getInstance().child(parent).child(id).setValue(vm, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                CadenaOutput = databaseError != null ? "Ha ocurrido un error: " + databaseError.getMessage() : mensaje;
            }
        });
        return CadenaOutput;
    }

    public void Obtener(String id, String child, final Class clase, final OnFragmentInteractionListener dataFound, final BaseFragment listener) {

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

    public void ObtenerTodos(String id, String child, final Class clase, final OnFragmentInteractionListener dataFound, final BaseFragment listener) {

        Query query = getInstance().child(child);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    List<Object> lista = new ArrayList<Object>();
                    for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                        lista.add(postSnapshot.getValue(clase));
                    }
                    dataFound.onDataTodosFound(lista,listener);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                String error = databaseError.getMessage();
            }
        });
    }


    public String UpdateKey(List<String> parent, String key, Object value, @Nullable final String mensaje) {

        DatabaseReference dref = getInstance();

        for(int x = 0; x < parent.size(); x++) {
            dref = dref.child(parent.get(x));
        }

        dref.child(key).setValue(value, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                CadenaOutput = databaseError != null ? "Ha ocurrido un error: " + databaseError.getMessage() : mensaje;
            }
        });
        return CadenaOutput;
    }

    public void ObtenerChildCount(String child, final BaseFragment baseFragment) {
        final Query query = getInstance().child(child);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int size = 0;
                if (dataSnapshot.exists()) {
                    size = (int) dataSnapshot.getChildrenCount();
                }

                baseFragment.receiveChildrenCount(size);

                query.removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                String error = databaseError.getMessage();
            }
        });

    }

    public void ObtenerPorFiltro(String codigo, String id, String child, final Class clase, final BaseFragment listener) {
        final Query query = getInstance().child(child).orderByChild(codigo).equalTo(id);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    listener.receiveObtenerPorFiltroData(dataSnapshot.getValue(clase));
                } else {
                    listener.receiveObtenerPorFiltroData(null);
                }

                query.removeEventListener(this);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                String error = databaseError.getMessage();
            }
        });
    }
}
