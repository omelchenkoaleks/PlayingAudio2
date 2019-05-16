package com.omelchenkoaleks.mymusicplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaActionSound;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mMediaPlayer;
    ImageView mPauseImageView;
    SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPauseImageView = findViewById(R.id.play_imageButton3);
        mSeekBar = findViewById(R.id.seekBar);

        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.stuff);
        mSeekBar.setMax(mMediaPlayer.getDuration());

        /*
            нужен слушатель, чтобы реализовать возможность перемотки SeekBar ...
         */
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mMediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                mSeekBar.setProgress(mMediaPlayer.getCurrentPosition());
            }
        }, 0, 1000);
    }

    public void play(View view) {
        if (mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
            mPauseImageView.setImageResource(R.drawable.ic_play_arrow_orange_24dp);
        } else {
            mMediaPlayer.start();
            mPauseImageView.setImageResource(R.drawable.ic_pause_orange_24dp);
        }
    }

    public void next(View view) {
    }

    public void previous(View view) {
    }
}
