package com.example.car_founder_final;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class parts extends Fragment {
private GridView gridViewitems;
private CarItemAdapter carItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view = inflater.inflate(R.layout.fragment_parts, container, false);
       gridViewitems = view.findViewById(R.id.grid_view);
       return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<CarItem> carItems = new ArrayList<>();
        carItems.add(new CarItem(R.drawable.engine,"engine"));
        carItemAdapter = new CarItemAdapter(requireContext(),carItems);
        gridViewitems.setAdapter(carItemAdapter);


    }
}