package com.example.fiaoRDITSC.Utility;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.fiaoRDITSC.Interfaces.OnListViewListener;
import com.example.fiaoRDITSC.R;

import java.util.ArrayList;


public class list_adapter extends ArrayAdapter<String>  {

    private Context context;
    private ArrayList<String> labels;
    private OnListViewListener _Listener;
    private boolean _show_right_button, _show_left_button,_showBackGroundColor;
    private int _layout;

    public list_adapter(Context context, ArrayList<String> labels, boolean show_left_button,boolean show_right_button, boolean showBackGroundColor, int layout, OnListViewListener Listener) {
        super(context, R.layout.row_list_view, labels);
        this.context = context;
        this.labels = labels;
        this._Listener = Listener;
        this._show_right_button = show_right_button;
        this._show_left_button = show_left_button;
        this._layout = layout;
        this._showBackGroundColor = showBackGroundColor;
    }

    static class ViewHolder {
        public TextView textoContenido;
        public ImageButton btnLeft;
        public ImageButton btnRight;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View rowView = convertView;

        if (rowView == null) {

            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            rowView = inflater.inflate(_layout, null, true);
            holder = new ViewHolder();
            holder.textoContenido = (TextView) rowView.findViewById(R.id.textView1);

            holder.btnLeft = (ImageButton) rowView.findViewById(R.id.imageButton1);
            holder.btnRight = (ImageButton) rowView.findViewById(R.id.imageButton2);

            if (!_show_left_button && holder.btnLeft != null) {
                holder.btnLeft.setVisibility(View.GONE);
            }

            if (!_show_right_button && holder.btnRight != null) {
            holder.btnRight.setVisibility(View.GONE);
            }


            rowView.setTag(holder);
        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        holder.textoContenido.setText(labels.get(position));

        if (_showBackGroundColor) {
           String color = _Listener.ToggleBackGroundColor(position);
           LinearLayout ll = rowView.findViewById(R.id.RelativeLayout1);
            ll.setBackgroundColor(Color.parseColor(color));
        }

        holder.textoContenido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _Listener.OnSelect(position);
            }
        });

        if (_show_left_button) {
            holder.btnLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _Listener.OnAdd(position);
                }
            });
        }

        if (_show_right_button) {
            holder.btnRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    _Listener.OnDelete(position);
                }
            });
        }
        return rowView;
    }
}
