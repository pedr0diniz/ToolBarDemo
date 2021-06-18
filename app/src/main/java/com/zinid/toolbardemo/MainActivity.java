package com.zinid.toolbardemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.respirarFragment);

        getSupportActionBar().setTitle("Respirar");

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                switch (item.getItemId()) {
                    case R.id.beneficiosFragment:
                        selectedFragment = BeneficiosFragment.newInstance(null, null);
                        getSupportActionBar().setTitle("Benefícios");
                        break;
                    case R.id.respirarFragment:
                        selectedFragment = RespirarFragment.newInstance(null, null);
                        getSupportActionBar().setTitle("Respirar");
                        break;
                    case R.id.sobreFragment:
                        selectedFragment = SobreFragment.newInstance(null, null);
                        getSupportActionBar().setTitle("Sobre");
                        break;
                }
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentContainerView, selectedFragment);
                transaction.commit();

                return true;
            }
        });

    }

}