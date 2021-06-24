package es.fao.practica.libreriamultimedia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;
import io.reactivex.observers.DisposableObserver;

public class AuthActivity extends AppCompatActivity {
    private static final String TAG = "AuthActivity";
    private TextInputLayout textInputLayoutUseEmail, textInputLayoutUsePass;
    private TextInputEditText userIdEmailET, userPassET;
    private TextView gotToRegister;
    private MaterialButton loginButton, cancelButton;
    Observable<Boolean> fromObservable;

    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";


    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        validationForm();
    }

    private void validationForm() {

        Observable<String> observableEmail = RxBindingHelper.getObservableFrom(userIdEmailET);
        Observable<String> observablePassword = RxBindingHelper.getObservableFrom(userPassET);

        fromObservable = Observable.combineLatest(observableEmail, observablePassword, new BiFunction<String, String, Boolean>() {

            @Override
            public Boolean apply(String email, String password) throws Exception {
                return isValidForm(email, password);
            }
        });
        fromObservable.subscribe(new DisposableObserver<Boolean>() {
            @Override
            public void onNext(Boolean aBoolean) {
                loginButton.setEnabled(aBoolean);


            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: ", e);

            }

            @Override
            public void onComplete() {
                textInputLayoutUseEmail.setError(null);
                textInputLayoutUsePass.setError(null);

            }
        });

    }

    private Boolean isValidForm(String email, String password) {

        boolean isEmail = Patterns.EMAIL_ADDRESS.matcher(email).matches() && !email.isEmpty();
        if (!isEmail) {
            textInputLayoutUseEmail.setError("El Email no es Valido");
        }
        boolean isPassword = password.length() > 6 && !password.isEmpty();
        if (!isPassword) {
            textInputLayoutUsePass.setError("Contraseña debe tener más de 6 caracteres ");
        } else {
            textInputLayoutUsePass.setError(null);
        }

        return isEmail & isPassword;

    }
}