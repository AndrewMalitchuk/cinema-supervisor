package com.cinema.cinema_supervisor.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.cinema.cinema_supervisor.R;
import com.cinema.cinema_supervisor.requests.APIInterface;
import com.cinema.cinema_supervisor.requests.entities.TokenAPI;
import com.cinema.cinema_supervisor.requests.entities.UserAPI;
import com.dynamitechetan.flowinggradient.FlowingGradientClass;
import com.example.myloadingbutton.MyLoadingButton;
import com.pd.chocobar.ChocoBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener, MyLoadingButton.MyLoadingButtonClick {

    @BindView(R.id.loginLinearLayout)
    LinearLayout loginLinearLayout;

    @BindView(R.id.loginLoginActivityButton)
    MyLoadingButton loginLoginActivityButton;

    @BindView(R.id.emailSignUpActivityEditText)
    EditText emailSignUpActivityEditText;

    @BindView(R.id.passwordSignUpActivityEditText)
    EditText passwordSignUpActivityEditText;

    public static final String ACCESS_TOKEN_PREF = "accessToken";

    public static final String REFRESH_TOKEN_PREF = "refreshToken";

    private SharedPreferences sharedpreferences_access;

    private SharedPreferences sharedpreferences_token;

    public static final String ACCOUNT_PREF = "accountPref";

    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        ButterKnife.bind(this);
        FlowingGradientClass grad = new FlowingGradientClass();
        grad.setBackgroundResource(R.drawable.translate)
                .onLinearLayout(loginLinearLayout)
                .setTransitionDuration(4000)
                .start();
        getApplicationContext().getSharedPreferences("accountPref", 0).edit().clear().commit();
        loginLoginActivityButton.setMyButtonClickListener(this);
        setLoadingButtonStyle();
    }

    /**
     * Initialize loading button according to documentation
     */
    private void setLoadingButtonStyle() {
        loginLoginActivityButton.setAnimationDuration(500)
                .setButtonLabel(getResources().getString(R.string.loginLoginActivityButtonString).toString())
                .setButtonLabelSize(20)
                .setProgressLoaderColor(R.color.colorLoginActivityCardView)
                .setButtonLabelColor(R.color.colorLoginActivityCardView)
                .setProgressDoneIcon(getResources().getDrawable(R.drawable.ic_check_white_24dp)) // Set MyLoadingButton done icon drawable.
                .setProgressErrorIcon(getResources().getDrawable(R.drawable.ic_clear_white_24dp)) //Set MyLoadingButton error icon drawable.
                .setNormalAfterError(true);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        loginLoginActivityButton.showNormalButton();
    }

    /**
     * Handler for LoadingButton; perform login requests
     */
    @Override
    public void onMyLoadingButtonClick() {
        APIInterface apiInterface = com.cinema.cinema_supervisor.requests.APIClient.getClient().create(APIInterface.class);
        if (emailSignUpActivityEditText.getText().equals("") || passwordSignUpActivityEditText.getText().equals("") ||
                emailSignUpActivityEditText.getText().length() == 0 || passwordSignUpActivityEditText.getText().length() == 0) {
            ChocoBar
                    .builder()
                    .setActivity(LoginActivity.this)
                    .setText("Please, check fields")
                    .setDuration(ChocoBar.LENGTH_LONG)
                    .red()
                    .show();
            loginLoginActivityButton.showErrorButton();
        } else {
            if (passwordSignUpActivityEditText.getText().length() < 8) {
                ChocoBar
                        .builder()
                        .setActivity(LoginActivity.this)
                        .setText("Password length must be more than 8 symbols")
                        .setDuration(ChocoBar.LENGTH_LONG)
                        .red()
                        .show();
                loginLoginActivityButton.showErrorButton();
            } else {
                setLoadingButtonStyle();
                RequestBody password_ = RequestBody.create(MediaType.parse("text/plain"),
                        passwordSignUpActivityEditText.getText().toString());
                RequestBody username_ = RequestBody.create(MediaType.parse("text/plain"),
                        emailSignUpActivityEditText.getText().toString().split("@")[0]);
                Call<TokenAPI> call = apiInterface.refreshToken(username_, password_);
                call.enqueue(new Callback<TokenAPI>() {

                    @Override
                    public void onResponse(Call<TokenAPI> call, Response<TokenAPI> response) {
                        loginLoginActivityButton.showDoneButton();
                        if (response.isSuccessful()) {
                            sharedpreferences_access = getSharedPreferences(ACCESS_TOKEN_PREF, Context.MODE_PRIVATE);
                            sharedpreferences_token = getSharedPreferences(REFRESH_TOKEN_PREF, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor_access = sharedpreferences_access.edit();
                            SharedPreferences.Editor editor_refresh = sharedpreferences_token.edit();
                            editor_access.putString("accessToken", response.body().getAccess());
                            editor_refresh.putString("refreshToken", response.body().getRefresh());
                            editor_refresh.putString("username", emailSignUpActivityEditText.getText().toString().split("@")[0]);
                            editor_refresh.putString("password", passwordSignUpActivityEditText.getText().toString());
                            editor_access.commit();
                            editor_refresh.commit();
                            sharedpreferences = getSharedPreferences(ACCOUNT_PREF, Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            Call<UserAPI> getLoggedUser = apiInterface.getCurrentUser("Bearer " + response.body().getAccess());
                            getLoggedUser.enqueue(new Callback<UserAPI>() {

                                @Override
                                public void onResponse(Call<UserAPI> call, Response<UserAPI> response) {
                                    if (response.isSuccessful()) {
                                        if(response.body().getIsStaff()) {
                                            SharedPreferences.Editor editor = sharedpreferences.edit();
                                            editor.putInt("userId", response.body().getId());
                                            editor.commit();
                                        }else{
                                            loginLoginActivityButton.showErrorButton();
                                            ChocoBar
                                                    .builder()
                                                    .setActivity(LoginActivity.this)
                                                    .setText("Check your data, please.")
                                                    .setDuration(ChocoBar.LENGTH_LONG)
                                                    .red()
                                                    .show();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<UserAPI> call, Throwable t) {
                                    Intent intent = new Intent(LoginActivity.this,ErrorActivity.class);
                                    intent.putExtra("isNetworkError",true);
                                    startActivity(intent);

                                }

                            });
                            editor.putString("token", response.body().getAccess());
                            editor.putString("login", emailSignUpActivityEditText.getText().toString().split("@")[0]);
                            editor.putString("password", passwordSignUpActivityEditText.getText().toString());
                            editor.commit();
                            ChocoBar
                                    .builder()
                                    .setActivity(LoginActivity.this)
                                    .setText("Success!")
                                    .setDuration(ChocoBar.LENGTH_LONG)
                                    .green()
                                    .setAction("LOGIN", new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        }
                                    })
                                    .show();
                        } else {
                            loginLoginActivityButton.showErrorButton();
                            ChocoBar
                                    .builder()
                                    .setActivity(LoginActivity.this)
                                    .setText("Check your data, please.")
                                    .setDuration(ChocoBar.LENGTH_LONG)
                                    .red()
                                    .show();
                        }
                    }

                    @Override
                    public void onFailure(Call<TokenAPI> call, Throwable t) {
                        loginLoginActivityButton.showErrorButton();
                        ChocoBar
                                .builder()
                                .setActivity(LoginActivity.this)
                                .setText(t.getLocalizedMessage())
                                .setDuration(ChocoBar.LENGTH_LONG)
                                .red()
                                .show();

                    }

                });
            }
        }
    }

}
