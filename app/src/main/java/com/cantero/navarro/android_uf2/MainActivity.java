package com.cantero.navarro.android_uf2;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private final String TAG = "MOVILES_UF2";
    private Button clickButton;
    private Button startButton;
    private EditText nameEditText;
    private int clicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        clickButton = findViewById(R.id.clickButton);
        startButton = findViewById(R.id.startButton);
        nameEditText = findViewById(R.id.nameEditText);
        clickButton.setEnabled(false);


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String buttonStatus = startButton.getText().toString();

                if (buttonStatus == getText(R.string.START)) {
                    startButton.setText(R.string.STOP);
                    clickButton.setEnabled(true);
                    clicks = 0;
                    clickButton.setText(String.valueOf(clicks));

                } else if (buttonStatus == getText(R.string.STOP)) {
                    String name = nameEditText.getText().toString();

                    clickButton.setEnabled(false);
                    startButton.setText(R.string.START);
                    addFirebaseData(name, clicks);
                    Toast.makeText(getApplicationContext(),
                            "Uploaded to firebase your score of: " + clicks + " and your name: " + name, Toast.LENGTH_LONG).show();
                }
            }
        });

        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicks++;
                clickButton.setText(String.valueOf(clicks));
            }
        });


    }

    public void addFirebaseData(String name, int number) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("clicks");
        myRef.push().setValue(new Click(number, name));

    }
}
