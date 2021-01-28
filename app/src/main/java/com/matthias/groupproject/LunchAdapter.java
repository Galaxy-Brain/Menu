package com.matthias.groupproject;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class LunchAdapter extends RecyclerView.Adapter<LunchAdapter.ViewHolder> {
    private ArrayList<Lunch> lunchData;
    private Context myContext;

    LunchAdapter(ArrayList<Lunch> mLunchData, Context context){
        //initialize the objects
        this.myContext= context;
        this.lunchData= mLunchData;

    }

    @NonNull
    @Override
    public LunchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LunchAdapter.ViewHolder(LayoutInflater.from(myContext).inflate(R.layout.lunch_list_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LunchAdapter.ViewHolder holder, int position) {

        Lunch currentLunch= lunchData.get(position);
        holder.bindTo(currentLunch);

    }

    @Override
    public int getItemCount() {
        return lunchData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView myLunchImage;
        private TextView myLunchTitle;
        private TextView myLunchPrice;
        private TextView myLunchDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myLunchImage = itemView.findViewById(R.id.lunch_image);
            myLunchTitle = itemView.findViewById(R.id.lunch_title);
            myLunchPrice = itemView.findViewById(R.id.lunch_price);
            myLunchDescription = itemView.findViewById(R.id.lunch_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int lunchPosition = getAdapterPosition();
                    Lunch currentLunch = lunchData.get(lunchPosition);
                    if(lunchPosition>=0){
                        Intent lunchIntent = new Intent(myContext, BreakfastActivity.class);
                        lunchIntent.putExtra("lTitle", currentLunch.getLunchTitle());
                        lunchIntent.putExtra("lPrice", currentLunch.getLunchPrice());
                        lunchIntent.putExtra("lImage", currentLunch.getLunchImage());
                        lunchIntent.putExtra("lDescription", currentLunch.getLunchDescription());

                        myContext.startActivity(lunchIntent);
                    }else{
                        Toast.makeText(myContext, "create an activity for the lunch", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        //Step 6 Create a method to bind the current view with data (Image, title and description)

        public void bindTo(Lunch currentLunch) {

            Glide.with(myContext).load(currentLunch.getLunchImage()).into(myLunchImage);
            myLunchTitle.setText(currentLunch.getLunchTitle());
            myLunchPrice.setText(currentLunch.getLunchPrice());
            myLunchDescription.setText(currentLunch.getLunchDescription());
        }
    }

}
