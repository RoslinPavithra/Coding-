package com.appsinfinity.www.constructioapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import java.io.IOException;

public class ResultActivity extends AppCompatActivity {

    TextView a,b,c,d,e,f,g;
    String floor, bricks, cement, steel, paint, wireCable, plyWood, total, msgBody, rooms, quality, costs;
    Uri fileUri;
    ImageView img_cover_low, img_cover_top;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        floor = getIntent().getStringExtra("floor");
        rooms = getIntent().getStringExtra("rooms");
        quality = getIntent().getStringExtra("quality");
        bricks = getIntent().getStringExtra("bricks");
        cement = getIntent().getStringExtra("cement");
        steel = getIntent().getStringExtra("steel");
        paint = getIntent().getStringExtra("paint");
        wireCable = getIntent().getStringExtra("wireCable");
        plyWood = getIntent().getStringExtra("plyWood");
        total = getIntent().getStringExtra("total");
        costs = getIntent().getStringExtra("costs");

        msgBody = "Cost Estimation Report - "+quality+" Quality \n"+floor+" Floors - "+rooms+" Rooms\n1.Cement: Rs."+cement+
                "\n2.Bricks & Crushed Stone: Rs."+bricks+"" +
                "\n3.Steel: Rs."+steel+"" +
                "\n4.PlyWood: Rs."+plyWood+"" +
                "\n5.Paint: Rs."+paint+"" +
                "\n6.WireCable: Rs."+wireCable+"" +
                "\nTotal: Rs."+total+"" +
                "\n\n\n Costs: "+costs;
        img_cover_low = findViewById(R.id.img_cover_low);
        img_cover_top = findViewById(R.id.img_cover_top);


        fileUri = Uri.parse(getIntent().getStringExtra("imageUrl"));
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fileUri);
            img_cover_low.setImageBitmap(bitmap);
            img_cover_top.setImageResource(R.drawable.ic_camera);
        } catch (IOException e) {
            e.printStackTrace();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog progressView = new ProgressDialog(
                        ResultActivity.this
                );
                progressView.setMessage("Creating Sharable Report..");
                progressView.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                progressView.setCancelable(false);
                progressView.show();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressView.dismiss();
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "REPORT");
                        sharingIntent.putExtra(Intent.EXTRA_TEXT, msgBody);
                        startActivity(sharingIntent);

                    }
                }, 3000);
            }
        });

        a = findViewById(R.id.txt_bricks);
        b = findViewById(R.id.txt_cement);
        c = findViewById(R.id.txt_steel);
        d = findViewById(R.id.txt_plywood);
        e = findViewById(R.id.txt_pain);
        f = findViewById(R.id.txt_wirecable);
        g = findViewById(R.id.txt_total);

        a.setText("Bricks & Crushed Stone: Rs:"+bricks);
        b.setText("Steel: Rs:"+steel);
        c.setText("Cement: Rs:"+cement);
        d.setText("PlayWood: Rs:"+plyWood);
        e.setText("Paint: Rs:"+paint);
        f.setText("WireCable: Rs:"+wireCable);
        g.setText("Total: Rs."+total);

        setImage();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_configuration) {
            createPopUp();
        }

            return super.onOptionsItemSelected(item);
        }

    private void createPopUp() {
        final MaterialDialog dialog = new MaterialDialog.Builder(ResultActivity.this)
                .customView(R.layout.dialog_add_number, true)
                .build();

        final TextView mo = (TextView) dialog.findViewById(R.id.edt_groups_count);
        if(quality.equals("Low")){
            mo.setText("Bricks & Crushed Stone: J.P Enterprise, \nCement: Birlasamrat, \nSteel: Crystal Enterprise, \nPlyWood: Ashley Furniture, \nPaint: Flash paint, \nWireCables: Bansal wire industries.");
        } else if(quality.equals("Medium")){
            mo.setText("Bricks & Crushed Stone: Maruthi krupa Robo, \nCement: ultratech, \nSteel: Mann Enterprise, \nPlyWood: IKEA, \nPaint: Dampshield paint , \nWireCables: Oasis connectivity pvt ltd.");
        } else if(quality.equals("High")){
            mo.setText("Bricks & Crushed Stone: SLN Enterprises, \nCement: ambhuja, \nSteel: Swastik matel craft, \nPlyWood: Williams-Sonoma, \nPaint: Chamen ultra paint , \nWireCables: Samiu sales corporation.");
        }

        Button btnSave = (Button) dialog.findViewById(R.id.btn_save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void setImage(){
        switch (floor){
            case "1":
                img_cover_top.setImageResource(R.drawable.img_h1);
                break;
            case "0":
                img_cover_top.setImageResource(R.drawable.img_h1);
                break;
            case "2":
                img_cover_top.setImageResource(R.drawable.img_h2);
                break;
            case "3":
                img_cover_top.setImageResource(R.drawable.img_h3);
                break;
            case "4":
                img_cover_top.setImageResource(R.drawable.img_h4);
                break;
            case "5":
                img_cover_top.setImageResource(R.drawable.img_h5);
                break;
            default:
                img_cover_top.setImageResource(R.drawable.img_h5);
                break;
        }
    }
}
