package com.mycompany.myapp4;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.widget.Toast;

class AlarmReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Use vibrator
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
				// Define the vibration pattern as an array of long values
				long[] pattern = {0, 1000, 500, 1000}; // 0ms pause, 1000ms vibration, 500ms pause, 1000ms vibration

				// Provide the pattern and -1 for no repeat
				vibrator.vibrate(VibrationEffect.createWaveform(pattern, -1));
			} else {
				// For devices running on older versions, use the deprecated vibrate() method
				long[] pattern = {0, 1000, 500, 1000, 0}; // 0ms pause, 1000ms vibration, 500msause
				vibrator.vibrate(pattern, -1);
			}
		}
		

        Toast.makeText(context, "Alarm! Wake up! Wake up!", Toast.LENGTH_LONG).show();

        // Use default ringtone
        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }

        // Setting default ringtone
        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);

        // Play ringtone
        if (ringtone != null) {
            ringtone.play();
        }
    }
}

