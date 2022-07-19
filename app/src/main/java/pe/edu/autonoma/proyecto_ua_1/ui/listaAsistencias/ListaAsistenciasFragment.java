package pe.edu.autonoma.proyecto_ua_1.ui.listaAsistencias;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import pe.edu.autonoma.proyecto_ua_1.R;
import pe.edu.autonoma.proyecto_ua_1.models.AsistenciaBean;
import pe.edu.autonoma.proyecto_ua_1.models.AsistenciaDAO;
import pe.edu.autonoma.proyecto_ua_1.models.DocenteBean;
import pe.edu.autonoma.proyecto_ua_1.models.DocenteDAO;


public class ListaAsistenciasFragment extends Fragment {

    ArrayAdapter<String> arrayAdapter;
    ListView lstAsistenciasDocentes;
    AsistenciaDAO objAsistenciaDAO;
    DocenteDAO objDocenteDAO;
    ArrayList<String> acum;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lista_asistencias, container, false);
        lstAsistenciasDocentes = root.findViewById(R.id.lst_asistencias_docentes);
        objAsistenciaDAO = new AsistenciaDAO(getContext());
        objAsistenciaDAO.open();
        objDocenteDAO = new DocenteDAO(getContext());
        objDocenteDAO.open();

        llenarListaDocentes();
        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, acum);
        lstAsistenciasDocentes.setAdapter(arrayAdapter);

        return root;
    }
    public void llenarListaDocentes(){
              acum = new ArrayList<>();
              ArrayList<AsistenciaBean> list;
              ArrayList<DocenteBean> list2;
              list = objAsistenciaDAO.listadoAsistencia();
              list2 = objDocenteDAO.listadoPersonas();
              for (int i = list.size()-1; i>=0 ; i--) {
                  AsistenciaBean obj = list.get(i);
                  for (DocenteBean obj2:list2) {
                      if(obj2.getId() == obj.getIdDocente()){
                          acum.add("id: " + obj.getId() + "\n" +
                                  "DNI: " + obj2.getDni() + "\n"+
                                  "nombre de docente: " + obj2.getNombre()+" "+obj2.getApellido()+"\n" +
                                  "Fecha de registro: " + obj.getFechaAsistencia());
                      }
                  }
              }
              /*
              for (AsistenciaBean obj:list) {
                  for (DocenteBean obj2:list2) {
                      if(obj2.getId() == obj.getIdDocente()){
                          acum.add("id: " + obj.getId() + "\n" +
                                  "DNI: " + obj2.getDni() + "\n"+
                                  "nombre de docente: " + obj2.getNombre()+" "+obj2.getApellido()+"\n" +
                                  "Fecha de registro: " + obj.getFechaAsistencia());
                      }
                  }
             }*/
     }
    @Override
     public void onDestroyView() {
        objAsistenciaDAO.close();
        objDocenteDAO.close();
        super.onDestroyView();
     }

}