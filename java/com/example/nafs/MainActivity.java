package com.example.nafs;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;

import android.os.Bundle;
import android.view.View;

import com.example.nafs.databinding.ActivityMainBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new home());
        binding.bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.home) {replaceFragment(new home());}
            else if (id == R.id.diary_b) {replaceFragment(new diary());}
            else if (id == R.id.quotes_b){replaceFragment(new quotes());}
            else if(id == R.id.activities_b)replaceFragment(new activities());
            return true;
        });

        BroadcastReceiver receive = new MyReceiver();
        IntentFilter f1 = new IntentFilter(Intent.ACTION_BATTERY_LOW);
        registerReceiver(receive , f1);
        IntentFilter f2 = new IntentFilter(Intent.ACTION_POWER_CONNECTED);
        registerReceiver(receive , f2);
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

    public void goToMood(View view){
        Intent intent = new Intent(MainActivity.this, mood.class);
        startActivity(intent);
    }



}