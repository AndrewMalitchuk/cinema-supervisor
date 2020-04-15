package com.cinema.cinema_supervisor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.cinema.cinema_supervisor.R;
import com.nonzeroapps.whatisnewdialog.NewItemDialog;
import com.nonzeroapps.whatisnewdialog.object.NewFeatureItem;
import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import java.util.ArrayList;

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
                onBackPressed();
            }
        });

        //
        ArrayList<NewFeatureItem> arrayList = new ArrayList<>();

        NewFeatureItem newFeatureItem = new NewFeatureItem();
        newFeatureItem.setFeatureDesc("From now on, you can search all things you need - cinemas, films, tickets and even genre!");
        newFeatureItem.setFeatureTitle("Searching");
        newFeatureItem.setImageResource("https://media.giphy.com/media/cWVlY0EeQZOrm/source.gif");
        arrayList.add(newFeatureItem);

        NewFeatureItem newFeatureItem2 = new NewFeatureItem();
        newFeatureItem2.setFeatureTitle("Notify");
        newFeatureItem2.setFeatureDesc("Get notification when new film arrive and when your chosen film will be shown");
        newFeatureItem2.setImageResource("https://media.giphy.com/media/huyZxIJvtqVeRp7QcS/source.gif");
        arrayList.add(newFeatureItem2);

        NewFeatureItem newFeatureItem3 = new NewFeatureItem();
        newFeatureItem3.setFeatureTitle("Find in map");
        newFeatureItem3.setFeatureDesc("Find your favourite cinema theatre easily via map!");
        newFeatureItem3.setImageResource("https://media.giphy.com/media/3ov9jYkVbdGMo6UcG4/source.gif");
        arrayList.add(newFeatureItem3);

        NewFeatureItem newFeatureItem4 = new NewFeatureItem();
        newFeatureItem4.setFeatureTitle("More information");
        newFeatureItem4.setFeatureDesc("Watch trailer before choosing film.");
        newFeatureItem4.setImageResource("https://media.giphy.com/media/Iyqv0kE4hUwYE/source.gif");
        arrayList.add(newFeatureItem4);
        //


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
                .addChangeLogAction(e->{
                    NewItemDialog
                            .init(getApplicationContext())
                            .setVersionName("1.2.0")
                            .setDialogTitle("New Features of 0.1 Version!")
                            .setPositiveButtonTitle("Close")
                            .setCancelable(true)
//                                .setCancelable(false)
                            .setItems(arrayList)
                            .setCancelButtonListener(new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            })
                            .showDialog(AboutDeveloperActivity.this);
                })
                .setWrapScrollView(true)
                .setShowAsCard(true);

        AboutView view = builder.build();

        flHolder.addView(view);


    }
}

