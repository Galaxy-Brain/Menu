package com.matthias.groupproject;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class LunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lunch_list_item);

        TextView lunchTitle = findViewById(R.id.lunch_title);
        lunchTitle.setText(getIntent().getStringExtra("lTitle"));

        TextView lunchPrice = findViewById(R.id.lunch_price);
        lunchPrice.setText(getIntent().getStringExtra("lPrice"));


        TextView lunchDescription = findViewById(R.id.lunch_description);
        lunchDescription.setText(getIntent().getStringExtra("lDescription"));

        ImageView lunchImage = findViewById(R.id.lunch_image);
        Glide.with(this).load(getIntent().getIntExtra("lImage", 0)).into(lunchImage);

    }
}
