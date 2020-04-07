package com.example.fiaoRDITSC.Utility;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.fiaoRDITSC.Interfaces.OnEditTextDatePicker;
import com.example.fiaoRDITSC.Interfaces.OnFragmentInteractionListener;
import com.example.fiaoRDITSC.ui.BaseFragment;

import java.util.Calendar;
import java.util.TimeZone;

public class MyEditTextDatePicker  implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private EditText _editText;
    private int _day;
    private int _month;
    private int _birthYear;
    private Context _context;
    private OnEditTextDatePicker _etdp;

    public EditText get_editText() {
        return _editText;
    }

    public MyEditTextDatePicker(OnEditTextDatePicker etdp, View v, OnFragmentInteractionListener listener, int editTextViewID)
    {
        this._etdp = etdp;
        this._editText = (EditText)v.findViewById(editTextViewID);
        this._editText.setOnClickListener(this);
        this._context = listener.getActivityContext();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        _birthYear = year;
        _month = monthOfYear;
        _day = dayOfMonth;
        updateDisplay();
    }
    @Override
    public void onClick(View v) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

        DatePickerDialog dialog = new DatePickerDialog(_context, this,
                calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        dialog.show();

    }

    // updates the date in the birth date EditText
    private void updateDisplay() {
       this._etdp.OnUpdateEditText(new StringBuilder().append(_day).append("/").append(_month + 1).append("/").append(_birthYear).append(" "));
    }
}