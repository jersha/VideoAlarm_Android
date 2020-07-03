package com.example.videoalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ImageView minutehand, hourhand;
    Button BT_Options;
    IntentFilter s_intentFilter;
    BroadcastReceiver m_timeChangedReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER, WindowManager.LayoutParams.TYPE_STATUS_BAR);

        BT_Options = findViewById(R.id.buttonOptions);
        minutehand = findViewById(R.id.iv_minute);
        hourhand = findViewById(R.id.iv_hour);
        final Calendar[] rightNow = {Calendar.getInstance()};
        int currentHourIn24Format = rightNow[0].get(Calendar.HOUR_OF_DAY);
        final int[] currentHourIn12Format = {rightNow[0].get(Calendar.HOUR)};
        final int[] currentMinute = {rightNow[0].get(Calendar.MINUTE)};

        final int[] hr_start_pt = {0};
        final int[][] hr_end_pt = {{currentHourIn12Format[0] * 30}};
        final int[][] min_start_pt = {{0}};
        final int[][] min_end_pt = {{currentMinute[0] * 6}};

        s_intentFilter = new IntentFilter();
        s_intentFilter.addAction(Intent.ACTION_TIME_TICK);
        s_intentFilter.addAction(Intent.ACTION_TIMEZONE_CHANGED);
        s_intentFilter.addAction(Intent.ACTION_TIME_CHANGED);

        final RotateAnimation rotate_hr = new RotateAnimation(hr_start_pt[0], hr_end_pt[0][0], Animation.RELATIVE_TO_SELF,
                0.5f,  Animation.RELATIVE_TO_SELF, 0.5f);
        rotate_hr.setFillAfter(true);
        rotate_hr.setDuration(500);
        hourhand.startAnimation(rotate_hr);
        hr_start_pt[0] = hr_end_pt[0][0];
        hr_end_pt[0][0] += currentHourIn12Format[0] * 30;

        final RotateAnimation rotate_min = new RotateAnimation(min_start_pt[0][0], min_end_pt[0][0], Animation.RELATIVE_TO_SELF,
                0.5f,  Animation.RELATIVE_TO_SELF, 0.5f);
        rotate_min.setFillAfter(true);
        rotate_min.setDuration(500);
        minutehand.startAnimation(rotate_min);
        min_start_pt[0][0] = min_end_pt[0][0];
        min_end_pt[0][0] += currentMinute[0] * 6;

        final BroadcastReceiver m_timeChangedReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                final String action = intent.getAction();

                assert action != null;
                if(action.equals(Intent.ACTION_TIME_TICK)){
                    rightNow[0] = Calendar.getInstance();
                    currentHourIn12Format[0] = rightNow[0].get(Calendar.HOUR);
                    currentMinute[0] = rightNow[0].get(Calendar.MINUTE);
                    hr_end_pt[0][0] = currentHourIn12Format[0] * 30;
                    min_end_pt[0][0] = currentMinute[0] * 6;

                    final RotateAnimation rotate_hr = new RotateAnimation(hr_start_pt[0], hr_end_pt[0][0], Animation.RELATIVE_TO_SELF,
                            0.5f,  Animation.RELATIVE_TO_SELF, 0.5f);
                    rotate_hr.setFillAfter(true);
                    rotate_hr.setDuration(500);
                    hourhand.startAnimation(rotate_hr);
                    hr_start_pt[0] = hr_end_pt[0][0];

                    final RotateAnimation rotate_min = new RotateAnimation(min_start_pt[0][0], min_end_pt[0][0], Animation.RELATIVE_TO_SELF,
                            0.5f,  Animation.RELATIVE_TO_SELF, 0.5f);
                    rotate_min.setFillAfter(true);
                    rotate_min.setDuration(500);
                    minutehand.startAnimation(rotate_min);
                    min_start_pt[0][0] = min_end_pt[0][0];
                }
            }
        };

        registerReceiver(m_timeChangedReceiver, s_intentFilter);

        BT_Options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
                finish();
            }
        });
    }
}