package com.example.fiaoRDITSC.ui.movimientos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.fiaoRDITSC.Interfaces.OnListViewListener;
import com.example.fiaoRDITSC.R;
import com.example.fiaoRDITSC.Utility.list_adapter;
import com.example.fiaoRDITSC.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

public class VerMovimientos  extends BaseFragment implements OnListViewListener {


    public static VerMovimientos newInstance() {
        return new VerMovimientos();
    }

    private ListView list_view;
    private ArrayList<String> labels;
    public static CrearPrestamoViewModel vm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }

        view = inflater.inflate(R.layout.fragment_ver_movimientos, container, false);

        list_view = (ListView) view.findViewById(R.id.movimientosLv);

        ArrayList lista = new ArrayList<String>();
        lista.add("MovimientosPrestamos");
        lista.add(VerPrestamos.vm.getId());
        _Firebase.ObtenerTodosOrdenados(lista, RealizarPagoViewModel.class,"fechaPago",mListener,this);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

    @Override
    public void receiveDataTodosOrdenados(List<Object> obj) {

        obj = orderData(obj);

        refreshListView(obj);
    }

    public List<Object> orderData(List<Object> obj) {
     for (int x = 0; x < obj.size() - 1; x++) {

         for (int c = 0; c < obj.size() - 1 - x; c++) {

             RealizarPagoViewModel vm = (RealizarPagoViewModel) obj.get(c);
             RealizarPagoViewModel vm2 = (RealizarPagoViewModel) obj.get(c+1);
             RealizarPagoViewModel aux;
             if (vm2.getFechaPago() > vm.getFechaPago()) {
                 aux = vm2;
                 obj.set(c+1,vm);
                 obj.set(c,aux);
             }
         }
     }
        return obj;
    }

    public void refreshListView(List<Object> obj) {
        labels = new ArrayList<String>();
        for (int i = 0; i < obj.size(); i++) {
            RealizarPagoViewModel vm = (RealizarPagoViewModel) obj.get(i);
            labels.add(vm.getDescripcion());
        }

        list_adapter adapter = new list_adapter(this.getActivityContext(), labels,false,false, R.layout.row_list_view_default,this);
        list_view.setAdapter(adapter);
    }


    @Override
    public void OnAdd(int itemSelectec) {
//        vm = lista.get(itemSelectec);
//        mListener.onCallFragmentKey(this,R.id.nav_host_fragment,RealizarPago.newInstance(),"Realizar Pago");
    }

    @Override
    public void OnDelete(int itemSelected) {

    }

    @Override
    public void OnSelect(int itemSelected) {

    }
}
