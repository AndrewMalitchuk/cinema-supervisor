package com.cinema.cinema_supervisor.activity;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.brouding.simpledialog.SimpleDialog;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.cinema.cinema_supervisor.R;
import com.cinema.cinema_supervisor.requests.APIClient;
import com.cinema.cinema_supervisor.requests.APIInterface;
import com.cinema.cinema_supervisor.requests.entities.CinemaAPI;
import com.cinema.client.requests.entities.TokenAPI;
import com.developer.mtextfield.ExtendedEditText;
import com.droidbyme.dialoglib.DroidDialog;
import com.dynamitechetan.flowinggradient.FlowingGradientClass;
import com.github.javiersantos.bottomdialogs.BottomDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.keiferstone.nonet.NoNet;
import com.liangfeizc.avatarview.AvatarView;
import com.pd.chocobar.ChocoBar;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.markdownview.Config;
import es.dmoral.markdownview.MarkdownView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import jp.wasabeef.glide.transformations.BlurTransformation;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.samuelwall.materialtaptargetprompt.MaterialTapTargetPrompt;

public class Main2Activity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.linLayout)
    LinearLayout linLayout;

    @BindView(R.id.floatingActionButton3)
    FloatingActionButton floatingActionButton;

    @BindView(R.id.usernameMainActivityTextView)
    TextView usernameMainActivityTextView;

    @BindView(R.id.positionMainActivityTextView)
    TextView positionMainActivityTextView;

    @BindView(R.id.imageView5)
    ImageView blurImageView;

    @BindView(R.id.cinemaPictureCinemaActivityAvatarView)
    AvatarView cinemaPictureCinemaActivityAvatarView;

    @BindView(R.id.cinemaMainActivityTextView)
    TextView cinemaMainActivityTextView;

    public static final String ACCOUNT_PREF = "accountPref";

    private SharedPreferences prefForCheckingFirstRun;
    private SharedPreferences.Editor editor;
    private SharedPreferences pref;


    private SharedPreferences sharedpreferences;


    private boolean firstRun = false;

    private APIInterface apiInterface;


    private int user_id;

    private CinemaAPI currentCinema;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ButterKnife.bind(this);


        prefForCheckingFirstRun = getSharedPreferences("com.cinema.supervisor", MODE_PRIVATE);


        pref = getApplicationContext().getSharedPreferences("UserData", 0);
        editor = pref.edit();

        String userNameFromPreferences = pref.getString("user_name", null);
        if (userNameFromPreferences != null && !userNameFromPreferences.equals("")) {
            toolbar.setTitle("Hello, " + userNameFromPreferences);
        } else {
            toolbar.setTitle("Hello, $username!");
        }
        setSupportActionBar(toolbar);


        //
        apiInterface = com.cinema.cinema_supervisor.requests.APIClient.getClient().create(APIInterface.class);
        sharedpreferences = getSharedPreferences(ACCOUNT_PREF, Context.MODE_PRIVATE);
        if (sharedpreferences != null) {
            String login = sharedpreferences.getString("login", null);
            String password = sharedpreferences.getString("password", null);
            user_id = sharedpreferences.getInt("userId", -1);

            Log.d("sdf","OK");

            RequestBody password_ = RequestBody.create(MediaType.parse("text/plain"),
                    password);

            RequestBody login_ = RequestBody.create(MediaType.parse("text/plain"),
                    login);

            Observable<com.cinema.client.requests.entities.TokenAPI> tokenRx = apiInterface.refreshTokenRx(login_, password_);
//
            tokenRx.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .map(result -> result)
                    .subscribe(new Consumer<TokenAPI>() {
                        @Override
                        public void accept(TokenAPI tokenAPI) throws Exception {
                            onToken(tokenAPI);
                            getJob(tokenAPI);
                        }
                    });
        }
        //




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

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.action_settings:
                        MarkdownView markdownView = new MarkdownView(Main2Activity.this);
                        markdownView.loadFromText("# Hello!\n ## Hello!\n **сюда кинь вступ з звіту**");
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
                                "mailto", "cinema.app.diploma@gmail.com", null));
//                        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Cinema-App Feedback");
//                        emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                        startActivity(Intent.createChooser(emailIntent, "Send email..."));
                        break;
                    case R.id.action_change_name:

                        AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
                        builder.setTitle("Your information");
                        // set the custom layout
                        final View customLayout = getLayoutInflater().inflate(R.layout.change_name_dialog, null);
                        builder.setView(customLayout);
                        // add a button
                        builder.setPositiveButton("Great!", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // send data from the AlertDialog to the Activity
                                ExtendedEditText userName = customLayout.findViewById(R.id.extended_edit_text1);

//                                sendDialogDataToActivity(editText.getText().toString());
                                editor.putString("user_name", userName.getText().toString());
//                                editor.putString("user_city", userCity.getText().toString());
                                editor.commit(); // commit changes

                                ChocoBar.builder().setActivity(Main2Activity.this)
                                        .setText("Success!")
                                        .setDuration(ChocoBar.LENGTH_SHORT)
                                        .build()
                                        .show();

                                Intent intent = getIntent();
                                finish();
                                startActivity(intent);
                            }
                        });
                        // create and show the alert dialog
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        break;
                    case R.id.action_about_developer:
                        startActivity(new Intent(Main2Activity.this, AboutDeveloperActivity.class));
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

//                                        pref.edit().clear();
//                                        pref.edit().commit();
//
//                                        getSharedPreferences(ACCOUNT_PREF, Context.MODE_PRIVATE).edit().clear();
//                                        getSharedPreferences(ACCOUNT_PREF, Context.MODE_PRIVATE).edit().remove("login");
//                                        getSharedPreferences(ACCOUNT_PREF, Context.MODE_PRIVATE).edit().remove("password");
//                                        getSharedPreferences(ACCOUNT_PREF, Context.MODE_PRIVATE).edit().apply();
//                                        getSharedPreferences(ACCOUNT_PREF, Context.MODE_PRIVATE).edit().commit();

                                        getApplicationContext().getSharedPreferences("ACCOUNT_PREF", 0).edit().clear().commit();


                                        Intent intent = new Intent(Main2Activity.this, LoginActivity.class);

                                        startActivity(intent);
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
            }
        });

        if (prefForCheckingFirstRun.getBoolean("firstrun", true) == true) {


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


        //



        //




    }

    private void onToken(com.cinema.client.requests.entities.TokenAPI tokenAPI) {
        Call<com.cinema.client.requests.entities.UserAPI> getLoggedUser = apiInterface.getCurrentUser("Bearer " + tokenAPI.getAccess());
        getLoggedUser.enqueue(new Callback<com.cinema.client.requests.entities.UserAPI>() {
            @Override
            public void onResponse(Call<com.cinema.client.requests.entities.UserAPI> call, Response<com.cinema.client.requests.entities.UserAPI> response) {
                if (response.isSuccessful()) {


                    Log.d("is_staff",response.body().getIsStaff()+"");

                    if(response.body().getIsStaff()) {

                        usernameMainActivityTextView.setText(response.body().getLastName()+" "+response.body().getFirstName());
                        positionMainActivityTextView.setText("Staff");


                    }
                }
            }

            @Override
            public void onFailure(Call<com.cinema.client.requests.entities.UserAPI> call, Throwable t) {

            }
        });
    }

    private void getJob(TokenAPI tokenAPI){
        Call<CinemaAPI> getJobCall=apiInterface.getJobByUserId(user_id,"Bearer " + tokenAPI.getAccess());
        getJobCall.enqueue(new Callback<CinemaAPI>() {
            @Override
            public void onResponse(Call<CinemaAPI> call, Response<CinemaAPI> response) {
                Log.d("cinema",response.body().getName());

                currentCinema=response.body();

                Glide.with(getApplicationContext())
                        .load(APIClient.HOST + response.body().getPicUrl())
                        .apply(RequestOptions.bitmapTransform(new BlurTransformation(25, 3)))
                        .into(blurImageView);

                Glide.with(getApplicationContext()).load(APIClient.HOST + response.body().getPicUrl()).into(cinemaPictureCinemaActivityAvatarView);

                cinemaMainActivityTextView.setText(response.body().getName());


            }

            @Override
            public void onFailure(Call<CinemaAPI> call, Throwable t) {

            }
        });
    }

    public void onImageClick(View view) {
        Intent intent = new Intent(Main2Activity.this, ZoomImageActivity.class);
        intent.putExtra("url", currentCinema.getPicUrl());
        startActivity(intent);
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
