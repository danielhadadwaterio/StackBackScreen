package com.water_io.fragmentstack;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.water_io.stackscreens.StackBackScreen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new StackBackScreen(-1,30);
    }
}
