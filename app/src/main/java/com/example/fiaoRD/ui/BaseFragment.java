package com.example.fiaoRD.ui;

import android.content.Context;

import androidx.fragment.app.Fragment;

import com.example.fiaoRD.Interfaces.OnFragmentInteractionListener;

public class BaseFragment extends Fragment {

    protected OnFragmentInteractionListener mListener;

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



}
