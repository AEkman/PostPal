package com.andreasekman.com.postpal.repository;

import com.andreasekman.com.postpal.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{
    List<Note> findByContentContaining(String content);
}
