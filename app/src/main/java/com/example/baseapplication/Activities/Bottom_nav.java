package com.example.baseapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.baseapplication.Fragments.HomeFragment;
import com.example.baseapplication.Fragments.ProfileFragment;
import com.example.baseapplication.Fragments.SearchFragment;
import com.example.baseapplication.R;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class Bottom_nav extends AppCompatActivity {

    private SmoothBottomBar bottomBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_nav);

        //testing
//        Intent intent = new Intent(this,Quiz.class);
//        startActivity(intent);


        replace(new HomeFragment());
        bottomBar = findViewById(R.id.bottomBar);
        bottomBar.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                switch (i){
                    case 0:
                        replace(new HomeFragment());
                        break;

                    case 1:
                        replace(new SearchFragment());
                        break;

                    case 2:
                        replace(new ProfileFragment());
                        break;
                }
                return true;
            }
        });
    }

    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout,fragment);
        transaction.commit();
    }

    public void addQuiz(View view) {
        Intent intent = new Intent(this,CreateQuiz.class);
        startActivity(intent);
    }
}