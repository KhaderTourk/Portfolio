package com.accelerometer.sensoraccelerometer;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor = null;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.imageView);

        sensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);


        if ( sensorManager.getDefaultSensor(sensor.TYPE_PROXIMITY) != null){
            sensor = sensorManager.getDefaultSensor(sensor.TYPE_PROXIMITY);
        }else {
            Toast.makeText(this , "Sensor PROXIMITY not found" , Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == sensor.TYPE_PROXIMITY){
            int val =(int)sensorEvent.values[0];
            if(val > 0){

                ObjectAnimator oaX = ObjectAnimator.ofFloat(image, "scaleX",  3f);
                ObjectAnimator oaY = ObjectAnimator.ofFloat(image, "scaleY",  3f);
                oaX.setDuration(1000);
                oaY.setDuration(1000);

                AnimatorSet as = new AnimatorSet();
                as.play(oaX).with(oaY);
                as.start();

            }else {

                ObjectAnimator oaX = ObjectAnimator.ofFloat(image, "scaleX",  0.3f);
                ObjectAnimator oaY = ObjectAnimator.ofFloat(image, "scaleY",  0.3f);
                oaX.setDuration(1000);
                oaY.setDuration(1000);

                AnimatorSet as = new AnimatorSet();
                as.play(oaX).with(oaY);
                as.start();

            }
        }

    }
    @Override
    protected void onResume(){
        super.onResume();

        sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_NORMAL);

    }
    protected void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {


    }
}


