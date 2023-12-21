package com.example.nafs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.Intent;
import android.widget.ImageButton;


public class activities extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_activities, container, false);

        ImageButton imageButton = rootView.findViewById(R.id.Relaxation);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRelaxation();
            }
        });

        ImageButton imageButton2 = rootView.findViewById(R.id.Creativity);
        imageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCreativity();
            }
        });

        ImageButton imageButton3 = rootView.findViewById(R.id.Resources);
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResources();
            }
        });

        ImageButton imageButton4 = rootView.findViewById(R.id.Physical);
        imageButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPhysical();
            }
        });
        return rootView;
    }

    public void openRelaxation(){
        Intent intent = new Intent(getActivity(), relaxation.class);
        startActivity(intent);
    }

    public void openCreativity(){
        Intent intent = new Intent(getActivity(), creativity.class);
        startActivity(intent);
    }

    public void openResources(){
        Intent intent = new Intent(getActivity(), resources1.class);
        startActivity(intent);
    }

    public void openPhysical(){
        Intent intent = new Intent(getActivity(), physical.class);
        startActivity(intent);
    }

}