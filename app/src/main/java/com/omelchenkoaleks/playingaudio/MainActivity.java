package com.omelchenkoaleks.playingaudio;

import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mMediaPlayer;
    Button mPlayPauseButton;
    Drawable mColorBackground;
    SeekBar mVolumeSeekBar;
    AudioManager mAudioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // нужен для контроля над звуком (связать звук с SeekBar)
        mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);

        mPlayPauseButton = findViewById(R.id.play_button);
        mColorBackground = mPlayPauseButton.getBackground();

        mVolumeSeekBar = findViewById(R.id.volume_seek_bar);
        mVolumeSeekBar.setMax(maxVolume);
        mVolumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("Progress changed", "" + progress);
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        mPlayPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer.isPlaying()) {
                    pause();
                } else {
                    play();
                    mPlayPauseButton.setBackgroundColor(getColor(R.color.colorAccent));
                }
            }
        });

        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.stuff);

    }

    public void play() {
        mMediaPlayer.start();
        mPlayPauseButton.setText(R.string.play);
    }

    public void pause() {
        mMediaPlayer.pause();
        mPlayPauseButton.setText(R.string.pause);
        mPlayPauseButton.setBackground(mColorBackground);
    }

}
