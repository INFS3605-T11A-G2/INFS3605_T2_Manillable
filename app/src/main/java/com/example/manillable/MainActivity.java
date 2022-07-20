package com.example.manillable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;

import com.example.manillable.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new HomeFragment());


        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch(item.getItemId()){
                case R.id.home:
                    replaceFragment(new HomeFragment());
                    Log.d(TAG, "Home Fragment: Run");
                    break;
                case R.id.clients:
                    replaceFragment(new ClientsFragment());
                    Log.d(TAG, "Client Fragment: Run");
                    break;
                case R.id.camera:
                    replaceFragment(new CameraFragment());
                    Log.d(TAG, "Camera Fragment: Run");
                    break;
                case R.id.invoices:
                    replaceFragment(new InvoiceFragment());
                    Log.d(TAG, "Invoice Fragment: Run");
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    Log.d(TAG, "Profile Fragment: Run");
                    break;
            }
            return true;
        });


    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
        fragmentTransaction.commit();
        Log.d(TAG, "Fragment Replaced");
    }

}