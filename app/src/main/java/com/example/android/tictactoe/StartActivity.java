package com.example.android.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        Button button1 = (Button)findViewById(R.id.easy);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this,MainActivity.class);

                Bundle b = new Bundle();
                b.putInt("level",3);
                intent.putExtras(b);
                startActivity(intent);
                //finish();
            }
        });


        Button button2 = (Button) findViewById(R.id.medium);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartActivity.this,MainActivity.class);

                Bundle b = new Bundle();
                b.putInt("level",4);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }



}
