package com.andreasekman.com.postpal.service;

import com.andreasekman.com.postpal.model.Note;
import com.andreasekman.com.postpal.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;

@Service
@Transactional(
        propagation = Propagation.SUPPORTS,
        readOnly = true)
public class NoteServiceBean implements NoteService {

    @Autowired
    private NoteRepository noteRepository;

    @Override
    public Collection<Note> findAll() {
        Collection<Note> notes = noteRepository.findAll();
        return notes;
    }

    @Override
    @Cacheable(value = "notes", key ="#id")
    public Note findOne(Long id) {
        Note note = noteRepository.findOne(id);
        return note;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @CachePut(value = "notes", key ="#result.id")
    public Note create(Note note) {
        if(note.getId() != null) {
            // TODO cannot create note with specified id
            return null;
        }
        Note savedNote = noteRepository.save(note);
        return savedNote;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @CachePut(value = "notes", key ="#note.id")
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
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    @CacheEvict(value = "notes", key = "id")
    public void delete(Long id) {

        noteRepository.delete(id);
    }

    @Override
    public Collection<Note> findByContent(String title, String content) {
        Collection<Note> notes = noteRepository.findByTitleContainingOrContentContaining(title, content);
        return notes;
    }

    @Override
    @CacheEvict(value = "notes", allEntries = true)
    public void evictCache() {

    }
}
