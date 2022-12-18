package com.example.autosalon.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.autosalon.R;

public class AvtoSalonActivity extends AppCompatActivity {
    AppCompatButton addCarBtn, showCarBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avto_salon);
        init();

    }

    public void init() {
        addCarBtn = findViewById(R.id.AddCar);
        showCarBtn = findViewById(R.id.ShowCar);
        addCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AvtoSalonActivity.this, AddCarActivity.class);
                startActivity(intent);
            }
        });
        showCarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AvtoSalonActivity.this, ShowCarActivity.class);
                startActivity(intent);
            }
        });
    }
}