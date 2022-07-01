package pe.edu.autonoma.proyecto_ua_1.ui.registrarAsistencia;


import android.os.Bundle;

import androidx.annotation.NonNull;
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
import pe.edu.autonoma.proyecto_ua_1.models.DocenteBean;
import pe.edu.autonoma.proyecto_ua_1.models.DocenteDAO;

public class RegistrarAsistenciaFragment extends Fragment implements View.OnClickListener{

    ArrayList<String> opcDocente;
    ArrayAdapter<String> adapter;
    DocenteDAO objDocenteDAO;
    DocenteBean objDocenteBean;
    //elementos de la tabla
    Spinner spnDocente;
    EditText etCodDocente;
    Button btnBuscarDocente;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_registrar_asistencia, container, false);
        objDocenteDAO = new DocenteDAO(getContext());
        objDocenteDAO.open();
        //elementos del fragmento grafico
        spnDocente = root.findViewById(R.id.spn_docente);
        llenarSpinner();
        etCodDocente = root.findViewById(R.id.et_coddocente);
        btnBuscarDocente = root.findViewById(R.id.btn_buscarDocente);
        btnBuscarDocente.setOnClickListener(this);


        return root;
    }
    public void llenarSpinner(){
        opcDocente = new ArrayList<>();
        opcDocente = llenarListaDocentes();
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, opcDocente);
        spnDocente.setAdapter(adapter);
    }
    public ArrayList<String> llenarListaDocentes(){
        ArrayList acum = new ArrayList<String>();
        ArrayList<DocenteBean> list;
        acum.add("-Seleccionar un docente-");
        list = objDocenteDAO.listadoPersonas();
        for (DocenteBean obj:list) {
            acum.add(obj.getNombre()+ obj.getApellido());
        }
        return acum;
    }
    public void buscarDocente(String codDocente){
        Boolean valor = false;
        ArrayList<DocenteBean> list;
        list = objDocenteDAO.listadoPersonas();
        for (DocenteBean obj:list) {

            if (codDocente.equals(obj.getDni())){
                valor = true;
                Toast.makeText(getContext(),"Si se encuentra el codigo", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view == btnBuscarDocente){
            String strCodDocente = etCodDocente.getText().toString();
            buscarDocente(strCodDocente);

        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


}