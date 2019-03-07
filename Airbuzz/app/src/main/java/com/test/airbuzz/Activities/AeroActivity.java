package com.test.airbuzz.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.test.airbuzz.MainActivity;
import com.test.airbuzz.R;

public class AeroActivity extends AppCompatActivity {


    TextView tv_a320, tv_a330, tv_a350, tv_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aero_activity_main);

        tv_a320 = findViewById(R.id.model_a320);
        tv_a330 = findViewById(R.id.model_a330);
        tv_a350 = findViewById(R.id.model_a350);
        tv_search = findViewById(R.id.search_btn);

        tv_a320.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AeroActivity.this, AeroInputActivity.class);
                intent.putExtra("aircraftName","A320");
                startActivity(intent);
            }
        });

        tv_a330.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AeroActivity.this, AeroInputActivity.class);
                intent.putExtra("aircraftName","A330");
                startActivity(intent);
            }
        });

        tv_a350.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AeroActivity.this, AeroInputActivity.class);
                intent.putExtra("aircraftName","A350");
                startActivity(intent);
            }
        });

        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AeroActivity.this, AeroInputActivity.class);
                startActivity(intent);
            }
        });


    }
}
