package com.example.car_founder_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
public class MainActivity extends AppCompatActivity {



    private DrawerLayout drawerLayout;

    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar, R.string.open_nav, R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            replaceFragment(new HomeFragment());
            navigationView.setCheckedItem(R.id.nav_profile);
        }


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.nav_profile) {
                    // Handle click on "Map" item
                    replaceFragment(new profile());
                    return true;
                }

                else if (item.getItemId() == R.id.nav_parts) {
                    // Handle click on "Settings" item
                    replaceFragment(new parts());
                    return true;
                }

                else if (item.getItemId() == R.id.nav_cars) {
                    // Handle click on "Settings" item
                    replaceFragment(new newcars());
                    return true;
                }
                else if (item.getItemId() == R.id.nav_about) {
                    // Handle click on "Settings" item
                    replaceFragment(new about_us());
                    return true;
                }
                else if (item.getItemId() == R.id.nav_logout) {
                    // Handle click on "Settings" item
                    Intent intent = new Intent(MainActivity.this,AuthActivity.class);
                    return true;
                }


                return false;
            }
        });



    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
