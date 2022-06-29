package pe.edu.autonoma.proyecto_ua_1.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import pe.edu.autonoma.proyecto_ua_1.R;
import pe.edu.autonoma.proyecto_ua_1.ui.docente.DocenteFragment;
//import pe.edu.autonoma.proyecto_ua_1.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment implements AdapterView.OnItemClickListener {

    //private FragmentHomeBinding binding;
    ListView mLvOpciones;
    ArrayAdapter<String> mLeadsAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //HomeViewModel homeViewModel =
          //      new ViewModelProvider(this).get(HomeViewModel.class);

       // binding = FragmentHomeBinding.inflate(inflater, container, false);
       // binding.getRoot();
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        mLvOpciones = root.findViewById(R.id.lst_opc);
        String [] opciones = {"Listar Docentes", "Registrar Docente"};
        mLeadsAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, opciones);
        mLvOpciones.setAdapter(mLeadsAdapter);
        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        FragmentManager miManejador = getActivity().getSupportFragmentManager();
        FragmentTransaction miTransaccion = miManejador.beginTransaction();

        switch (position){
            case 0:
               // miTransaccion.replace(position, R.layout.fragment_docente);
                miTransaccion.commit();
                break;
            case 1:
                break;
        }
    }
}