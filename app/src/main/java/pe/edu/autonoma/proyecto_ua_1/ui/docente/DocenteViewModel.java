package pe.edu.autonoma.proyecto_ua_1.ui.docente;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DocenteViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final MutableLiveData<String> mText;

    public DocenteViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is docente fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}