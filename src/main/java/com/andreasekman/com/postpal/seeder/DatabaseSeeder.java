package com.andreasekman.com.postpal.seeder;

import com.andreasekman.com.postpal.model.Note;
import com.andreasekman.com.postpal.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DatabaseSeeder implements CommandLineRunner {
    private NoteRepository noteRepository;

    @Autowired
    public DatabaseSeeder(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        List<Note> note = new ArrayList<>();

        note.add(new Note("Post 1", "Content 1"));
        note.add(new Note("Post 2", "Content 2"));
        note.add(new Note("Post 3", "Content 3"));
        note.add(new Note("Post 4", "Curry banan"));

        noteRepository.save(note);
    }
}
