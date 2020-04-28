package com.example.fiaoRDITSC.ui.PrestamistaCliente;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.fiaoRDITSC.Interfaces.OnListViewListener;
import com.example.fiaoRDITSC.R;
import com.example.fiaoRDITSC.Utility.list_adapter;
import com.example.fiaoRDITSC.ui.BaseFragment;
import com.example.fiaoRDITSC.ui.PrestamistaColmaderoCodigo.PrestamistaColmaderoCodigo;
import com.example.fiaoRDITSC.ui.login.LoginFragment;
import com.example.fiaoRDITSC.ui.register.RegisterViewModel;

import java.util.ArrayList;
import java.util.List;

public class PrestamistaCliente extends BaseFragment implements OnListViewListener {

    private PrestamistaClienteViewModel mViewModel;
    private ListView list_view;
    private ArrayList<String> labels;
    private Button btnPrestamista;

    public static PrestamistaCliente newInstance() {
        return new PrestamistaCliente();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }

        View v = inflater.inflate(R.layout.prestamista_cliente_fragment, container, false);

        list_view = (ListView) v.findViewById(R.id.prestamistaLv);
        btnPrestamista = v.findViewById(R.id.btnAddPrestamista);

        ArrayList lista = new ArrayList<String>();
        lista.add("ClientePrestamistas");
        lista.add(LoginFragment.id);
        _Firebase.ObtenerTodos(lista, PrestamistaClienteViewModel.class, mListener,this);

        final PrestamistaCliente instance = this;
        btnPrestamista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onCallFragmentKey(instance,R.id.nav_host_fragment,PrestamistaColmaderoCodigo.newInstance(),"Añadir prestamista");
            }
        });

        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(PrestamistaClienteViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void receiveDataTodos(List<Object> objs) {
        labels = new ArrayList<>();
        for(int x = 0; x < objs.size(); x++) {
            PrestamistaClienteViewModel vm = (PrestamistaClienteViewModel) objs.get(x);
            labels.add(vm.getPrestamista());
        }

        list_adapter adapter = new list_adapter(this.getActivityContext(), labels,false,false, false, R.layout.row_list_view_default,this);
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

    @Override
    public String ToggleBackGroundColor(int position) {
        return null;
    }
}
