package com.example.nafs;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.widget.Button;
import android.widget.ImageButton;

public class quotes extends Fragment {

    ImageButton anxiety_b;
    ImageButton love_b;
    ImageButton poetic_b;
    ImageButton motivation_b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_quotes, container, false);

        anxiety_b = (ImageButton) rootView.findViewById(R.id.anxiety);
        love_b = (ImageButton) rootView.findViewById(R.id.love);
        poetic_b = (ImageButton) rootView.findViewById(R.id.poetic);
        motivation_b = (ImageButton) rootView.findViewById(R.id.motivation);



        anxiety_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anxiety();
            }
        });

        love_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                love();
            }
        });

        poetic_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poetic();
            }
        });

        motivation_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                motivation();
            }
        });

        return rootView;
    }

    public void anxiety(){
        Intent intent = new Intent(getActivity() , anxiety_button.class);
        startActivity(intent);
    }

    public void love(){
        Intent intent = new Intent(getActivity() , love_button.class);
        startActivity(intent);
    }

    public void poetic(){
        Intent intent = new Intent(getActivity() , poetic_button.class);
        startActivity(intent);
    }

    public void motivation(){
        Intent intent = new Intent(getActivity() , motivation_button.class);
        startActivity(intent);
    }
}