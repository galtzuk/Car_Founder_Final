package com.example.car_founder_final;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class RegisterFragment extends Fragment {

    TextInputEditText etRegFullName;
    TextInputEditText etRegEmail;
    TextInputEditText etRegPassword;
    Button btnSignUpSubmit;
    FirebaseAuth mAuth;


    private static final String KEY_FULLNAME = "fullName";
    private static final String KEY_EMAIL = "email";

    FirebaseFirestore usersdb = FirebaseFirestore.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        etRegFullName = view.findViewById(R.id.etRegFullName);
        etRegEmail = view.findViewById(R.id.etRegEmail);
        etRegPassword = view.findViewById(R.id.etRegPass);
        btnSignUpSubmit = view.findViewById(R.id.btnSignUpSubmit);

        mAuth = FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        btnSignUpSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.createUserWithEmailAndPassword(etRegEmail.getText().toString(), etRegPassword.getText().toString())
                        .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if (etRegFullName.getText() != null) {

                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information


                                        User user = new User(etRegFullName.getText().toString(),etRegEmail.getText().toString());

                                        // Save the user or perform any other operations

                                        Map<String, Object> userMap = new HashMap<>();
                                        userMap.put(KEY_FULLNAME, user.getFullName());
                                        userMap.put(KEY_EMAIL, user.getEmail());

                                        usersdb.collection("usersdb").document(mAuth.getCurrentUser().getUid()).set(userMap);

                                        Intent intent = new Intent(requireContext(), MainActivity.class);
                                        intent.putExtra("user", user);
                                        startActivity(intent);
                                        getActivity().finish();

                                    }
                                    else
                                    {
                                        Toast.makeText(requireContext(), "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
            }
        });
    }

}