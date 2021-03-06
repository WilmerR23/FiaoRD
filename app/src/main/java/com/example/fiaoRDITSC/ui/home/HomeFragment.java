package com.example.fiaoRDITSC.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import com.example.fiaoRDITSC.Interfaces.OnListViewListener;
import com.example.fiaoRDITSC.R;
import com.example.fiaoRDITSC.Utility.MessageDialog;
import com.example.fiaoRDITSC.Utility.list_adapter;
import com.example.fiaoRDITSC.ui.BaseFragment;
import com.example.fiaoRDITSC.ui.PrestamistaColmaderoCodigo.PrestamistaColmaderoCodigoViewModel;
import com.example.fiaoRDITSC.ui.Utility;
import com.example.fiaoRDITSC.ui.cliente.ClienteFragment;
import com.example.fiaoRDITSC.ui.login.LoginFragment;
import com.example.fiaoRDITSC.ui.movimientos.CrearPrestamo;
import com.example.fiaoRDITSC.ui.movimientos.CrearPrestamoViewModel;
import com.example.fiaoRDITSC.ui.movimientos.VerPrestamos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HomeFragment extends BaseFragment implements OnListViewListener, View.OnClickListener {

    private HomeViewModel homeViewModel;
    private ArrayList<String> labels;
    private List<Object> registros;
    private ListView list_view;
    private List<PrestamistaColmaderoCodigoViewModel> prestamistaClientesList;
    private Button btnBuscarCliente, btnAtrasados;
    private EditText etNombreCliente;
    private View root;
    private ConstraintLayout home;
    public static String key;
    public static boolean isColmadero;
    public static PrestamistaColmaderoCodigoViewModel model;
    private PrestamistaColmaderoCodigoViewModel currentIterator;
    private List<Object> listaCliente;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        if (container != null) {
            container.removeAllViews();
        }

        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        root = inflater.inflate(R.layout.fragment_home, container, false);
        home = root.findViewById(R.id.constraintLayout_Home);

        home.setVisibility(View.INVISIBLE);
        _Firebase.ExisteKey(LoginFragment.id,"CodigoUsuarioPrestamista",this);


        return root;
    }

    public void colmaderoInit() {
        mListener.setCurrentFragment(this,0);
        home.setVisibility(View.VISIBLE);
        list_view = (ListView) root.findViewById(R.id.clientesLv);
        btnBuscarCliente = root.findViewById(R.id.btnBuscarCliente);
        btnAtrasados = root.findViewById(R.id.btnAtrasados);
        etNombreCliente = root.findViewById(R.id.etNombreCliente);
        labels = new ArrayList<String>();
        registros = new ArrayList<>();

        ArrayList lista = new ArrayList<String>();
        lista.add("PrestamistaClientes");
        lista.add(LoginFragment.id);

        btnBuscarCliente.setOnClickListener(this);
        btnAtrasados.setOnClickListener(this);
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
    }

    @Override
    public void receiveExisteKey(boolean valor) {
        isColmadero = valor;
        if (valor) {
            colmaderoInit();
        } else {
            mListener.getMenu().findItem(R.id.nav_home).setVisible(false);
            mListener.onCallFragmentKey(this,R.id.nav_host_fragment,ClienteFragment.newInstance(),"Mi perfil");
        }
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

        list_adapter adapter = new list_adapter(this.getActivityContext(), labels, true,true,false,R.layout.row_list_view, this);
        list_view.setAdapter(adapter);
    }

    public void setKey(int itemSelected) {
        home.setVisibility(View.INVISIBLE);
        model = prestamistaClientesList.get(itemSelected);
        key = model.getId();
    }

    @Override
    public void OnAdd(int itemSelected) {
        VerPrestamos.vm = null;
        setKey(itemSelected);
        mListener.onCallFragmentKey(this,R.id.nav_host_fragment, CrearPrestamo.newInstance(),"Crear Prestamo");
       // mListener.onMakeToast("Boton que tendra la funcion de mostrar pantalla de prestamo",Toast.LENGTH_LONG);
    }

    @Override
    public void OnDelete(final int itemSelected) {
        mListener.onMakeDialog(this,
                new MessageDialog("Importante",
                        "¿ Desea eliminar este registro ?",
                        "Confirmar",
                        "Cancerlar"), itemSelected);
    }

    @Override
    public void DialogPositiveCallback(Object parameter) {
        PrestamistaColmaderoCodigoViewModel model = prestamistaClientesList.get((int) parameter);
        String key = model.getId();
        ArrayList<String> lista = new ArrayList<>();
        lista.add("PrestamistaClientes");
        lista.add(LoginFragment.id);
        lista.add(key);
        _Firebase.Remove(lista);
        Toast.makeText(this.getActivityContext(),"Registro eliminado satisfactoriamente",Toast.LENGTH_LONG).show();
        _Firebase.ObtenerTodos(lista, PrestamistaColmaderoCodigoViewModel.class,mListener,this);
    }

    @Override
    public void OnSelect(final int itemSelected) {
        setKey(itemSelected);
        mListener.onCallFragmentKey(this,R.id.nav_host_fragment,ClienteFragment.newInstance(),"Perfil Cliente");

        //mListener.onMakeToast("Ha elegido el registro en la fila" + itemSelected + 1,Toast.LENGTH_LONG);
    }

    @Override
    public String ToggleBackGroundColor(int position) {
//        CrearPrestamoViewModel cpvm = prestamistaClientesList.get(position);
            String color = "#33FF0000";
        return color;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBuscarCliente:
                String texto = etNombreCliente.getText().toString();
                List<Object> lista = new ArrayList<>();
                if (texto.length() > 0) {
                    for(PrestamistaColmaderoCodigoViewModel item : prestamistaClientesList){
                        if (item.getDescripcion().toLowerCase().contains(texto.toLowerCase())) {
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
            case R.id.btnAtrasados:
                etNombreCliente.setText("");
                buscarClientesAtrasados();
                break;
        }
    }

    public void buscarClientesAtrasados() {

        listaCliente = new ArrayList<>();
        ArrayList<String> lista = new ArrayList<>();

            for(int x = 0; x < prestamistaClientesList.size(); x++) {
                lista.add("Prestamos");
                currentIterator = prestamistaClientesList.get(x);
                String id = prestamistaClientesList.get(x).getId();
                lista.add(id);

                boolean ultimaIteraccion = (prestamistaClientesList.size() - 1) == x;
               _Firebase.ObtenerTodosPorFiltro(lista,"prestamista", LoginFragment.id,CrearPrestamoViewModel.class,mListener,this,ultimaIteraccion, currentIterator);
               lista = new ArrayList<>();
            }
    }

    @Override
    public void receiveDataTodosPorFiltro(List<Object> obj, boolean ultimaIteraccion, PrestamistaColmaderoCodigoViewModel currentIterator) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        for (int x = 0; x < obj.size(); x++){
                CrearPrestamoViewModel cpvm = (CrearPrestamoViewModel) obj.get(x);
                int montoADeber = cpvm.getTotal() - cpvm.getTotalPagado();
                if (montoADeber > 0) {
                    Date fechaInicio = format.parse(cpvm.getFechaInicio());
                    Date fechaActual = new Date();
                    long diff = fechaActual.getTime() - fechaInicio.getTime();
                    long dias = diff / (24 * 60 * 60 * 1000);

                    int cantidadPeriodosPasados = (int) dias / cpvm.getTipoPago();
                    int cantidadTotalPagadoSegunFechaActual = cantidadPeriodosPasados * cpvm.getCuota();

                    if (cantidadTotalPagadoSegunFechaActual > cpvm.getTotalPagado()) {
                        listaCliente.add(currentIterator);
                        break;
                    }
                }
        }

        if (ultimaIteraccion) {
            refreshListView(listaCliente);
        }

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}