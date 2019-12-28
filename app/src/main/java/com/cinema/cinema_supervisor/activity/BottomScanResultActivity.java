package com.cinema.cinema_supervisor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import com.cinema.cinema_supervisor.R;
import com.github.javiersantos.bottomdialogs.BottomDialog;

import java.util.ArrayList;

public class BottomScanResultActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_scan_result);


        new BottomDialog.Builder(this)
                .setTitle("QR scan info")
                .setContent("Film: Once upon a time in Hollywood\nDate: 10-10-10\nPlace: c-10-10\nCosmos")
                .setPositiveText("Submit")
                .setPositiveBackgroundColorResource(R.color.colorAccent)
                .setPositiveTextColorResource(android.R.color.white)
                .onPositive(new BottomDialog.ButtonCallback() {
                    @Override
                    public void onClick(BottomDialog dialog) {

                    }
                })
                .setNegativeText("Ignore")
                .setNegativeTextColorResource(R.color.colorPrimary)
                .onNegative(new BottomDialog.ButtonCallback() {
                    @Override
                    public void onClick(BottomDialog dialog) {


                    }
                })
                .setCancelable(false)
                .show();


    }


}
