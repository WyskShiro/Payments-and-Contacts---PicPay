package will.shiro.desafiopicpay.util.di;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;
import javax.inject.Provider;

public class ViewModelFactory<T extends ViewModel> implements ViewModelProvider.Factory {

    Provider<T> viewModelProvider;

    @Inject
    public ViewModelFactory(Provider<T> viewModelProvider) {
        this.viewModelProvider = viewModelProvider;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) viewModelProvider.get();
    }
}
