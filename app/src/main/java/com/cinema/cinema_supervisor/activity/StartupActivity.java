package com.cinema.cinema_supervisor.activity;

import android.content.Intent;
import android.os.Bundle;

import com.cinema.cinema_supervisor.R;
import com.cuneytayyildiz.onboarder.OnboarderActivity;
import com.cuneytayyildiz.onboarder.OnboarderPage;
import com.cuneytayyildiz.onboarder.utils.OnboarderPageChangeListener;

import java.util.Arrays;
import java.util.List;

public class StartupActivity extends OnboarderActivity implements OnboarderPageChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<OnboarderPage> pages = Arrays.asList(
                new OnboarderPage.Builder()
                        .title("Cinema-App")
                        .description("Buy tickets easily!")
                        .imageResourceId( R.drawable.ic_ticket_white)
                        .imageSizeDp(256,256)
                        .backgroundColorId(R.color.startup_5)
                        .titleColorId(R.color.colorLoginActivityText)
                        .descriptionColorId(R.color.colorLoginActivityText)
                        .multilineDescriptionCentered(true)
                        .build(),
                new OnboarderPage.Builder()
                        .title("Films")
                        .description("Find films you really enjoy!")
                        .imageResourceId( R.drawable.ic_undraw_apps_m7mh)
                        .imageSizeDp(256,256)
                        .backgroundColorId(R.color.startup_1)
                        .titleColor(R.color.about_item_text_color)
                        .descriptionColor(R.color.about_item_text_color)
                        .multilineDescriptionCentered(true)
                        .build(),
                new OnboarderPage.Builder()
                        .title("Cinema")
                        .description("Find the best cinema hall nearby")
                        .imageResourceId( R.drawable.ic_undraw_map_1r69)
                        .imageSizeDp(256,256)
                        .backgroundColorId(R.color.startup_2)
                        .titleColor(R.color.about_item_text_color)
                        .descriptionColor(R.color.about_item_text_color)
                        .multilineDescriptionCentered(true)
                        .build(),
                new OnboarderPage.Builder()
                        .title("Ticket")
                        .description("Store and use e-tickets")
                        .imageResourceId( R.drawable.ic_undraw_address_udes)
                        .imageSizeDp(256,256)
                        .backgroundColorId(R.color.startup_3)
                        .titleColor(R.color.about_item_text_color)
                        .descriptionColor(R.color.about_item_text_color)
                        .multilineDescriptionCentered(true)
                        .build(),
                new OnboarderPage.Builder()
                        .title("Notification")
                        .description("Get notification when film will shown soon")
                        .imageResourceId( R.drawable.ic_undraw_reminders_697p)
                        .imageSizeDp(256,256)
                        .backgroundColorId(R.color.startup_4)
                        .titleColor(R.color.about_item_text_color)
                        .descriptionColor(R.color.about_item_text_color)
                        .multilineDescriptionCentered(true)
                        .build()
        );
        setOnboarderPageChangeListener(this);
        initOnboardingPages(pages);
    }

    @Override
    public void onFinishButtonPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onSkipButtonPressed() {
        super.onSkipButtonPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPageChanged(int position) {

    }

}

