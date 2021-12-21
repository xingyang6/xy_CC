package com.example.a12_13.live;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.a12_13.R;
import com.example.a12_13.util.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class live_home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_home);
        BottomNavigationView navView = findViewById(R.id.huhao_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_live_huhao)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.huhao_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}