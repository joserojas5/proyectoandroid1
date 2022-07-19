package pe.edu.autonoma.proyecto_ua_1.ui.registrarAsistencia;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import pe.edu.autonoma.proyecto_ua_1.R;
import pe.edu.autonoma.proyecto_ua_1.models.AsistenciaBean;
import pe.edu.autonoma.proyecto_ua_1.models.AsistenciaDAO;
import pe.edu.autonoma.proyecto_ua_1.models.DocenteBean;
import pe.edu.autonoma.proyecto_ua_1.models.DocenteDAO;

public class RegistrarAsistenciaFragment extends Fragment implements View.OnClickListener{


    DocenteDAO objDocenteDAO;
    DocenteBean objDocenteBean;
    AsistenciaBean objAsistenciaBean;
    AsistenciaDAO objAsistenciaDAO;
    //elementos de la tabla
    Spinner spnDocente;
    EditText etCodDocente;
    Button btnBuscarDocente;
    EditText txtTiempo;
    TextView tvNombreApellidoDocente;
    Button btnRegistrarAsistencia;
    Boolean busquedaDocente = false;

    private int id;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_registrar_asistencia, container, false);
        objDocenteDAO = new DocenteDAO(getContext());
        objDocenteDAO.open();
        objAsistenciaDAO = new AsistenciaDAO(getContext());
        objAsistenciaDAO.open();
        //elementos del fragmento grafico
        txtTiempo = root.findViewById(R.id.txt_tiempo);
        tvNombreApellidoDocente = root.findViewById(R.id.tv_nombre_apellido);

        etCodDocente = root.findViewById(R.id.et_coddocente);
        btnBuscarDocente = root.findViewById(R.id.btn_buscarDocente);
        btnRegistrarAsistencia = root.findViewById(R.id.btn_registrarAsistencia);
        btnBuscarDocente.setOnClickListener(this);
        btnRegistrarAsistencia.setOnClickListener(this);
        /**
         sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
         String currentDateandTime = sdf.format(new Date());*/
        DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy, HH:mm:ss");
        String date = df.format(Calendar.getInstance().getTime());
        txtTiempo.setText(date);



        return root;
    }


    public void buscarDocente(String codDocente){
        ArrayList<DocenteBean> list;
        list = objDocenteDAO.listadoPersonas();
        for (DocenteBean obj:list) {

            if (codDocente.equals(obj.getDni())){
                tvNombreApellidoDocente.setText(obj.getNombre() +" "+ obj.getApellido());
                id = obj.getId();
                busquedaDocente = true;
            }
        }
    }


    @Override
    public void onClick(View view) {
        if (view == btnBuscarDocente){
            String strCodDocente = etCodDocente.getText().toString();
            buscarDocente(strCodDocente);
        }
        if(view == btnRegistrarAsistencia && busquedaDocente == true){
            registarDBDocente();
        }
    }
    public void registarDBDocente() {
        String tiempo = txtTiempo.getText().toString();
        int idDocente = id;

        long estado = 0;

        objAsistenciaBean = new AsistenciaBean();
        objAsistenciaBean.setIdDocente(idDocente);
        objAsistenciaBean.setFechaAsistencia(tiempo);

        estado = objAsistenciaDAO.insertar(objAsistenciaBean);//falla de conexion


        if (estado == 0) {
            Toast.makeText(getContext(), "Registro No Insertado", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Registro Insertado", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onDestroyView() {
        objAsistenciaDAO.close();
        objDocenteDAO.close();
        super.onDestroyView();
    }


}