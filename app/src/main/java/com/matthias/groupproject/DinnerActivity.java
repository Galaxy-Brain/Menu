package com.matthias.groupproject;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class DinnerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dinner_list_item);

        TextView dinnerTitle = findViewById(R.id.dinner_title);
        dinnerTitle.setText(getIntent().getStringExtra("dTitle"));

        TextView dinnerPrice = findViewById(R.id.dinner_price);
        dinnerPrice.setText(getIntent().getStringExtra("dPrice"));


        TextView dinnerDescription = findViewById(R.id.dinner_description);
        dinnerDescription.setText(getIntent().getStringExtra("dDescription"));

        ImageView dinnerImage = findViewById(R.id.dinner_image);
        Glide.with(this).load(getIntent().getIntExtra("dImage", 0)).into(dinnerImage);

    }
}
