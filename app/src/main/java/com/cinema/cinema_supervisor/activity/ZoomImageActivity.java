package com.cinema.cinema_supervisor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.cinema.cinema_supervisor.R;
import com.cinema.cinema_supervisor.requests.APIClient;
import com.github.chrisbanes.photoview.PhotoView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ZoomImageActivity extends AppCompatActivity {


    @BindView(R.id.filmPosterZoomActivityPhotoView)
    PhotoView filmPosterZoomActivityPhotoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoom_image);
        ButterKnife.bind(this);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        String url= APIClient.HOST+getIntent().getStringExtra("url");
        Glide.with(this).load(url).into(filmPosterZoomActivityPhotoView);
    }

}