package pe.edu.autonoma.proyecto_ua_1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import pe.edu.autonoma.proyecto_ua_1.LoginActivity;
import pe.edu.autonoma.proyecto_ua_1.R;

public class HomeFragment extends Fragment {
    LoginActivity objLoginActivity = new LoginActivity();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //binding = null;
    }


}