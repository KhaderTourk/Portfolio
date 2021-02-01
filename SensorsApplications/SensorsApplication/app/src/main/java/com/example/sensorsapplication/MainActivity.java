package com.example.sensorsapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView textView;
    ImageView xImage ;
    ImageView yImage ;
   // ImageView objectv ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView =findViewById(R.id.textView);
        xImage = findViewById(R.id.imageX);
        yImage = findViewById(R.id.imageY);
      //  objectv = findViewById(R.id.objectimage);

       SensorManager sensorManager = (SensorManager) getSystemService(this.SENSOR_SERVICE);

        Sensor sensor = null ;

        if ( sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null){
            sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }else {
            System.out.println("is not found");
        }

        sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL);


    }


    @Override
    public void onSensorChanged(SensorEvent SensorEvent) {
        if (SensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float x =SensorEvent.values[0];
            float y =SensorEvent.values[1];
            float z =SensorEvent.values[2];

//            DisplayMetrics dm = new DisplayMetrics();
//            getWindowManager().getDefaultDisplay().getMetrics(dm);
//
//            int screenWidth = dm.widthPixels;
//            int screenHigth = dm.heightPixels;


            textView.setText("x :"+x+"\n"+"y :"+y+"\n"+"x :"+z);

            if (x > 0){
                xImage.setImageDrawable(getDrawable(R.drawable.ic_left));
//                if (objectv.getX() > -50)
//                objectv.setX(objectv.getX()-x*5);
            }else if (x < 0){
                xImage.setImageDrawable(getDrawable(R.drawable.ic_right));
//                if (objectv.getX() < screenWidth - 350)
//                    objectv.setX(objectv.getX()-x*5);
            }

            if (y > 0){
                yImage.setImageDrawable(getDrawable(R.drawable.ic_downward));
//                if (objectv.getY() < screenHigth -(objectv.getHeight()*1.5))
//                    objectv.setY(objectv.getY()+y*5);
            }else if (y < 0){
                yImage.setImageDrawable(getDrawable(R.drawable.ic_upward));
//                if (objectv.getY() > -30)
//                    objectv.setY(objectv.getY()+y*5);
            }


        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}


