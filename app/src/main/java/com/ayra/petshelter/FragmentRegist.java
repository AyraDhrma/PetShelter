package com.ayra.petshelter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentRegist extends Fragment {

    EditText fName, fEmail, fPhone;
    Button btn_regist;
    DatabaseReference databaseReference;
    Model getset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fragment_regist, container, false);

        fName = view.findViewById(R.id.Fullname);
        fEmail = view.findViewById(R.id.Email);
        fPhone = view.findViewById(R.id.Phone);
        btn_regist = view.findViewById(R.id.Register);

        getset = new Model();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("voluntir");

        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                            Toast.makeText(getActivity().getApplicationContext(), "Valid email address", Toast.LENGTH_SHORT).show();

                            getset.setUser_name(full_name);
                            getset.setEmail(email_name);
                            getset.setTelepon(phone_name);

                            databaseReference.push().setValue(getset);
                            Toast.makeText(getActivity(), "Insert Success", Toast.LENGTH_LONG).show();
                            fName.setText(null);
                            fEmail.setText(null);
                            fPhone.setText(null);
                        }else {
                            Toast.makeText(getActivity().getApplicationContext(), "Invalid Your Phone", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getActivity().getApplicationContext(), "Invalid email address", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


        return view;
    }

}
