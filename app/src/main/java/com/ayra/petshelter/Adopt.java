package com.ayra.petshelter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Adopt extends AppCompatActivity {

    TextView tv_name;
    ImageView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt);

        tv_name = findViewById(R.id.tv_name_adopt);
        iv_image = findViewById(R.id.iv_image_adopt);

        byte[] bytes = getIntent().getByteArrayExtra("url");
        String name = getIntent().getStringExtra("name");
        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        iv_image.setImageBitmap(bm);
        tv_name.setText(name);
    }
}
