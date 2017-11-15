package com.example.admin.zoodirectory;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ViewAnimalActivity extends AppCompatActivity {
    TextView tvAnimalName;
    TextView tvAnimalInfo;
    public static final String ZEBRA = "Zebras are wild animals that are spotted and can usually seen in places like Australia or Africa.";
    public static final String TIGER   = "Tigers are wild animals that are typically dangerous to humans.  Best not to approach them. They may eat you.";
    public static final String PENGUIN = "Penguins live in mostly arctic places such as Antarctica or the North and South pole.  They are generally harmless animals.";
    public static final String BAT = "Bats are usually nocturnal animals.  They tend to congregate in dark places like caves.  They're also blind";
    public static final String EAGLE = "Eagles are considered an engangered species.  You may never see one in your life.  The bald eagle is considered a patriotic symbol";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animal);
        String animal = getIntent().getExtras().getString("animal");
        Log.d("ViewAnimalActivity", "onCreate: last page, animal equals" + animal);
        tvAnimalName = findViewById(R.id.tvAnimalName);
        tvAnimalInfo  = findViewById(R.id.tvAnimalInfo);
        MediaPlayer mp;
        switch (animal) {
            case "Zebra":
                tvAnimalName.setText(animal);
                tvAnimalInfo.setText(ZEBRA);
                 mp = MediaPlayer.create(getApplicationContext(), R.raw.zebra7);
                mp.start();
                break;

            case "Tiger":
                tvAnimalName.setText(animal);
                tvAnimalInfo.setText(TIGER);
                 mp = MediaPlayer.create(getApplicationContext(), R.raw.tiger6);
                mp.start();
                break;
            case "Eagle":
                tvAnimalName.setText(animal);
                tvAnimalInfo.setText(EAGLE);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.tropical);
                mp.start();
                break;
            case "Penguin":
                tvAnimalName.setText(animal);
                tvAnimalInfo.setText(PENGUIN);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.penguin3);
                mp.start();
                break;
            case "Bat":
                tvAnimalName.setText(animal);
                tvAnimalInfo.setText(BAT);
                mp = MediaPlayer.create(getApplicationContext(), R.raw.bat4);
                mp.start();
                break;
        }

    }
}
