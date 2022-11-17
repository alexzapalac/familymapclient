package com.example.familymapclient.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.familymapclient.R;
import com.example.familymapclient.model.Model;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Model.getInstance().getAuthtoken() == null) {
            // If we don't have an auth token that means the user hasn't logged in yet
            startLoginFragment();
        } else {
            //startMapFragment();
        }
    }

    private void startLoginFragment() {
        FragmentManager fm = this.getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.mainActivityFragmentFrameLayout,
                new LoginFragment()).commit();
    }

    public void startMapFragment() {
        FragmentManager fm = this.getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.mainActivityFragmentFrameLayout,
                new MapFragment()).commit();
    }

}
