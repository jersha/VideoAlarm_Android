package com.example.videoalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class Settings extends AppCompatActivity {

    EditText ET_KW1, ET_KW2, ET_KW3, ET_KW4, ET_KW5;
    String KW1, KW2, KW3, KW4, KW5;
    Button BT_Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_SHOW_WALLPAPER, WindowManager.LayoutParams.TYPE_STATUS_BAR);
        ET_KW1 = findViewById(R.id.editText_kw1);
        ET_KW2 = findViewById(R.id.editText_kw2);
        ET_KW3 = findViewById(R.id.editText_kw3);
        ET_KW4 = findViewById(R.id.editText_kw4);
        ET_KW5 = findViewById(R.id.editText_kw5);
        BT_Back = findViewById(R.id.buttonBack);

        BT_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KW1 = ET_KW1.toString();
                KW2 = ET_KW2.toString();
                KW3 = ET_KW3.toString();
                KW4 = ET_KW4.toString();
                KW5 = ET_KW5.toString();
            }
        });

        BT_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Settings.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}