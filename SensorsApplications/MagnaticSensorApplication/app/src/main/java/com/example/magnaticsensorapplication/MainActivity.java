package com.example.magnaticsensorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensor = null;
    TextView tvValues ;
    TextView tvAccess ;
    TextView red ;
    TextView green ;
    TextView blue ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvValues = findViewById(R.id.tv_values);
        tvAccess = findViewById(R.id.tv_access);
        red = findViewById(R.id.tv_red);
        green = findViewById(R.id.tv_green);
        blue = findViewById(R.id.tv_blue);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);


        if ( sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD) != null){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        }else {
            Toast.makeText(this , "Sensor MAGNETIC_FIELD not found" , Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            int x =(int) Math.abs(sensorEvent.values[0]);
            int y =(int) Math.abs(sensorEvent.values[1]);
            int z =(int) Math.abs(sensorEvent.values[2]);

            tvValues.setText("X : "+ x +"\nY : "+ y +"\nZ : "+ z +"\n MaxVal : "+ sensorEvent.sensor.getMaximumRange());
            if(x > y && x > z ){

                tvAccess.setText("X");
                setCVisible(x);

            }else if(y > x && y > z ){

                tvAccess.setText("Y");
                setCVisible(y);

            }else if(z > x && z > y ){

                tvAccess.setText("Z");
                setCVisible(z);

            }
        }
    }
    public void setCVisible(int vale){
        if (vale > 0 && vale < 200){
            green.setVisibility(View.VISIBLE);
            blue.setVisibility(View.INVISIBLE);
            red.setVisibility(View.INVISIBLE);
        }else  if (vale > 200 && vale < 500){
            green.setVisibility(View.INVISIBLE);
            blue.setVisibility(View.VISIBLE);
            red.setVisibility(View.INVISIBLE);
        }else  if ( vale > 500){
            green.setVisibility(View.INVISIBLE);
            blue.setVisibility(View.INVISIBLE);
            red.setVisibility(View.VISIBLE);
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
