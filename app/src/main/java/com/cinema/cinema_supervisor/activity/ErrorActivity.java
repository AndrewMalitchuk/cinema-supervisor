package com.cinema.cinema_supervisor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cinema.cinema_supervisor.R;
import com.medialablk.easygifview.EasyGifView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ErrorActivity extends AppCompatActivity {


    @BindView(R.id.errorTitle)
    TextView errorTitle;

    @BindView(R.id.errorDescription)
    TextView errorDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        ButterKnife.bind(this);


        boolean isNetworkError=getIntent().getBooleanExtra("isNetworkError",false);
        boolean isAppError=getIntent().getBooleanExtra("isAppError",false);

        EasyGifView easyGifView = (EasyGifView) findViewById(R.id.easyGifView);

        if(!isAppError&&!isNetworkError){
            easyGifView.setGifFromResource(R.drawable.cat_fail_0); //Your own GIF file from Resources
            errorTitle.setText("The weather on Mars has changed");
            errorDescription.setText("We do not know what happened, but we are correcting it already.");
        }else if(isAppError){
            easyGifView.setGifFromResource(R.drawable.app_error); //Your own GIF file from Resources
            errorTitle.setText("It's fine");
            errorDescription.setText("Programs are created by human beings, and human beings are fallible.");
        }else{
            easyGifView.setGifFromResource(R.drawable.net_error); //Your own GIF file from Resources
            errorTitle.setText("You are in the Mesozoic");
            errorDescription.setText("Maybe talk to others, and in the meantime, the Internet will appear");
        }






//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        );
    }

    public void onUnderstandButtonClick(View view){
        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
    }

}
