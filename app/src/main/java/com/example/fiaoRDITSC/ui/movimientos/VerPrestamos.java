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
    private List<CrearPrestamoViewModel> lista;
    public static CrearPrestamoViewModel vm;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }

        view = inflater.inflate(R.layout.fragment_ver_prestamos, container, false);

        list_view = (ListView) view.findViewById(R.id.prestamosLv);

        ArrayList lista = new ArrayList<String>();
        lista.add("Prestamos");

        if (HomeFragment.key != null) {
            lista.add(HomeFragment.key);
        } else {
            lista.add(LoginFragment.id);
        }
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
        lista = new ArrayList<>();
        for (int i = 0; i < obj.size(); i++) {
            CrearPrestamoViewModel vm = (CrearPrestamoViewModel) obj.get(i);
            lista.add(vm);
            labels.add(vm.getDescripcion());
        }
        list_adapter adapter = new list_adapter(this.getActivityContext(), labels,true, true, R.layout.row_list_view_edit,this);
        list_view.setAdapter(adapter);
    }


    @Override
    public void OnAdd(int itemSelectec) {
            vm = lista.get(itemSelectec);
            mListener.onCallFragmentKey(this,R.id.nav_host_fragment,RealizarPago.newInstance(),"Realizar Pago");
    }

    @Override
    public void OnDelete(int itemSelected) {
        vm = lista.get(itemSelected);
        mListener.onCallFragmentKey(this,R.id.nav_host_fragment,CrearPrestamo.newInstance(),"Editar prestamo");
    }

    @Override
    public void OnSelect(int itemSelected) {
        vm = lista.get(itemSelected);
        mListener.onCallFragmentKey(this,R.id.nav_host_fragment,VerMovimientos.newInstance(),"Movimientos");
    }
}
