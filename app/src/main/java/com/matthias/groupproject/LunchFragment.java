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
public class LunchFragment extends Fragment {
    private RecyclerView lunchRecyclerView;
    private ArrayList<Lunch> lunchData;
    private LunchAdapter lunchAdapter;

    public LunchFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_lunch, container, false);
        /*2. Initialize recyclerView*/
        lunchRecyclerView =rootView.findViewById(R.id.recycler_lunch);
        //4. set layout for the recycler view
        lunchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //5. Initialize the array list that will contain data
        lunchData = new ArrayList<>();
        //6. Initilize the new recipe adapter
        lunchAdapter = new LunchAdapter(lunchData, getContext());
        //7. set the adapter
        lunchRecyclerView.setAdapter(lunchAdapter);
        //8. Get data
        initializeData();
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT|ItemTouchHelper.DOWN|ItemTouchHelper.UP,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = viewHolder.getAdapterPosition();
                Collections.swap(lunchData,from,to);
                lunchAdapter.notifyItemMoved(from, to);

                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                lunchData.remove(viewHolder.getAdapterPosition());
                lunchAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        })     ;
        helper.attachToRecyclerView(lunchRecyclerView);
        return rootView;
    }

    private void initializeData() {
        //8.1 get the data created in the resource file: Strings.xml
        String[] lunchTitles = getResources().getStringArray(R.array.lunch_title);
        String[] lunchPrice = getResources().getStringArray(R.array.lunch_price);
        String[] lunchDescription = getResources().getStringArray(R.array.lunch_description);
        TypedArray lunchImages = getResources().obtainTypedArray(R.array.lunch_images);
        //8.2 Clear Existing data to avoid duplication
        lunchData.clear();
        //8.3 Create an array list of dessert recipes with title description and images
        for (int i=0; i<lunchTitles.length; i++){
            lunchData.add(new Lunch(lunchImages.getResourceId(i,  0),lunchTitles[i], lunchPrice[i],lunchDescription[i]));
        }
        //8.4 clean up data in the typed array
        lunchImages.recycle();
        //8.5 notify adapter of the change in the data set``
        lunchAdapter.notifyDataSetChanged();
    }

}