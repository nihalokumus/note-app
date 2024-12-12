package com.example.note_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note_app.adapter.NoteAdapter;
import com.example.note_app.model.Database;
import com.example.note_app.model.Not;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements NoteListener {
    ImageView imageAddNote;
    Database db;

    NoteAdapter adapter;
    RecyclerView recyclerView;
    ArrayList <Not> notListesi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
       /* ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }

    void initialize() {
        /*------------------------------*/
        Database db = new  Database (this);
        notListesi= new ArrayList<>();

        /*------------------------------*/
        imageAddNote = findViewById(R.id.imageAddNote);
        recyclerView = findViewById(R.id.recyclerviewNotlar);
        /*------------------------------*/

        notListesi.addAll(db.getNotlarım());

        adapter = new NoteAdapter(notListesi, this);
        recyclerView.setAdapter(adapter);


                imageAddNote.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), NoteDetailsActivity.class);
                        intent.putExtra("type", "newNote");
                        startActivity(intent);
                    }
                });
    }
    void updateNotListesi(){
        notListesi.clear();
        notListesi.addAll(db.getNotlarım());
        adapter.notifyDataSetChanged();

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateNotListesi();


    }

    @Override
    public void NoteClick(Not not) {

    }
}