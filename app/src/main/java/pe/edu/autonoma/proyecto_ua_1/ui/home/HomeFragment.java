package pe.edu.autonoma.proyecto_ua_1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import pe.edu.autonoma.proyecto_ua_1.R;

public class HomeFragment extends Fragment {

    //private FragmentHomeBinding binding;
    ListView mLvOpciones;
    ArrayAdapter<String> mLeadsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mLvOpciones = root.findViewById(R.id.lst_opc);
        String [] opciones = {"Listar Docentes", "Registrar Docente"};
        mLeadsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, opciones);
        mLvOpciones.setAdapter(mLeadsAdapter);
        return root;
    }


/*
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blank, container, false);
    }*/

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }


}