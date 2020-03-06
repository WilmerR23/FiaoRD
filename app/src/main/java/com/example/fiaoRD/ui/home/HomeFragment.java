package com.example.fiaoRD.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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

public class HomeFragment extends BaseFragment implements OnListViewListener, View.OnClickListener {

    private HomeViewModel homeViewModel;
    private ArrayList<String> labels;
    private List<Object> registros;
    private ListView list_view;
    private List<PrestamistaColmaderoCodigoViewModel> prestamistaClientesList;
    private Button btnBuscarCliente;
    private EditText etNombreCliente;

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        list_view = (ListView) root.findViewById(R.id.clientesLv);
        btnBuscarCliente = root.findViewById(R.id.btnBuscarCliente);
        etNombreCliente = root.findViewById(R.id.etNombreCliente);
        labels = new ArrayList<String>();
        registros = new ArrayList<>();

        ArrayList lista = new ArrayList<String>();
        lista.add("PrestamistaClientes");
        lista.add(LoginFragment.id);

        btnBuscarCliente.setOnClickListener(this);
      //  list_view.setOnItemClickListener(this);
        etNombreCliente.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                // TODO Auto-generated method stub
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {
                    int length = etNombreCliente.getText().toString().length();
                    if (length == 0) {
                        refreshListView(registros);
                    }
                // TODO Auto-generated method stub
            }
        });

        _Firebase.ObtenerTodos(lista, PrestamistaColmaderoCodigoViewModel.class,mListener,this);
        return root;
    }

    @Override
    public void receiveDataTodos(List<Object> obj) {
        registros = obj;
        refreshListView(obj);
    }

    public void refreshListView(List<Object> obj) {
        prestamistaClientesList = new ArrayList<>();
        labels = new ArrayList<String>();
        for (int i = 0; i < obj.size(); i++) {
            PrestamistaColmaderoCodigoViewModel vm = (PrestamistaColmaderoCodigoViewModel) obj.get(i);
            prestamistaClientesList.add(vm);
            labels.add(vm.getDescripcion());
        }

        list_adapter adapter = new list_adapter(this.getActivityContext(), labels,this);
        list_view.setAdapter(adapter);
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
                PrestamistaColmaderoCodigoViewModel model = prestamistaClientesList.get(itemSelected);
                String key = model.getId();
                ArrayList<String> lista = new ArrayList<>();
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
       // mListener.onCallFragment(ClienteFragment.newInstance());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBuscarCliente:
                String texto = etNombreCliente.getText().toString();
                List<Object> lista = new ArrayList<>();
                if (texto.length() > 0) {
                    for(PrestamistaColmaderoCodigoViewModel item : prestamistaClientesList){
                        if (item.getDescripcion().contains(texto)) {
                            lista.add(item);
                        }
                    }
                    refreshListView(lista);
                   // ArrayList<String> lista = new ArrayList<>();
                   // lista.add("PrestamistaClientes");
                   // lista.add(LoginFragment.id);
                   // _Firebase.ObtenerTodosPorFiltro(lista,"descripcion",texto, PrestamistaColmaderoCodigoViewModel.class,mListener,this);
                } else {
                    mListener.onMakeToast("El filtro por nombre se encuentra vacio",Toast.LENGTH_LONG);
                }
                break;
        }
    }
}