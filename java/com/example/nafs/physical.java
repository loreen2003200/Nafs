package com.example.nafs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class physical extends AppCompatActivity {

    ImageView link1, link2, link3, link4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_physical);

        link1 = findViewById(R.id.link1);
        link2 = findViewById(R.id.link2);
        link3 = findViewById(R.id.link3);
        link4 = findViewById(R.id.link4);

        link1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                GoToUrl("https://www.verywellmind.com/how-dancing-helps-your-mental-health-5206963");
            }
        });

        link2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                GoToUrl("https://www.mhconn.org/mind-body-health/practicing-pilates-improve-mental-health/");
            }
        });

        link3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                GoToUrl("https://www.apa.org/monitor/2022/11/defeating-depression-naturally");
            }
        });

        link4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                GoToUrl("https://journals.sagepub.com/doi/full/10.1177/15598276221124095");
            }
        });


    }

    private void GoToUrl(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }


}