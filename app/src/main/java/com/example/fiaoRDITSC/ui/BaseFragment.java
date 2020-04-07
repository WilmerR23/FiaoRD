package com.example.fiaoRDITSC.ui;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.Fragment;

import com.example.fiaoRDITSC.Firebase;
import com.example.fiaoRDITSC.Interfaces.OnFragmentInteractionListener;

import java.util.List;

public abstract class BaseFragment extends Fragment {

    protected OnFragmentInteractionListener mListener;
    protected Firebase _Firebase;
    public View view;

    public OnFragmentInteractionListener getListener() {
        return mListener;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
            _Firebase = new Firebase();
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public Context getActivityContext() {
       return mListener.getActivityContext();
    }

    public void receiveChildrenCount(int count) {}
    public void receiveData(Object obj) {}
    public void receiveDataTodos(List<Object> obj) {}

    public void receiveObtenerPorFiltroData(Object obj) {}

    public void receiveExisteValor(boolean valor) {}
    public void receiveExisteKey(boolean valor) {}

}
