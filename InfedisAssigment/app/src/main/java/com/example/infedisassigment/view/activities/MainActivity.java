package com.example.infedisassigment.view.activities;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import com.example.infedisassigment.R;
import com.example.infedisassigment.view.fragments.HomeFragment;
import com.example.infedisassigment.view.fragments.MainFragment;

public class MainActivity extends AppCompatActivity {

    public static Toolbar toolbar;
    public static TextView titleMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        openFragment();
    }

    private void initViews(){
        toolbar = findViewById(R.id.toolbar);
        titleMain = toolbar.findViewById(R.id.titleMain);
    }

    private void openFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, new HomeFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}