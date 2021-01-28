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

public class DinnerAdapter extends RecyclerView.Adapter<DinnerAdapter.ViewHolder> {
    private ArrayList<Dinner> dinnerData;
    private Context myContext;

    DinnerAdapter(ArrayList<Dinner> mDinnerData, Context context){
        //initialize the objects
        this.myContext= context;
        this.dinnerData= mDinnerData;

    }

    @NonNull
    @Override
    public DinnerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DinnerAdapter.ViewHolder(LayoutInflater.from(myContext).inflate(R.layout.dinner_list_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DinnerAdapter.ViewHolder holder, int position) {

        Dinner currentDinner= dinnerData.get(position);
        holder.bindTo(currentDinner);

    }

    @Override
    public int getItemCount() {
        return dinnerData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView myDinnerImage;
        private TextView myDinnerTitle;
        private TextView myDinnerPrice;
        private TextView myDinnerDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            myDinnerImage = itemView.findViewById(R.id.dinner_image);
            myDinnerTitle = itemView.findViewById(R.id.dinner_title);
            myDinnerPrice = itemView.findViewById(R.id.dinner_price);
            myDinnerDescription = itemView.findViewById(R.id.dinner_description);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int dinnerPosition = getAdapterPosition();
                    Dinner currentDinner = dinnerData.get(dinnerPosition);
                    if(dinnerPosition>=0){
                        Intent dinnerIntent = new Intent(myContext, DinnerActivity.class);
                        dinnerIntent.putExtra("lTitle", currentDinner.getDinnerTitle());
                        dinnerIntent.putExtra("lPrice", currentDinner.getDinnerPrice());
                        dinnerIntent.putExtra("lImage", currentDinner.getDinnerImage());
                        dinnerIntent.putExtra("lDescription", currentDinner.getDinnerDescription());

                        myContext.startActivity(dinnerIntent);
                    }else{
                        Toast.makeText(myContext, "create an activity for the lunch", Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }

        //Step 6 Create a method to bind the current view with data (Image, title and description)

        public void bindTo(Dinner currentDinner) {

            Glide.with(myContext).load(currentDinner.getDinnerImage()).into(myDinnerImage);
            myDinnerTitle.setText(currentDinner.getDinnerTitle());
            myDinnerPrice.setText(currentDinner.getDinnerPrice());
            myDinnerDescription.setText(currentDinner.getDinnerDescription());
        }
    }
}
