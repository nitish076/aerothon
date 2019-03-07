package com.test.airbuzz.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.test.airbuzz.R;

public class AeroActivity extends AppCompatActivity {

    Button btnInput,btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aero_activity_main);

        btnInput = findViewById(R.id.input_btn);
        btnSearch = findViewById(R.id.search_btn);

        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AeroActivity.this,AeroInputActivity.class);
                startActivity(intent);
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AeroActivity.this,AeroSearchActivty.class);
                startActivity(intent);
            }
        });

    }
}
