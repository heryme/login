package p.com.gameproject.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import p.com.gameproject.R;
import p.com.gameproject.utils.SharePref;

public class SplashActivity extends AppCompatActivity {

    private SharePref sharePref ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sharePref = new SharePref(SplashActivity.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Check Login Or Not
                if(sharePref.isLogin()) {
                    Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                    finish();
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        }, 3000);
    }

}
