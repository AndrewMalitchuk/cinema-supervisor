package com.cinema.cinema_supervisor.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cinema.cinema_supervisor.R;
import com.dynamitechetan.flowinggradient.FlowingGradientClass;
import com.example.myloadingbutton.MyLoadingButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements
        View.OnClickListener, MyLoadingButton.MyLoadingButtonClick{




    @BindView(R.id.loginLinearLayout)
    LinearLayout loginLinearLayout;

    @BindView(R.id.loginLoginActivityButton)
    MyLoadingButton loginLoginActivityButton;

    @BindView(R.id.signUpLoginActivityTextView)
    TextView signUpLoginActivityTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        ButterKnife.bind(this);


        // Gradient
        FlowingGradientClass grad = new FlowingGradientClass();
        grad.setBackgroundResource(R.drawable.translate)
                .onLinearLayout(loginLinearLayout)
                .setTransitionDuration(4000)
                .start();

        //
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );


        // LoadingButton
        loginLoginActivityButton.setMyButtonClickListener(this);
        setLoadingButtonStyle();

        // SignUp TextView
        signUpLoginActivityTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Click",Toast.LENGTH_SHORT).show();
//                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
            }
        });


//        getWindow().setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//        );


    }

    private void setLoadingButtonStyle(){
        loginLoginActivityButton.setAnimationDuration(500)
                .setProgressDoneIcon(getResources().getDrawable(R.drawable.ic_check_black_24dp)) // Set MyLoadingButton done icon drawable.
                .setProgressErrorIcon(getResources().getDrawable(R.drawable.ic_clear_black_24dp)); //Set MyLoadingButton error icon drawable.

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        loginLoginActivityButton.showNormalButton();
    }

    @Override
    public void onMyLoadingButtonClick() {
        Toast.makeText(this, "MyLoadingButton Click", Toast.LENGTH_SHORT).show();

    }
}
