package com.example.nafs;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import androidx.annotation.Nullable;

public class MyPlayService extends Service {
    private MediaPlayer mediaPlayer;
    private final IBinder binder = new LocalBinder();
    private boolean isPaused = false;
    private int currentProgress = 0;

    public class LocalBinder extends Binder {
        MyPlayService getService() {
            return MyPlayService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.nature);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopSelf();
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (isPaused) {
            mediaPlayer.seekTo(currentProgress);
        } else {
            mediaPlayer.start();
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    public void pauseAudio() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            isPaused = true;
            currentProgress = mediaPlayer.getCurrentPosition();
        }
    }

    public void resumeAudio() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(currentProgress);
            mediaPlayer.start();
            isPaused = false;
        }
    }
}