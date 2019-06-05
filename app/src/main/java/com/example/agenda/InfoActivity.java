package com.example.agenda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        text = (TextView) findViewById(R.id.textView5);

        Bundle extras = getIntent().getExtras();
        int texto = extras.getInt("texto", 0);
        text.setText(getText(texto));


    }
}
