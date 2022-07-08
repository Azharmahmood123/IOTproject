package com.fyp.iot.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private TextView tvWelcome;
    private Button btnLogout;
    private LinearLayout layoutAlert;
    private TextView tvMessage;
    private TextView tvBPM;
    private TextView tvSpo;
    private TextView tvTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        configure();

    }

    private void findViews() {
        tvWelcome = findViewById(R.id.tvWelcome);
        btnLogout = findViewById(R.id.btnLogout);

        layoutAlert = findViewById(R.id.layoutAlert);
        tvMessage = findViewById(R.id.tvMessage);
        tvBPM = findViewById(R.id.tvBPM);
        tvSpo = findViewById(R.id.tvSpo);
        tvTemp = findViewById(R.id.tvTemp);
    }

    private void configure() {
        String welcomeText = "Welcome " + Utils.getStringPrefs(Utils.PrefConstants.PREF_USER_EMAIL);
        tvWelcome.setText(welcomeText);

        btnLogout.setOnClickListener(v -> {
            Utils.setPrefs(Utils.PrefConstants.PREF_USER_EMAIL, "");
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });

        loadLiveData();
    }

    private void loadLiveData() {
        if (NetworkUtil.isNetworkAvailable(MainActivity.this)) {
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference databaseReference = firebaseDatabase.getReference();
            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        DataModel dataModel = snapshot.getValue(DataModel.class);
                        if (dataModel != null) {

                            if (dataModel.getAlert().length() > 0) {
                                layoutAlert.setVisibility(View.VISIBLE);
                                tvMessage.setText(dataModel.getAlert());
                            } else {
                                layoutAlert.setVisibility(View.GONE);
                            }

                            tvBPM.setText(dataModel.getBPM());
                            tvSpo.setText(dataModel.getSpo2());
                            tvTemp.setText(dataModel.getTemp());

                        }
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        } else {
            Utils.showToast("No internet connection found.");
        }
    }
}