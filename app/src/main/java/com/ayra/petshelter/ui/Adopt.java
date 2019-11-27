package com.ayra.petshelter.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ayra.petshelter.R;

public class Adopt extends AppCompatActivity implements View.OnClickListener{

    TextView tv_name;
    ImageView iv_image;
    Button bMail, bCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopt);

        tv_name = findViewById(R.id.tv_name_adopt);
        iv_image = findViewById(R.id.iv_image_adopt);
        bMail = findViewById(R.id.btn_mail);
        bCall = findViewById(R.id.btn_call);
        bMail.setOnClickListener(this);
        bCall.setOnClickListener(this);


        byte[] bytes = getIntent().getByteArrayExtra("url");
        String name = getIntent().getStringExtra("name");
        Bitmap bm = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        iv_image.setImageBitmap(bm);
        tv_name.setText(name);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_mail:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "aryadharma5007@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hello, I want to adopt");
                startActivity(Intent.createChooser(emailIntent, null));
            break;
            case R.id.btn_call:
                String phoneNumber = "081283333333";
                Intent DialPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                startActivity(DialPhone);
                break;
        }
    }
}
