package com.matthias.groupproject;

import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;

/**
 * A simple {@link Fragment} subclass.
 */
public class BreakfastFragment extends Fragment {

    private RecyclerView breakfastRecyclerView;
    private ArrayList<Breakfast> breakfastData;
    private BreakfastAdapter breakfastAdapter;

    public BreakfastFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_breakfast, container, false);
        /*2. Initialize recyclerView*/
        breakfastRecyclerView=rootView.findViewById(R.id.recycler_breakfast);
        //4. set layout for the recycler view
        breakfastRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //5. Initialize the array list that will contain data
        breakfastData= new ArrayList<>();
        //6. Initilize the new recipe adapter
        breakfastAdapter= new BreakfastAdapter(breakfastData, getContext());
        //7. set the adapter
        breakfastRecyclerView.setAdapter(breakfastAdapter);
        //8. Get data
        initializeData();
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT|ItemTouchHelper.DOWN|ItemTouchHelper.UP,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = viewHolder.getAdapterPosition();
                Collections.swap(breakfastData,from,to);
                breakfastAdapter.notifyItemMoved(from, to);

                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                breakfastData.remove(viewHolder.getAdapterPosition());
                breakfastAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        })     ;
        helper.attachToRecyclerView(breakfastRecyclerView);
        return rootView;
    }

    private void initializeData() {
        //8.1 get the data created in the resource file: Strings.xml
        String[] breakfastTitles = getResources().getStringArray(R.array.breakfast_title);
        String[] breakfastPrice = getResources().getStringArray(R.array.breakfast_price);
        String[] breakfastDescription = getResources().getStringArray(R.array.breakfast_description);
        TypedArray breakfastImages = getResources().obtainTypedArray(R.array.breakfast_images);
        //8.2 Clear Existing data to avoid duplication
        breakfastData.clear();
        //8.3 Create an array list of dessert recipes with title description and images
        for (int i=0; i<breakfastTitles.length; i++){
            breakfastData.add(new Breakfast(breakfastImages.getResourceId(i,  0),breakfastTitles[i], breakfastPrice[i],breakfastDescription[i]));
        }
        //8.4 clean up data in the typed array
        breakfastImages.recycle();
        //8.5 notify adapter of the change in the data set``
        breakfastAdapter.notifyDataSetChanged();
    }

}