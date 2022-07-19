package pe.edu.autonoma.proyecto_ua_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText etcorreo, etcontraseña;
    private Button btnIngresar;
    public ArrayList<Usuario>listaUsuario;
    private  Usuario objUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        ini();
    }

    public void ini(){
        etcorreo = (EditText) findViewById(R.id.etcorreo);
        etcontraseña = (EditText)  findViewById(R.id.etcontraseña);
        btnIngresar = (Button) findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(this);
    }

    private void llenarUsuarios(){
        listaUsuario = new ArrayList<Usuario>();
        listaUsuario.add(new Usuario("admin", "apellido admin", "admin@admin.com", "12345"));
    }

    private void iniciarSecion(){
        llenarUsuarios();
        String correo= etcorreo.getText().toString();
        String contraseña= etcontraseña.getText().toString();
        boolean usuarioEncontrado = false;

        for (int i =0; i< listaUsuario.size(); i++){

            if (correo.equals(listaUsuario.get(i).getCorreo()) && contraseña.equals(listaUsuario.get(i).getContraseña())){
                Toast.makeText(this, "Bienvenido " +listaUsuario.get(i).getNombre(), Toast.LENGTH_SHORT).show();
                Intent inicio = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(inicio);
            }
            else if (correo.isEmpty() || contraseña.isEmpty()){
                Toast.makeText(this,"lleno los campos", Toast.LENGTH_SHORT).show();
            }
        }

        //if (!usuarioEncontrado){
            //Toast.makeText(this,"el correo o contraseña son incorrectos", Toast.LENGTH_SHORT).show();
        //}
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnIngresar){
            iniciarSecion();
        }

    }
}