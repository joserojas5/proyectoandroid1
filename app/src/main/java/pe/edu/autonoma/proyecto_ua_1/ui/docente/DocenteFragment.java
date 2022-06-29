package pe.edu.autonoma.proyecto_ua_1.ui.docente;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import pe.edu.autonoma.proyecto_ua_1.R;

import pe.edu.autonoma.proyecto_ua_1.databinding.FragmentDocenteBinding;
import pe.edu.autonoma.proyecto_ua_1.models.DocenteBean;
import pe.edu.autonoma.proyecto_ua_1.models.DocenteDAO;

public class DocenteFragment extends Fragment{

    private FragmentDocenteBinding binding;
    ArrayAdapter<String> arrayAdapter;
    ListView lstDocentes;
    DocenteDAO objDocenteDAO;
    ArrayList<String> acum;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        DocenteViewModel docenteViewModel =
                new ViewModelProvider(this).get(DocenteViewModel.class);

        binding = FragmentDocenteBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        lstDocentes = root.findViewById(R.id.lst_docentes);
        objDocenteDAO = new DocenteDAO(getContext());
        objDocenteDAO.open();
        llenarListaDocentes();
        arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, acum);
        lstDocentes.setAdapter(arrayAdapter);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void llenarListaDocentes(){
        acum = new ArrayList<>();
        ArrayList<DocenteBean> list;

        list = objDocenteDAO.listadoPersonas();
        for (DocenteBean obj:list) {
            acum.add("Nombre: " + obj.getNombre()+ obj.getApellido() +"\n"+
                    "DNI: "+obj.getDni() + "\n" +
                    "Edad: "+ obj.getEdad() + "\n"+
                    "Sexo: " + obj.getSexo() + "\n" +
                    "Telefono: " + obj.getTelefono() + "\n"+
                    "Correo: " + obj.getCorreo());


        }


    }






}