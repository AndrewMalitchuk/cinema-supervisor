package com.cinema.cinema_supervisor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.cinema.cinema_supervisor.R;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AboutDeveloperActivity extends AppCompatActivity {


    @BindView(R.id.toolbar9)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_developer);

        ButterKnife.bind(this);

        toolbar.setTitle("About developer");
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Main2Activity.class));
            }
        });


        final FrameLayout flHolder = findViewById(R.id.about);

        AboutBuilder builder = AboutBuilder.with(this)
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .setPhoto(R.drawable.opo0432d)
                .setCover(R.mipmap.profile_cover)
                .setLinksAnimated(true)
                .setDividerDashGap(13)
                .setName("Andrew Malitchuk")
                .setSubTitle("Android enthusiast")
                .setLinksColumnsCount(4)
                .addGitHubLink("AndrewMalitchuk")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .setActionsColumnsCount(2)
                .addFeedbackAction("cinema.app.diploma@gmail.com")
                .addIntroduceAction(new Intent(AboutDeveloperActivity.this,StartupActivity.class))
                .addChangeLogAction((Intent) null)
                .setWrapScrollView(true)
                .setShowAsCard(true);

        AboutView view = builder.build();

        flHolder.addView(view);


    }
}

