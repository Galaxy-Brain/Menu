package com.matthias.groupproject;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class BreakfastActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.breakfast_list_item);

        TextView breakfastTitle = findViewById(R.id.breakfast_title);
        breakfastTitle.setText(getIntent().getStringExtra("bTitle"));

        TextView breakfastPrice = findViewById(R.id.breakfast_price);
        breakfastPrice.setText(getIntent().getStringExtra("bPrice"));


        TextView breakfastDescription = findViewById(R.id.breakfast_description);
        breakfastDescription.setText(getIntent().getStringExtra("bDescription"));

        ImageView breakfastImage = findViewById(R.id.breakfast_image);
        Glide.with(this).load(getIntent().getIntExtra("bImage", 0)).into(breakfastImage);

    }
}
