package de.perflyst.batterycalibration;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button calibrate_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onClickCalibrate();
    }

    public void onClickCalibrate() {
        calibrate_button = findViewById(R.id.calibrate_button);

        calibrate_button.setOnClickListener(view -> {
            try {
                Process su = Runtime.getRuntime().exec("su");
                DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());

                File file_batt_reset = new File("/sys/class/power_supply/battery/batt_reset_soc");

                if (file_batt_reset.exists()) {
                    outputStream.writeBytes("echo 1 > /sys/class/power_supply/battery/batt_reset_soc\n");
                    outputStream.flush();
                    outputStream.writeBytes("dumpsys batterystats --reset\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    su.waitFor();
                } else {
                    outputStream.writeBytes("dumpsys batterystats --reset\n");
                    outputStream.flush();
                    outputStream.writeBytes("exit\n");
                    outputStream.flush();
                    su.waitFor();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(getApplicationContext(), "Calibration successful!", Toast.LENGTH_LONG).show();
        });
    }
}
