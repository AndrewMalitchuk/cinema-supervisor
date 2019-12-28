package com.cinema.cinema_supervisor.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.brouding.simpledialog.SimpleDialog;
import com.cinema.cinema_supervisor.R;
import com.droidbyme.dialoglib.DroidDialog;
import com.dynamitechetan.flowinggradient.FlowingGradientClass;
import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.keiferstone.nonet.NoNet;
import com.pd.chocobar.ChocoBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.markdownview.Config;
import es.dmoral.markdownview.MarkdownView;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class Main2Activity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.linLayout)
    LinearLayout linLayout;

    @BindView(R.id.floatingActionButton3)
    FloatingActionButton floatingActionButton;


    private SharedPreferences prefForCheckingFirstRun;
    private SharedPreferences.Editor editor;

    private boolean firstRun = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);


        prefForCheckingFirstRun = getSharedPreferences("com.cinema.supervisor", MODE_PRIVATE);

        toolbar.setTitle("Hello, $username!");


        NoNet.configure()
                .endpoint("https://google.com")
                .timeout(5)
                .connectedPollFrequency(60)
                .disconnectedPollFrequency(1);

        NoNet.monitor(this)
                .poll()
                .snackbar();

        FlowingGradientClass grad = new FlowingGradientClass();
        grad.setBackgroundResource(R.drawable.translate)
                .onLinearLayout(linLayout)
                .setTransitionDuration(4000)
                .start();

        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(item -> {

            switch (item.getItemId()) {
                case R.id.action_settings:
                    MarkdownView markdownView = new MarkdownView(Main2Activity.this);
                    markdownView.loadFromText("# Hello!\n ## Hello!");
                    Config defaultConfig = Config.getDefaultConfig();
                    defaultConfig.setDefaultMargin(25);
                    markdownView.setCurrentConfig(defaultConfig);
                    new SimpleDialog.Builder(Main2Activity.this)
                            .setTitle("Need help?")
                            .setCustomView(markdownView)
                            .setBtnConfirmText("Thanks!")
                            .setBtnConfirmTextSizeDp(16)
                            .setBtnConfirmTextColor("#1fd1ab")
                            .show();
                    break;
                case R.id.action_feedback:
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                            "mailto", "cinema.app.feedback@gmail.com", null));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Cinema-App Feedback");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                    startActivity(Intent.createChooser(emailIntent, "Send email..."));
                    break;
                case R.id.action_logout:
                    new DroidDialog.Builder(Main2Activity.this)
                            .icon(R.drawable.ic_exit_to_app_black_24dp)
                            .title("Logout")
                            .content("Are you sure about this?")
                            .cancelable(true, false)
                            .positiveButton("Yes, I'm sure", new DroidDialog.onPositiveListener() {
                                @Override
                                public void onPositive(Dialog droidDialog) {
                                    Toast.makeText(Main2Activity.this, "Yes, I'm sure", Toast.LENGTH_SHORT).show();
                                    //
                                    startActivity(new Intent(Main2Activity.this, LoginActivity.class));
                                    //
                                }
                            })
                            .negativeButton("No", new DroidDialog.onNegativeListener() {
                                @Override
                                public void onNegative(Dialog droidDialog) {
                                    Toast.makeText(Main2Activity.this, "No", Toast.LENGTH_SHORT).show();
                                    //
                                    Intent intent = getIntent();
                                    finish();
                                    startActivity(intent);
                                    //
                                }
                            })
                            .color(ContextCompat.getColor(Main2Activity.this, R.color.colorAccent), ContextCompat.getColor(Main2Activity.this, R.color.white),
                                    ContextCompat.getColor(Main2Activity.this, R.color.colorAccent))
                            .show();

                    break;
            }


            return false;
        });

        if (prefForCheckingFirstRun.getBoolean("firstrun", true) == true) {

            //
            new MaterialTapTargetPrompt.Builder(Main2Activity.this)
                    .setTarget(R.id.floatingActionButton3)
                    .setPrimaryText("Send your first email")
                    .setSecondaryText("Tap the envelope to start composing your first email")
                    .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                        @Override
                        public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                            if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED) {
                                new MaterialTapTargetPrompt.Builder(Main2Activity.this)
                                        .setTarget(R.id.toolbar)
                                        .setPrimaryText("Send your first email")
                                        .setSecondaryText("Tap the envelope to start composing your first email")
//                                        .setIcon(R.drawable.ic_account_circle_black_24dp)
                                        .setPromptStateChangeListener(new MaterialTapTargetPrompt.PromptStateChangeListener() {
                                            @Override
                                            public void onPromptStateChanged(MaterialTapTargetPrompt prompt, int state) {
                                                if (state == MaterialTapTargetPrompt.STATE_FOCAL_PRESSED) {
                                                    // User has pressed the prompt target
                                                }
                                            }
                                        })
                                        .show();
                            }
                        }
                    })
                    .show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);


        return super.onCreateOptionsMenu(menu);
    }

    public void onFabClick(View view) {


        Intent intent = new Intent(
                Main2Activity.this, ScanQRActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        String barCode = data.getStringExtra("barcode");
        String barCodeType = data.getStringExtra("barType");

        new BottomDialog.Builder(this)
                .setTitle("QR scan info")
//                .setContent("Film: Once upon a time in Hollywood\nDate: 10-10-10\nPlace: c-10-10\nCosmos")
                .setContent("Barcode: " + barCode + "\nType: " + barCodeType)
                .setPositiveText("Submit")
                .setPositiveBackgroundColorResource(R.color.colorAccent)
                .setPositiveTextColorResource(android.R.color.white)
                .onPositive(dialog -> ChocoBar.builder().setActivity(Main2Activity.this)
                        .setText("Submitted!")
                        .setDuration(ChocoBar.LENGTH_SHORT)
                        .green()
                        .show())
                .setNegativeText("Ignore")
                .setNegativeTextColorResource(R.color.colorPrimary)
                .onNegative(dialog -> ChocoBar.builder().setActivity(Main2Activity.this)
                        .setText("Ignored!")
                        .setDuration(ChocoBar.LENGTH_SHORT)
                        .red()
                        .show())
                .setCancelable(false)
                .show();


    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefForCheckingFirstRun.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            prefForCheckingFirstRun.edit().putBoolean("firstrun", false).commit();
        }
    }
}
