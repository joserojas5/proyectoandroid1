package pe.edu.autonoma.proyecto_ua_1.ui.registrarAsistencia;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pe.edu.autonoma.proyecto_ua_1.R;
import pe.edu.autonoma.proyecto_ua_1.databinding.FragmentHomeBinding;
import pe.edu.autonoma.proyecto_ua_1.databinding.FragmentRegistrarAsistenciaBinding;
import pe.edu.autonoma.proyecto_ua_1.ui.home.HomeViewModel;

public class RegistrarAsistenciaFragment extends Fragment {

    private FragmentRegistrarAsistenciaBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        RegistrarAsistenciaViewModel registrarAsistenciaViewModel =
                new ViewModelProvider(this).get(RegistrarAsistenciaViewModel.class);

        binding = FragmentRegistrarAsistenciaBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textRegistrarAsistencia;
        registrarAsistenciaViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}