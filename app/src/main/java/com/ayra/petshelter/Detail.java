package com.ayra.petshelter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class Detail extends AppCompatActivity {

    TextView mName, mBreed, mDesc;
    ImageView mImageIv;
    Button btn_adopt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        mName = findViewById(R.id.tv_name);
        mBreed = findViewById(R.id.tv_breed);
        mDesc = findViewById(R.id.tv_desc);
        mImageIv = findViewById(R.id.iv_image);
        btn_adopt = findViewById(R.id.button_adopt);

        byte[] bytes = getIntent().getByteArrayExtra("url");
        final String name = getIntent().getStringExtra("name");
        String breed = getIntent().getStringExtra("breed");
        String desc = getIntent().getStringExtra("desc");
        final Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        mName.setText(name);
        mBreed.setText(breed);
        mDesc.setText(desc);
        mImageIv.setImageBitmap(bm);

        btn_adopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable mDrawable = mImageIv.getDrawable();
                Bitmap mBitmap = ((BitmapDrawable) mDrawable).getBitmap();
                final String name_adopt = name;
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] bytes = stream.toByteArray();
                Intent intent = new Intent(Detail.this, Adopt.class);
                intent.putExtra("name", name_adopt);
                intent.putExtra("url", bytes);
                startActivity(intent);
            }
        });
    }


}
