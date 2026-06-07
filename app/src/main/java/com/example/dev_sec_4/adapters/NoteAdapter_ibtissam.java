package com.example.dev_sec_4.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.dev_sec_4.R;
import com.example.dev_sec_4.database.Note_ibtissam;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NoteAdapter_ibtissam extends RecyclerView.Adapter<NoteAdapter_ibtissam.NoteViewHolder_ibtissam> {
    
    private final List<Note_ibtissam> notesList_ibtissam;
    
    public NoteAdapter_ibtissam(List<Note_ibtissam> notesList_ibtissam) {
        this.notesList_ibtissam = notesList_ibtissam;
    }
    
    @NonNull
    @Override
    public NoteViewHolder_ibtissam onCreateViewHolder(@NonNull ViewGroup parent_ibtissam, int viewType_ibtissam) {
        View view_ibtissam = LayoutInflater.from(parent_ibtissam.getContext()).inflate(R.layout.item_note_ibtissam, parent_ibtissam, false);
        return new NoteViewHolder_ibtissam(view_ibtissam);
    }
    
    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder_ibtissam holder_ibtissam, int position_ibtissam) {
        Note_ibtissam note_ibtissam = notesList_ibtissam.get(position_ibtissam);
        holder_ibtissam.bind_ibtissam(note_ibtissam);
    }
    
    @Override
    public int getItemCount() {
        return notesList_ibtissam.size();
    }
    
    static class NoteViewHolder_ibtissam extends RecyclerView.ViewHolder {
        private final TextView tvNoteTitle_ibtissam;
        private final TextView tvNoteContent_ibtissam;
        private final TextView tvNoteDate_ibtissam;
        
        public NoteViewHolder_ibtissam(@NonNull View itemView_ibtissam) {
            super(itemView_ibtissam);
            tvNoteTitle_ibtissam = itemView_ibtissam.findViewById(R.id.tvNoteTitle_ibtissam);
            tvNoteContent_ibtissam = itemView_ibtissam.findViewById(R.id.tvNoteContent_ibtissam);
            tvNoteDate_ibtissam = itemView_ibtissam.findViewById(R.id.tvNoteDate_ibtissam);
        }
        
        public void bind_ibtissam(Note_ibtissam note_ibtissam) {
            tvNoteTitle_ibtissam.setText(note_ibtissam.getTitle_ibtissam());
            tvNoteContent_ibtissam.setText(note_ibtissam.getContent_ibtissam());
            
            SimpleDateFormat sdf_ibtissam = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
            String date_ibtissam = sdf_ibtissam.format(new Date(note_ibtissam.getCreatedAt_ibtissam()));
            tvNoteDate_ibtissam.setText(date_ibtissam);
        }
    }
}
