package com.example.texttospeech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class SpeechToText extends AppCompatActivity {
    TextView textViewTTs;
    ImageView imageView;
    Switch aSwitch_stt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_to_text);
        textViewTTs = findViewById(R.id.stt_Text);
        imageView = findViewById(R.id.voice);
        aSwitch_stt = findViewById(R.id.Switch_stt);

        aSwitch_stt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SpeechToText.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSpeechInput();
            }
        });

    }

    public void getSpeechInput() {
        Intent u = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        u.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        u.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        u.putExtra(RecognizerIntent.EXTRA_PROMPT, "speak something");

        try {
            startActivityForResult(u, 20);

        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();

        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 20: {
                if (resultCode == RESULT_OK && data != null) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    textViewTTs.setText(result.get(0));
                }
                break;
            }
        }
    }
}
