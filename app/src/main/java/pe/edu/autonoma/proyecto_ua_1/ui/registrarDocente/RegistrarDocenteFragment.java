package pe.edu.autonoma.proyecto_ua_1.ui.registrarDocente;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pe.edu.autonoma.proyecto_ua_1.R;
import pe.edu.autonoma.proyecto_ua_1.databinding.FragmentHomeBinding;
import pe.edu.autonoma.proyecto_ua_1.databinding.FragmentRegistrarDocenteBinding;
import pe.edu.autonoma.proyecto_ua_1.models.DocenteBean;
import pe.edu.autonoma.proyecto_ua_1.models.DocenteDAO;
import pe.edu.autonoma.proyecto_ua_1.ui.home.HomeViewModel;

public class RegistrarDocenteFragment extends Fragment implements View.OnClickListener{

    private FragmentRegistrarDocenteBinding binding;
    DocenteBean objDocenteBean;
    DocenteDAO objDocenteDAO;

    String valorSpinner;
    ArrayList<String> opcSexo;
    ArrayAdapter<String> adapter;
    Spinner spnSexo;
    EditText etNombre, etApellido, etDni, etEdad, etTelefono, etCorreo;
    Button btnRegistrar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RegistrarDocenteViewModel registrarDocenteViewModel =
                new ViewModelProvider(this).get(RegistrarDocenteViewModel.class);

        binding = FragmentRegistrarDocenteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        spnSexo = root.findViewById(R.id.spn_sexo);
        opcSexo = new ArrayList<>();
        opcSexo.add("-Selecciona genero-");
        opcSexo.add("Femenino");
        opcSexo.add("Masculino");
        adapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1, opcSexo);
        spnSexo.setAdapter(adapter);

        //EditText
        etNombre = (EditText) root.findViewById(R.id.et_nombre);
        etApellido = (EditText) root.findViewById(R.id.et_apellido);
        etDni = (EditText) root.findViewById(R.id.et_dni);
        etEdad = (EditText) root.findViewById(R.id.et_edad);
        etTelefono = (EditText) root.findViewById(R.id.et_telefono);
        etCorreo = (EditText) root.findViewById(R.id.et_correo);
        btnRegistrar = (Button) root.findViewById(R.id.btn_registrar);
        btnRegistrar.setOnClickListener(this);
        objDocenteDAO = new DocenteDAO(getContext());
        objDocenteDAO.open();

        return root;
    }


    @Override
    public void onClick(View view) {
        seleccionarSexo();
        if(view == btnRegistrar && valorSpinner != null){
            registarDBDocente();
        }else{
            Toast.makeText(getContext(), "Falta sexo", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void seleccionarSexo(){
        switch (spnSexo.getSelectedItemPosition()){
            case 0:
                valorSpinner = null;
                break;
            case 1:
                valorSpinner = "Femenino";
                break;
            case 2:
                valorSpinner = "Masculino";
                break;

        }
    }
    public void registarDBDocente(){
        seleccionarSexo();
        String nombre = etNombre.getText().toString();
        String apellido = etApellido.getText().toString();
        String edad =etEdad.getText().toString();
        String sexo = valorSpinner;
        String dni = etDni.getText().toString();
        String correo = etCorreo.getText().toString();
        String telefono = etTelefono.getText().toString();
        objDocenteBean = new DocenteBean();
        objDocenteBean.setNombre(nombre);
        objDocenteBean.setApellido(apellido);
        objDocenteBean.setEdad(Integer.parseInt(edad));
        objDocenteBean.setDni(dni);
        objDocenteBean.setSexo(sexo);
        objDocenteBean.setCorreo(correo);
        objDocenteBean.setTelefono(telefono);
        long estado = objDocenteDAO.insertar(objDocenteBean);//falla de conexion
        if (estado == 0) {
            Toast.makeText(getContext(), "Registro No Insertado", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getContext(), "Registro Insertado", Toast.LENGTH_SHORT).show();
            limpiarET();
        }


    }
    public void limpiarET(){
        etNombre.setText("");
        etApellido.setText("");
        etEdad.setText("");
        etDni.setText("");
        etCorreo.setText("");
        etTelefono.setText("");
    }
}