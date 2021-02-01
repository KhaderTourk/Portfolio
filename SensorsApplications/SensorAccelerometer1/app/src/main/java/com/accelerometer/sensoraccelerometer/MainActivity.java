package com.accelerometer.sensoraccelerometer;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor = null;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.info);

        sensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);


        if ( sensorManager.getDefaultSensor(sensor.TYPE_GYROSCOPE) != null){
            sensor = sensorManager.getDefaultSensor(sensor.TYPE_GYROSCOPE);
        }else {
            Toast.makeText(this , "Sensor GYROSCOPE not found" , Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == sensor.TYPE_GYROSCOPE){
            int x =(int)sensorEvent.values[0];
            int y =(int)sensorEvent.values[1];
            int z =(int)sensorEvent.values[2];

            textView.setText("x :"+x+"\n"+"y :"+y+"\n"+"x :"+z);

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


