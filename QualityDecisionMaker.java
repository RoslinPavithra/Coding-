package com.appsinfinity.www.constructioapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class QualityDecisionMaker extends AppCompatActivity implements View.OnClickListener {

    CardView card1, card2, card3;
    String floor, room, squareFeet, fileUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quality_decision_maker);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        floor = getIntent().getStringExtra("floor");
        room = getIntent().getStringExtra("room");
        squareFeet = getIntent().getStringExtra("squareFeet");
        fileUrl = getIntent().getStringExtra("imageUrl");

        card1 = findViewById(R.id.card_1);
        card2 = findViewById(R.id.card_2);
        card3 = findViewById(R.id.card_3);
        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        final Intent intent = new Intent(this, ProcessingActiviy.class);
        intent.putExtra("room", room);
        intent.putExtra("floor", floor);
        intent.putExtra("squareFeet", squareFeet);
        intent.putExtra("imageUrl", fileUrl);
        if(view.getId() == R.id.card_1){
            intent.putExtra("quality", "Low");
        } else if(view.getId() == R.id.card_2){
            intent.putExtra("quality", "Medium");
        } else if(view.getId() == R.id.card_3){
            intent.putExtra("quality", "High");
        }

        final ProgressDialog progressView = new ProgressDialog(
                this
        );
        progressView.setMessage("Please wait..");
        progressView.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        progressView.setCancelable(false);
        progressView.show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressView.dismiss();
                startActivity(intent);
            }
        }, 2000);
    }
}
