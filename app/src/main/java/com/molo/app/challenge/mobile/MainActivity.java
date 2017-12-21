package com.molo.app.challenge.mobile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.molo.app.challenge.mobile.fragments.TrendingFragment;
import com.molo.app.challenge.mobile.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            android.support.v4.app.FragmentManager fragmentManager=getSupportFragmentManager();
            android.support.v4.app.FragmentTransaction transaction=fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.trend:
                    transaction.add(R.id.fragment, TrendingFragment.newInstance()).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        requestQueue= Volley.newRequestQueue(this);
        Utils.httpInit(requestQueue);
        getSupportFragmentManager().beginTransaction().add(R.id.fragment,TrendingFragment.newInstance()).commit();
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
