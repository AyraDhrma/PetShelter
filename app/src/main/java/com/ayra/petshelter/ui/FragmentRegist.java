package com.ayra.petshelter.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.ayra.petshelter.R;
import com.ayra.petshelter.entity.Model;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentRegist extends Fragment {

    private EditText fName, fEmail, fPhone;
    private DatabaseReference databaseReference;
    private Model model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_regist, container, false);

        fName = view.findViewById(R.id.edit_name);
        fEmail = view.findViewById(R.id.edit_email);
        fPhone = view.findViewById(R.id.edit_phone);
        Button btnRegister = view.findViewById(R.id.Register);

        model = new Model();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("voluntir");

        btnRegister.setOnClickListener(view1 -> {
            String full_name = fName.getText().toString().trim();
            String email_name = fEmail.getText().toString().trim();
            String phone_name = fPhone.getText().toString().trim();
            String email_pattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
            String phone_pattern = "^[+]?[0-9]{10,13}$";
            if (full_name.isEmpty() || email_name.isEmpty() || phone_name.isEmpty()) {
                Toast.makeText(getActivity().getApplicationContext(), "Field cannot be empty", Toast.LENGTH_SHORT).show();
            } else {
                if (email_name.matches(email_pattern)) {
                    if (phone_name.matches(phone_pattern)) {

                        model.setUser_name(full_name);
                        model.setEmail(email_name);
                        model.setPhone(phone_name);

                        databaseReference.push().setValue(model);
                        Toast.makeText(getActivity(), "Insert Success", Toast.LENGTH_LONG).show();
                        fName.setText(null);
                        fEmail.setText(null);
                        fPhone.setText(null);

                        Intent intent = new Intent(getActivity(), RegisterSuccess.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Invalid Your Phone", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

}
