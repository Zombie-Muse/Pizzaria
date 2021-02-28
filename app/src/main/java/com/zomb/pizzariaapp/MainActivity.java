package com.zomb.pizzariaapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOrder = (Button) findViewById(R.id.btnStartOrder);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOrderClick();
            }
        });
    }

    public void onOrderClick() {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }
}