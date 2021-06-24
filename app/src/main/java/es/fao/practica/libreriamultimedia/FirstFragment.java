package es.fao.practica.libreriamultimedia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class FirstFragment extends Fragment {
    private TextInputLayout textInputLayoutUseEmail,textInputLayoutUsePass;
    private TextInputEditText userIdEmailET, userPassET;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textInputLayoutUseEmail = view.findViewById(R.id.textInputEmail);
        textInputLayoutUsePass = view.findViewById(R.id.textInputPassword);
        userIdEmailET= view.findViewById(R.id.editTextPassword);
        userPassET = view.findViewById(R.id.editTextEmail);



        view.findViewById(R.id.acceptarbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=userIdEmailET.toString();

                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        view.findViewById(R.id.cancelarbtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userIdEmailET.setText("");
                userPassET.setText("");

            }
        });
    }
}