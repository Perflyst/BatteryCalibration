package de.perflyst.batterycalibration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Button calibrate_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onClickCalibrate();

        }

        public void onClickCalibrate() {
            calibrate_button = (Button)findViewById(R.id.calibrate_button);

            calibrate_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        Process su = Runtime.getRuntime().exec("su");
                        DataOutputStream outputStream = new DataOutputStream(su.getOutputStream());
                        outputStream.writeBytes("echo 1 > /sys/class/power_supply/battery/batt_reset_soc\n");
                        outputStream.flush();
                        outputStream.writeBytes("dumpsys batterystats --reset\n");
                        outputStream.flush();
                        outputStream.writeBytes("exit\n");
                        outputStream.flush();
                        su.waitFor();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "Calibration successful", Toast.LENGTH_LONG).show();
                }
            });
        }
}
