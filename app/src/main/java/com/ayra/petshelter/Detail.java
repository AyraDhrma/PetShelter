package com.ayra.petshelter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Detail extends AppCompatActivity {

    TextView mName, mBreed, mDesc;
    ImageView mImageIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mName = findViewById(R.id.tv_name);
        mBreed = findViewById(R.id.tv_breed);
        mDesc = findViewById(R.id.tv_desc);
        mImageIv = findViewById(R.id.iv_image);

        byte[] bytes = getIntent().getByteArrayExtra("url");
        String name = getIntent().getStringExtra("name");
        String breed = getIntent().getStringExtra("breed");
        String desc = getIntent().getStringExtra("desc");
        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        mName.setText(name);
        mBreed.setText(breed);
        mDesc.setText(desc);
        mImageIv.setImageBitmap(bm);
    }


}
