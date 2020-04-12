package com.example.fiaoRDITSC.ui.movimientos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fiaoRDITSC.Interfaces.OnFragmentInteractionListener;
import com.example.fiaoRDITSC.Interfaces.OnListViewListener;
import com.example.fiaoRDITSC.R;
import com.example.fiaoRDITSC.Utility.list_adapter;
import com.example.fiaoRDITSC.ui.BaseFragment;
import com.example.fiaoRDITSC.ui.home.HomeFragment;
import com.example.fiaoRDITSC.ui.login.LoginFragment;

import java.util.ArrayList;
import java.util.List;

public class VerPrestamos extends BaseFragment implements OnListViewListener {


    public static VerPrestamos newInstance() {
        return new VerPrestamos();
    }

    private ListView list_view;
    private ArrayList<String> labels;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_ver_prestamos, container, false);

        list_view = (ListView) view.findViewById(R.id.prestamosLv);

        ArrayList lista = new ArrayList<String>();
        lista.add("Prestamos");
        lista.add(HomeFragment.key);
        _Firebase.ObtenerTodos(lista, CrearPrestamoViewModel.class,mListener,this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

    @Override
    public void receiveDataTodos(List<Object> obj) {
        refreshListView(obj);
    }

    public void refreshListView(List<Object> obj) {
        labels = new ArrayList<String>();
        for (int i = 0; i < obj.size(); i++) {
            CrearPrestamoViewModel vm = (CrearPrestamoViewModel) obj.get(i);
            labels.add(vm.getDescripcion());
        }

        list_adapter adapter = new list_adapter(this.getActivityContext(), labels,false, R.layout.row_list_view_edit,this);
        list_view.setAdapter(adapter);
    }


    @Override
    public void OnAdd(int itemSelectec) {

    }

    @Override
    public void OnDelete(int itemSelected) {

    }

    @Override
    public void OnSelect(int itemSelected) {

    }
}
