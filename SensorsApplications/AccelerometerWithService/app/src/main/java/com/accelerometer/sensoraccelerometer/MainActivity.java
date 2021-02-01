package com.accelerometer.sensoraccelerometer;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor = null;
    private ConstraintLayout layout;
    MediaPlayer mediaPlayerM;
    MediaPlayer mediaPlayerN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.layOut);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


        if ( sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT) != null){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        }else {
            Toast.makeText(this , "Sensor LIGHT not found" , Toast.LENGTH_SHORT).show();
        }
    }
public void playMorning(){
if(mediaPlayerM == null){
    mediaPlayerM = MediaPlayer.create(this, R.raw.birds);
    mediaPlayerM.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            stopM();
        }
    });
}
mediaPlayerM.start();
}

    public void stopM(){
        if(mediaPlayerM != null){
            mediaPlayerM.release();
            mediaPlayerM = null;
        }
    }
    public void playNight(){
        if(mediaPlayerN == null){
            mediaPlayerN = MediaPlayer.create(this, R.raw.nigth);
            mediaPlayerN.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopN();
                }
            });
        }
        mediaPlayerN.start();
    }

    public void stopN(){
        if(mediaPlayerN != null){
            mediaPlayerN.release();
            mediaPlayerN = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopM();
        stopN();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_LIGHT){
            int val =(int)sensorEvent.values[0];
            if(val > 50){
                layout.setBackgroundResource(R.drawable.morning);
                playMorning();
                stopN();
            }else {
               layout.setBackgroundResource(R.drawable.nigth);
               playNight();
               stopM();
            }
        }
    }
    @Override
    protected void onResume(){
        super.onResume();

        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);

    }
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }
}


