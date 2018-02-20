package com.appsinfinity.www.constructioapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class ProcessingActiviy extends AppCompatActivity {

    ImageView img_gif;
    TextView txt_state;
    String floor, room, squareFeet, fileUrl, quality, costs;
    Integer totalSquareFeet, bricks, cement, steel, plyWood, paint, wireCables, total;

    Handler handler1, handler2, handler3, handler4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_processing_activiy);

        img_gif = (ImageView) findViewById(R.id.img_1);
        Glide.with(this).load(R.drawable.img_22).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).crossFade().into(img_gif);

        txt_state = findViewById(R.id.txt_success_message);
        txt_state.setText("Initiating Calculations..");
        handler1 = new Handler();
        handler2 = new Handler();
        handler3 = new Handler();
        handler4 = new Handler();

        floor = getIntent().getStringExtra("floor");
        room = getIntent().getStringExtra("room");
        squareFeet = getIntent().getStringExtra("squareFeet");
        fileUrl = getIntent().getStringExtra("imageUrl");
        quality = getIntent().getStringExtra("quality");

        Log.e("getting it", "here"+floor+" "+room+" "+squareFeet+" "+fileUrl+" "+quality);

        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                txt_state.setText("Performing Calculations..");
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        txt_state.setText("Finalizing Calculations..");
                        handler3.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                txt_state.setText("Generating Report..");
                                Toast.makeText(
                                        ProcessingActiviy.this,
                                        "Calculations successfully completed",
                                        Toast.LENGTH_SHORT).show();
                                handler4.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        Intent intent = new Intent(ProcessingActiviy.this, ResultActivity.class);
                                        intent.putExtra("floor", floor);
                                        intent.putExtra("bricks", String.valueOf(bricks));
                                        intent.putExtra("cement", String.valueOf(cement));
                                        intent.putExtra("steel", String.valueOf(steel));
                                        intent.putExtra("plyWood", String.valueOf(plyWood));
                                        intent.putExtra("paint", String.valueOf(paint));
                                        intent.putExtra("wireCable", String.valueOf(wireCables));
                                        intent.putExtra("imageUrl", fileUrl);
                                        intent.putExtra("rooms", room);
                                        intent.putExtra("quality", quality);
                                        intent.putExtra("total", String.valueOf(total));
                                        intent.putExtra("costs", costs);
                                        startActivity(intent);
                                        finish();
                                    }
                                }, 3000);
                            }
                        }, 3000);
                    }
                }, 3000);
            }
        }, 4000);
        calculate();
    }

    private void calculate(){
        totalSquareFeet = Integer.valueOf(floor)*Integer.valueOf(room)*Integer.valueOf(squareFeet);
        if(quality.equals("Low")){
            bricks = totalSquareFeet/8 * 420;
            cement = totalSquareFeet/10 * 280;
            steel = totalSquareFeet/12 * 250;
            plyWood = totalSquareFeet/40 * 234;
            paint = totalSquareFeet/20 * 250;
            wireCables = totalSquareFeet/75 * 60;
            costs = "Bricks & Crushed Stone: 4200/ton, Cement: 280/bag, Steel: 250/pieces, PlyWood: 234/sq ft, Paint: 234/sq ft, WireCable: 60/meters";
        } else if( quality.equals("Medium")){
            bricks = totalSquareFeet/8 * 650;
            cement = totalSquareFeet/10 * 320;
            steel = totalSquareFeet/12 * 450;
            plyWood = totalSquareFeet/40 * 450;
            paint = totalSquareFeet/20 * 450;
            wireCables = totalSquareFeet/75 * 150;
            costs = "Bricks & Crushed Stone: 6500/ton, Cement: 320/bag, Steel: 450/pieces, PlyWood: 450/sq ft, Paint: 450/sq ft, WireCable: 150/meters";
        } else if(quality.equals("High")){
            bricks = totalSquareFeet/8 * 950;
            cement = totalSquareFeet/10 * 550;
            steel = totalSquareFeet/12 * 480;
            plyWood = totalSquareFeet/40 * 720;
            paint = totalSquareFeet/20 * 890;
            wireCables = totalSquareFeet/70 * 300;
            costs = "Bricks & Crushed Stone: 9500/ton, Cement: 550/bag, Steel: 480/pieces, PlyWood: 720/sq ft, Paint: 890/sq ft, WireCable: 300/meters";
        }
        total = bricks + cement + steel + plyWood + paint + wireCables;
    }

    @Override
    public void onBackPressed() {
        handler1.removeCallbacksAndMessages(null);
        handler2.removeCallbacksAndMessages(null);
        handler3.removeCallbacksAndMessages(null);
        handler4.removeCallbacksAndMessages(null);
        super.onBackPressed();
    }
}
