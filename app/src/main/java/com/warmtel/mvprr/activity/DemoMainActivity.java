package com.warmtel.mvprr.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.warmtel.mvprr.R;
import com.warmtel.mvprr.fragment.cheap.CheapFragment;

public class DemoMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_neary_layout);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.neary_layout, CheapFragment.newInstance())
                .commit();
    }
}
