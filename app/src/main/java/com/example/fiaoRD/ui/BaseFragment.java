package com.example.fiaoRD.ui;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.example.fiaoRD.Firebase;
import com.example.fiaoRD.Interfaces.OnFragmentInteractionListener;
import com.example.fiaoRD.Models.BaseModel;

import java.util.List;

public abstract class BaseFragment extends Fragment {

    protected OnFragmentInteractionListener mListener;
    protected Firebase _Firebase;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public void receiveChildrenCount(int count) {}
    public void receiveData(Object obj) {}
    public void receiveDataTodos(List<Object> obj) {}

    public void receiveObtenerPorFiltroData(Object obj) {}


}
