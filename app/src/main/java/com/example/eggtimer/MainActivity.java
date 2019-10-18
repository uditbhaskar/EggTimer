package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    boolean isActive=false;
    Button button;
    CountDownTimer countDownTimer;


    public void onClick(View view){

            if(isActive){
                textView.setText("0:30");
                seekBar.setProgress(30);
                seekBar.setEnabled(true);
                button.setText("GO!!");
                countDownTimer.cancel();
                isActive=false;
            }
            else{
            seekBar.setEnabled(isActive);
            button.setText("STOP!!");
            isActive=true;
            countDownTimer=new CountDownTimer(seekBar.getProgress()*1000+100,1000) {
            @Override
            public void onTick(long l) {
                    updateTimer((int)l/1000);
            }

            @Override
            public void onFinish() {
                MediaPlayer mediaPlayer=MediaPlayer.create(getApplicationContext(), R.raw.alarm_sound);
                mediaPlayer.start();
            }
        }.start();
            }

    }

    public void updateTimer(int progress){
        int minutes;
        int seconds;
        minutes=progress/60;
        seconds=progress-minutes*60;
        String secondString=Integer.toString(seconds);
        if (seconds<=9){
            secondString="0"+secondString;
        }
        textView.setText(Integer.toString(minutes)+" : "+secondString);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=findViewById(R.id.button);

        seekBar = findViewById(R.id.timerSeekBar);
        textView=findViewById(R.id.countDown);

        seekBar.setMax(600);
        seekBar.setProgress(30);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}