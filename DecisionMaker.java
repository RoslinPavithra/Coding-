package com.appsinfinity.www.constructioapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.IOException;

public class DecisionMaker extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    EditText edt_floor, edt_room, edt_sq_feet;
    Spinner spn_quality;
    String type;
    Button btn_calculate;
    ImageView img_cover;
    Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decision_maker);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        edt_floor = findViewById(R.id.edt_number_of_floors);
        edt_room = findViewById(R.id.edt_number_of_rooms);
        edt_sq_feet = findViewById(R.id.edt_square_feet);
        btn_calculate = findViewById(R.id.btn_calculate);
        img_cover = findViewById(R.id.img_cover);

        fileUri = Uri.parse(getIntent().getStringExtra("imageUrl"));
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), fileUri);
            img_cover.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        btn_calculate.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_calculate){
            if(edt_room.getText().toString().trim().length() > 0 &&
                    edt_floor.getText().toString().trim().length() > 0 &&
                    edt_sq_feet.getText().toString().trim().length() > 0) {
                final ProgressDialog progressView = new ProgressDialog(
                        this
                );
                progressView.setMessage("Submitting Form..");
                progressView.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
                progressView.setCancelable(false);
                progressView.show();
                final Intent intent = new Intent(DecisionMaker.this, QualityDecisionMaker.class);
                intent.putExtra("room", edt_room.getText().toString().trim());
                intent.putExtra("floor", edt_floor.getText().toString().trim());
                intent.putExtra("squareFeet", edt_sq_feet.getText().toString().trim());
                intent.putExtra("imageUrl", fileUri.toString());
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressView.dismiss();
                        startActivity(intent);
                    }
                }, 3000);
            } else {
                Toast.makeText(
                        DecisionMaker.this,
                        "Please fill all fields. ",
                        Toast.LENGTH_SHORT
                ).show();
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        type = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
