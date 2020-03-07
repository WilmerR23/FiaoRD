package com.example.fiaoRDITSC;
import android.os.Bundle;
import com.example.fiaoRDITSC.ui.login.LoginFragment;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Aqui se conecta la vista xml con el archivo activity
        setContentView(R.layout.login_activity);

        //Aqui llamo a este metodo para mover el contenido del login fragment al contenido del R.layout.login_activity
        if (savedInstanceState == null) {
           getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, LoginFragment.
                            newInstance()).commit();
        }
    }
}
