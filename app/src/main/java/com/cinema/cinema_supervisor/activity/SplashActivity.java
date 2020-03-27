package com.cinema.cinema_supervisor.activity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.cinema.cinema_supervisor.R;
import com.cinema.cinema_supervisor.requests.APIInterface;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {


    APIInterface apiInterface;

    public static final String ACCOUNT_PREF = "accountPref";
    private SharedPreferences sharedpreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.WHITE);
        }

        apiInterface= com.cinema.cinema_supervisor.requests.APIClient.getClient().create(APIInterface.class);

        sharedpreferences = getSharedPreferences(ACCOUNT_PREF, Context.MODE_PRIVATE);

        if (sharedpreferences != null) {

            String login = sharedpreferences.getString("login", null);
            String password = sharedpreferences.getString("password", null);
            int userId = sharedpreferences.getInt("userId", -1);

            if(login!=null||password!=null||userId!=-1){

                RequestBody password_ = RequestBody.create(MediaType.parse("text/plain"),
                        password);

                RequestBody login_ = RequestBody.create(MediaType.parse("text/plain"),
                        login);

                Log.d("splash",password+" "+login);

                Call<com.cinema.client.requests.entities.TokenAPI> call=apiInterface.refreshToken(login_,password_);
                call.enqueue(new Callback<com.cinema.client.requests.entities.TokenAPI>() {
                    @Override
                    public void onResponse(Call<com.cinema.client.requests.entities.TokenAPI> call, Response<com.cinema.client.requests.entities.TokenAPI> response) {
                        if(response.isSuccessful()){
                            Intent intent=new Intent(SplashActivity.this,Main2Activity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<com.cinema.client.requests.entities.TokenAPI> call, Throwable t) {
                        Log.d("FROM","2");
                        Intent intent=new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });

            }else{
                Log.d("FROM","1");
                Intent intent=new Intent(this,LoginActivity.class);
                startActivity(intent);
            }


        }else{
            Log.d("FROM","0");
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
        }




    }
}
