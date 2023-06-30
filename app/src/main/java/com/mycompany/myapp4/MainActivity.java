package com.mycompany.myapp4;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;

public class MainActivity extends Activity {

    private TimePicker alarmTimePicker;
    private PendingIntent pendingIntent;
    private AlarmManager alarmManager;
    private ToggleButton toggleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        alarmTimePicker = findViewById(R.id.timepicker1);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        toggleButton = findViewById(R.id.toggleButton);
    }

    public void OnToggleClicked(View view) {
        long time;
        if (toggleButton.isChecked()) {
            Toast.makeText(MainActivity.this, "ALARM ON", Toast.LENGTH_SHORT).show();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, alarmTimePicker.getCurrentHour());
            calendar.set(Calendar.MINUTE, alarmTimePicker.getCurrentMinute());

            Intent intent = new Intent(this, AlarmReciever.class);
            pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

            time = calendar.getTimeInMillis();
            if (System.currentTimeMillis() > time) {
                time += 24 * 60 * 60 * 1000; // Add 24 hours
            }

            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 24 * 60 * 60 * 1000, pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            Toast.makeText(MainActivity.this, "ALARM OFF", Toast.LENGTH_SHORT).show();
        }
    }
}

