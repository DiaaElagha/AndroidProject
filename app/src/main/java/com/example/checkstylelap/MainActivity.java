package com.example.checkstylelap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView hours, minutes, seconds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hours = findViewById(R.id.hour);
        minutes = findViewById(R.id.minute);
        seconds = findViewById(R.id.second);

        final Handler finalhandler = new Handler(Looper.getMainLooper());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                int minute = 0;
                int hour = 0;
                for (int i = 0; i < 60; i++) {
                    final int k = i;
                    finalhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            seconds.setText(k + "");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (i == 59) {
                        if (minute == 59 && i == 59) {
                            i = 0;
                            minute = 0;
                            hour += 1;
                            final int th = hour;
                            finalhandler.post(new Runnable() {
                                @Override
                                public void run() {
                                    hours.setText(th + "");
                                }
                            });
                        } else {
                            i = 0;
                            minute += 1;
                        }
                        final int tm = minute;
                        finalhandler.post(new Runnable() {
                            @Override
                            public void run() {
                                minutes.setText(tm + "");
                            }
                        });
                    }


                }
            }
        });
        thread.start();

    }
}