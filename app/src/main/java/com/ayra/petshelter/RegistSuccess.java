package com.ayra.petshelter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistSuccess extends AppCompatActivity {

    Button btn_home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_success);

    btn_home = findViewById(R.id.btn_home);

    btn_home.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(RegistSuccess.this, MainActivity.class);
            finish();
            startActivity(intent);
        }
    });
    }
}
