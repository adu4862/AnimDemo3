package com.yl.animdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/6 0006.
 */

public class SplashActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah);
        ButterKnife.bind(this);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;

        switch (view.getId()) {
            case R.id.button:
                intent = new Intent(this,PaddingTopAnimActivity.class);
                break;
            case R.id.button2:
                intent = new Intent(this,HeightAnimActivity.class);
                break;
        }
        startActivity(intent);

    }
}
