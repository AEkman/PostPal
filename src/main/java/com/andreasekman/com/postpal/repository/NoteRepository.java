package com.andreasekman.com.postpal.repository;

import com.andreasekman.com.postpal.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{
    Collection<Note> findByTitleContainingOrContentContaining(String title, String content);
}
