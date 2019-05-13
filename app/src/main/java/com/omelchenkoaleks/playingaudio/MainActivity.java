package com.omelchenkoaleks.playingaudio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mMediaPlayer;
    Button mPlayPauseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
            Аудио может воспроизводится без отображения -
                не нужна разметка
                т.к. нет привязки к ui можно получить контекст всего приложения
         */

        mPlayPauseButton = findViewById(R.id.play_button);
        mPlayPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mMediaPlayer.isPlaying()) {
                    pause();
                } else {
                    play();
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
    }

}
