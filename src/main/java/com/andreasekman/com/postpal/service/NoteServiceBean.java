package com.andreasekman.com.postpal.service;

import com.andreasekman.com.postpal.model.Note;
import com.andreasekman.com.postpal.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class NoteServiceBean implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Collection<Note> findAll() {
        Collection<Note> notes = noteRepository.findAll();
        return notes;
    }

    @Override
    public Note findOne(Long id) {
        Note note = noteRepository.findOne(id);
        return note;
    }

    @Override
    public Note create(Note note) {
        if(note.getId() != null) {
            // TODO cannot create note with specified id
            return null;
        }
        Note savedNote = noteRepository.save(note);
        return savedNote;
    }

    @Override
    public Note update(Note note) {
        Note notePersisted = findOne((note.getId()));
        if (notePersisted == null) {
            // TODO cannot update greeting that hasnt been persisted
            return null;
        }
        Note updateNote = noteRepository.save(note);
        return updateNote;
    }

    @Override
    public void delete(Long id) {

        noteRepository.delete(id);
    }

    @Override
    public Collection<Note> findByContent(String content) {
        Collection<Note> notes = noteRepository.findByContentContaining(content);
        return notes;
    }
}
