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
public class DinnerFragment extends Fragment {

    private RecyclerView dinnerRecyclerView;
    private ArrayList<Dinner> dinnerData;
    private DinnerAdapter dinnerAdapter;

    public DinnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_dinner, container, false);
        /*2. Initialize recyclerView*/
        dinnerRecyclerView =rootView.findViewById(R.id.recycler_dinner);
        //4. set layout for the recycler view
        dinnerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //5. Initialize the array list that will contain data
        dinnerData = new ArrayList<>();
        //6. Initialize the new recipe adapter
        dinnerAdapter = new DinnerAdapter(dinnerData, getContext());
        //7. set the adapter
        dinnerRecyclerView.setAdapter(dinnerAdapter);
        //8. Get data
        initializeData();
        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT|ItemTouchHelper.DOWN|ItemTouchHelper.UP,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = viewHolder.getAdapterPosition();
                Collections.swap(dinnerData,from,to);
                dinnerAdapter.notifyItemMoved(from, to);

                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                dinnerData.remove(viewHolder.getAdapterPosition());
                dinnerAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
            }
        })     ;
        helper.attachToRecyclerView(dinnerRecyclerView);
        return rootView;
    }

    private void initializeData() {
        //8.1 get the data created in the resource file: Strings.xml
        String[] dinnerTitles = getResources().getStringArray(R.array.dinner_title);
        String[] dinnerPrice = getResources().getStringArray(R.array.dinner_price);
        String[] dinnerDescription = getResources().getStringArray(R.array.dinner_description);
        TypedArray dinnerImages = getResources().obtainTypedArray(R.array.dinner_images);
        //8.2 Clear Existing data to avoid duplication
        dinnerData.clear();
        //8.3 Create an array list of dessert recipes with title description and images
        for (int i=0; i<dinnerTitles.length; i++){
            dinnerData.add(new Dinner(dinnerImages.getResourceId(i,  0),dinnerTitles[i], dinnerPrice[i],dinnerDescription[i]));
        }
        //8.4 clean up data in the typed array
        dinnerImages.recycle();
        //8.5 notify adapter of the change in the data set``
        dinnerAdapter.notifyDataSetChanged();
    }

}
