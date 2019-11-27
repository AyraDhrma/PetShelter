package com.ayra.petshelter.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ayra.petshelter.R;

public class RegisterSuccess extends AppCompatActivity {

    Button btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_success);

        btn_home = findViewById(R.id.btn_home);

        btn_home.setOnClickListener(view -> {
            Intent intent = new Intent(RegisterSuccess.this, MainActivity.class);
            finish();
            startActivity(intent);
        });
    }
}
