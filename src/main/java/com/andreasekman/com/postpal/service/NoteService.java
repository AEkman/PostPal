package com.andreasekman.com.postpal.service;

import com.andreasekman.com.postpal.model.Note;

import java.util.Collection;

public interface NoteService {
    Collection<Note> findAll();

    Note findOne(Long id);

    Note create(Note note);

    Note update(Note note);

    Collection<Note> findByContent(String title, String content);

    void delete(Long id);

    void evictCache();
}
