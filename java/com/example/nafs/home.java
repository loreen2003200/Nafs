package com.example.nafs;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nafs.R.id;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Map;

public class home extends Fragment {
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        ImageView imageView = rootView.findViewById(R.id.profile_b);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });

        SharedPreferences preferences = getActivity().getSharedPreferences("UserInfo", 0);
        String username = preferences.getString("username", "");

        TextView user = rootView.findViewById(id.userName_home);

        user.setText(username);


        TextView text = rootView.findViewById(R.id.moodText);

        SharedPreferences sp = getActivity().getSharedPreferences("Moods", Context.MODE_PRIVATE);
        Map<String, ?> SavedMoods = sp.getAll();
        StringBuilder displayText = new StringBuilder();
        for (String key : SavedMoods.keySet()) {
            displayText.append(SavedMoods.get(key)).append("\n");
        }
        text.setText(displayText.toString());



        return rootView;
    }


    public void openProfile(){
        Intent intent = new Intent(getActivity(), profile.class);
        startActivity(intent);
    }
}