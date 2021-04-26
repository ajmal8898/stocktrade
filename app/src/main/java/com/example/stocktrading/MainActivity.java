package com.example.stocktrading;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation topAnimation, bottomAnimation;
    ImageView ivAppIcon;
    TextView tvAppName, tvAppDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        ivAppIcon = findViewById(R.id.ivAppIcon);
        tvAppName = findViewById(R.id.tvAppName);
        tvAppDescription = findViewById(R.id.tvAppDescription);
        ivAppIcon.setAnimation(topAnimation);
        tvAppName.setAnimation(bottomAnimation);
        tvAppDescription.setAnimation(bottomAnimation);
        int SPLASH_SCREEN = 5000;
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN);
    }
}
