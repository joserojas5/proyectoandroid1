package pe.edu.autonoma.proyecto_ua_1.ui.registrarAsistencia;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class RegistrarAsistenciaViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<String> mText;

    public RegistrarAsistenciaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is registrar asistencia fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}