package com.example.datacollectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    SensorManager sm=null;
    TextView ttg,tta;
    List listg;
    List lista;
    SensorEventListener selg=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float[] values = event.values;
            ttg.setText("Gyroscope\nx: "+values[0]+"\ny: "+values[1]+"\nz: "+values[2]);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    SensorEventListener sela=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            float values[]=event.values;
            tta.setText("Accelerometer\nx: "+values[0]+"\ny: "+values[1]+"\nz: "+values[2]);

        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sm=(SensorManager)getSystemService((SENSOR_SERVICE));
        ttg=(TextView)findViewById(R.id.tvg);
        tta=(TextView)findViewById(R.id.tva);
        listg=sm.getSensorList(Sensor.TYPE_GYROSCOPE);
        lista=sm.getSensorList((Sensor.TYPE_ACCELEROMETER));
        if(listg.size()>0){
            sm.registerListener(selg,(Sensor)listg.get(0),SensorManager.SENSOR_DELAY_NORMAL);

        }
        else{
            Toast.makeText(getBaseContext(), "Error: No Gyroscope.", Toast.LENGTH_LONG).show();

        }
        if(lista.size()>0){
            sm.registerListener(sela,(Sensor)lista.get(0),SensorManager.SENSOR_DELAY_NORMAL);
        }
        else{
            Toast.makeText(getBaseContext(), "Error: No Accelerometer.", Toast.LENGTH_LONG).show();

        }

    }
    @Override
    protected void onStop() {
        if(lista.size()>0 || listg.size()>0){
            sm.unregisterListener(sela);
            sm.unregisterListener(selg);
        }
        super.onStop();
    }


}