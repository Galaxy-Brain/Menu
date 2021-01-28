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

public class BreakfastAdapter extends RecyclerView.Adapter<BreakfastAdapter.ViewHolder> {
    private ArrayList<Breakfast> breakfastData;
    private Context myContext;

    BreakfastAdapter(ArrayList<Breakfast> mBreakfastData, Context context){
        //initialize the objects
        this.myContext= context;
        this.breakfastData= mBreakfastData;

    }

    @NonNull
    @Override
    public BreakfastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(myContext).inflate(R.layout.breakfast_list_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BreakfastAdapter.ViewHolder holder, int position) {

        Breakfast currentBreakfast= breakfastData.get(position);
        holder.bindTo(currentBreakfast);

    }

    @Override
    public int getItemCount() {
        return breakfastData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView myBreakfastImage;
        private TextView myBreakfastTitle;
        private TextView myBreakfastPrice;
        private TextView myBreakfastDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myBreakfastImage = itemView.findViewById(R.id.breakfast_image);
            myBreakfastTitle = itemView.findViewById(R.id.breakfast_title);
            myBreakfastPrice = itemView.findViewById(R.id.breakfast_price);
            myBreakfastDescription = itemView.findViewById(R.id.breakfast_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int breakfastPosition = getAdapterPosition();
                    Breakfast currentBreakfast = breakfastData.get(breakfastPosition);
                    if(breakfastPosition>=0){
                        Intent breakfastIntent = new Intent(myContext, BreakfastActivity.class);
                        breakfastIntent.putExtra("bTitle", currentBreakfast.getBreakfastTitle());
                        breakfastIntent.putExtra("bPrice", currentBreakfast.getBreakfastPrice());
                        breakfastIntent.putExtra("bImage", currentBreakfast.getBreakfastImage());
                        breakfastIntent.putExtra("bDescription", currentBreakfast.getBreakfastDescription());

                        myContext.startActivity(breakfastIntent);
                    }else{
                        Toast.makeText(myContext, "create an activity for the breakfast", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        //Step 6 Create a method to bind the current view with data (Image, title and description)

        public void bindTo(Breakfast currentBreakfast) {

            Glide.with(myContext).load(currentBreakfast.getBreakfastImage()).into(myBreakfastImage);
            myBreakfastTitle.setText(currentBreakfast.getBreakfastTitle());
            myBreakfastPrice.setText(currentBreakfast.getBreakfastPrice());
            myBreakfastDescription.setText(currentBreakfast.getBreakfastDescription());
        }
    }
}

