package com.example.note_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.note_app.model.Database;
import com.example.note_app.model.Not;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class NoteDetailsActivity extends AppCompatActivity {

    String type = null;
    EditText inputTitle, inputNote;
    TextView textDate;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        db = new Database(this);
        type = getIntent().getStringExtra("type");

        inputTitle = findViewById(R.id.inputTitle);
        inputNote = findViewById(R.id.inputNote);
        textDate = findViewById(R.id.textDate);

        textDate.setText(new SimpleDateFormat("EEEE, dd MMMM yyyy HH:mm", Locale.getDefault()).format(new Date()));

        findViewById(R.id.imageSave).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (inputTitle.getText().toString().trim().isEmpty()) {
                    inputTitle.setError("Başlık Giriniz");
                } else if (inputNote.getText().toString().trim().isEmpty()) {
                    inputNote.setError("Not Giriniz");
                } else {
                    db.yeniNot (new Not(inputTitle.getText().toString(), inputNote.getText().toString(), null, null, null, null, textDate.getText().toString()));
                    finish();
                }
            }
        });

        findViewById(R.id.imageBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
