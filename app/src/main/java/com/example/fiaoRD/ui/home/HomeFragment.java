package com.example.fiaoRD.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.fiaoRD.Interfaces.OnListViewListener;
import com.example.fiaoRD.MainActivity;
import com.example.fiaoRD.R;
import com.example.fiaoRD.Utility.list_adapter;
import com.example.fiaoRD.ui.BaseFragment;
import com.example.fiaoRD.ui.PrestamistaColmaderoCodigo.PrestamistaColmaderoCodigoViewModel;
import com.example.fiaoRD.ui.cliente.ClienteFragment;
import com.example.fiaoRD.ui.home.HomeViewModel;
import com.example.fiaoRD.ui.login.LoginFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends BaseFragment implements OnListViewListener {

    private HomeViewModel homeViewModel;
    private ArrayList<String> labels;
    private ListView list_view;
    private List<PrestamistaColmaderoCodigoViewModel> prestamistaClientesList;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        list_view = (ListView) root.findViewById(R.id.clientesLv);
        labels = new ArrayList<String>();

        ArrayList lista = new ArrayList<String>();
        lista.add("PrestamistaClientes");
        lista.add(LoginFragment.id);

      //  list_view.setOnItemClickListener(this);

        _Firebase.ObtenerTodos(lista, PrestamistaColmaderoCodigoViewModel.class,mListener,this);
        return root;
    }

    @Override
    public void receiveDataTodos(List<Object> obj) {
        refreshListView(obj);
    }

    public void refreshListView(List<Object> obj) {
        prestamistaClientesList = new ArrayList<>();
        for (int i = 0; i < obj.size(); i++) {
            PrestamistaColmaderoCodigoViewModel vm = (PrestamistaColmaderoCodigoViewModel) obj.get(i);
            prestamistaClientesList.add(vm);
            labels.add(vm.getDescripcion());
        }

        list_adapter adapter = new list_adapter(this.getActivityContext(), labels,this);
        list_view.setAdapter(adapter);
        labels = new ArrayList<String>();
    }

    @Override
    public void OnAdd(int itemSelected) {

    }

    @Override
    public void OnDelete(final int itemSelected) {
        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this.getActivityContext());
        dialogo1.setTitle("Importante");
        dialogo1.setMessage("¿ Desea eliminar este registro ?");
        dialogo1.setCancelable(true);
        final HomeFragment hF = this;
        dialogo1.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                ArrayList<String> lista = new ArrayList<>();
                PrestamistaColmaderoCodigoViewModel model = prestamistaClientesList.get(itemSelected);
                String key = model.getId();
                lista.add("PrestamistaClientes");
                lista.add(LoginFragment.id);
                lista.add(key);
                _Firebase.Remove(lista);
                Toast.makeText(hF.getActivityContext(),"Registro eliminado satisfactoriamente",Toast.LENGTH_LONG).show();
                _Firebase.ObtenerTodos(lista, PrestamistaColmaderoCodigoViewModel.class,mListener,hF);
            }
        });
        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogo1, int id) {
                dialogo1.cancel();
            }
        });
        dialogo1.show();
    }

    @Override
    public void OnSelect(final int itemSelected) {
        mListener.onCallFragment(ClienteFragment.newInstance());

    }
}