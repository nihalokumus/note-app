package com.example.note_app.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.note_app.NoteListener;
import com.example.note_app.R;
import com.example.note_app.model.Not;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {

    ArrayList<Not> notListesi;
    Activity activity;
    NoteListener listener;

    public NoteAdapter(ArrayList<Not> notListesi, Activity activity) {
        /*public NoteAdapter(ArrayList<Not> notListesi, Activity activity, NoteListener listener) */
        this.notListesi = notListesi;
        listener= (NoteListener) activity;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NoteViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_note, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        holder.setNote(notListesi.get(position));
    }

    @Override
    public int getItemCount() {
        return notListesi.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {
        CardView cardViewContainer;
        TextView textTitle, textNote, textDate;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewContainer = itemView.findViewById(R.id.cardViewContainer);
            textTitle = itemView.findViewById(R.id.textTitle);
            textNote = itemView.findViewById(R.id.textNote);
            textDate = itemView.findViewById(R.id.textDate);
        }

        void setNote(Not not) {
            textTitle.setText(not.getBaslik());
            textNote.setText(not.getNotMetin());
            textDate.setText(not.getTarih());
            cardViewContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.NoteClick(not);
                    }
                }
            });
        }
    }
}
