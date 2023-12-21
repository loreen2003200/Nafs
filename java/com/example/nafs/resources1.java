package com.example.nafs;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

public class resources1 extends AppCompatActivity {

    private MyPlayService audioService;
    private boolean isServiceBound = false;

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyPlayService.LocalBinder binder = (MyPlayService.LocalBinder) service;
            audioService = binder.getService();
            isServiceBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            audioService = null;
            isServiceBound = false;
        }
    };

    ImageView pod1, pod2, pod3, play, pause, stop;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resources1);

        pod1 = findViewById(R.id.pod1);
        pod2 = findViewById(R.id.pod2);
        pod3 = findViewById(R.id.pod3);
        play = findViewById(R.id.play);
        stop = findViewById(R.id.stop);
        pause = findViewById(R.id.pause);


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isServiceBound) {
                    Intent intent = new Intent(resources1.this, MyPlayService.class);
                    bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
                    startService(intent);
                } else {
                    audioService.resumeAudio();
                }
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isServiceBound) {
                    audioService.pauseAudio();
                }
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isServiceBound) {
                    unbindService(serviceConnection);
                    stopService(new Intent(resources1.this, MyPlayService.class));
                    isServiceBound = false;
                }
            }
        });

        pod1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                GoToUrl("https://youtu.be/ufQEqi4LUZ4?si=Np-poFd24yF-pQa3");
            }
        });

        pod2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                GoToUrl("https://youtu.be/mMtNQgsNZ6Y?si=7DZY74ZoVBqSteQD");
            }
        });

        pod3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                GoToUrl("https://youtu.be/iLlrIi9-NfQ?si=fUKBBAQItSLUvh0J");
            }
        });


    }

    private void GoToUrl(String s){
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isServiceBound) {
            unbindService(serviceConnection);
        }
    }

}