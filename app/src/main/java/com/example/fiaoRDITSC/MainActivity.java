package com.example.fiaoRDITSC;

import android.os.Bundle;

import android.util.Log;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.fiaoRDITSC.Models.BaseModel;
import com.example.fiaoRDITSC.ui.BaseFragment;
import com.example.fiaoRDITSC.ui.PrestamistaCliente.PrestamistaCliente;
import com.example.fiaoRDITSC.ui.cliente.ClienteFragment;
import com.example.fiaoRDITSC.ui.home.HomeFragment;
import com.example.fiaoRDITSC.ui.login.LoginFragment;
import com.example.fiaoRDITSC.ui.movimientos.CrearPrestamo;
import com.example.fiaoRDITSC.ui.movimientos.VerMovimientos;
import com.example.fiaoRDITSC.ui.movimientos.VerPrestamos;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.widget.Toolbar;
import android.view.Menu;

import java.io.Serializable;

public class MainActivity extends BaseActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        vm =  getIntent().getSerializableExtra("BaseModel");

        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_perfil,R.id.nav_prestamista)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        nav_Menu = navigationView.getMenu();
        setupDrawerContent(navigationView);
    }

    private void setupDrawerContent(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public void selectDrawerItem(MenuItem menuItem) {
        switch(menuItem.getItemId()) {
            case R.id.nav_home:
                this.onCallFragmentKey(this.getCurrentFragment(),R.id.nav_host_fragment, HomeFragment.newInstance(),"Clientes");
                break;
            case R.id.nav_perfil:
                HomeFragment.key = null;
                this.onCallFragmentKey(this.getCurrentFragment(),R.id.nav_host_fragment, ClienteFragment.newInstance(), "Mi perfil");
                break;
            case R.id.nav_prestamista:
                this.onCallFragmentKey(this.getCurrentFragment(),R.id.nav_host_fragment, PrestamistaCliente.newInstance(), "Mis Prestamistas");
                break;
        }
        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        drawer.closeDrawers();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cerrarSesion:
                HomeFragment.key = null;
                HomeFragment.model = null;
                VerPrestamos.vm = null;
                VerMovimientos.vm = null;
                LoginFragment.id = null;
                LoginFragment.UserVm = null;
                LoginFragment.cad = null;
                LoginFragment.Descripcion = null;
                LoginFragment.NombreUsuario = null;
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        Class cs = getCurrentFragment().getClass();
        BaseFragment bF = getCurrentFragment();

        if ((cs == ClienteFragment.class || VerPrestamos.vm == null) && HomeFragment.isColmadero) {
            this.onCallFragmentKey(getCurrentFragment(),R.id.nav_host_fragment,HomeFragment.newInstance(),"Clientes");
        } else if (cs == HomeFragment.class || (cs == ClienteFragment.class && !HomeFragment.isColmadero)) {
            finish();
        } else {
            getSupportActionBar().setTitle(bF.prev_Fragment.title);
            setCurrentFragment(bF.prev_Fragment);
            super.onBackPressed();
        }
    }

}
