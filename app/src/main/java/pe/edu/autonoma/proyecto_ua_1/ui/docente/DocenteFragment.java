package pe.edu.autonoma.proyecto_ua_1.ui.docente;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import pe.edu.autonoma.proyecto_ua_1.R;

import pe.edu.autonoma.proyecto_ua_1.models.DocenteBean;
import pe.edu.autonoma.proyecto_ua_1.models.DocenteDAO;

public class DocenteFragment extends Fragment{

    ArrayAdapter<String> arrayAdapter;
    ListView lstDocentes;
    DocenteDAO objDocenteDAO;
    ArrayList<String> acum;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_docente, container, false);

        lstDocentes = root.findViewById(R.id.lst_docentes);
        objDocenteDAO = new DocenteDAO(getContext());
        objDocenteDAO.open();
        llenarListaDocentes();
        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, acum);
        lstDocentes.setAdapter(arrayAdapter);
        lstDocentes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Intent intent = new Intent(getActivity(), OpcionesDocenteActivity.class);
                //startActivity(intent);

            }
        });


        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
    public void llenarListaDocentes(){
        acum = new ArrayList<>();
        ArrayList<DocenteBean> list;
        list = objDocenteDAO.listadoPersonas();
        for (DocenteBean obj:list) {

            acum.add("id: " + obj.getId() + "\n" +
                    "Nombres: " + obj.getNombre()+"\n" +
                    "Apellidos: " + obj.getApellido() +"\n"+
                    "DNI: "+obj.getDni() + "\n" +
                    "Edad: "+ obj.getEdad() + "\n"+
                    "Sexo: " + obj.getSexo() + "\n" +
                    "Telefono: " + obj.getTelefono() + "\n"+
                    "Correo: " + obj.getCorreo());


        }
    }
}