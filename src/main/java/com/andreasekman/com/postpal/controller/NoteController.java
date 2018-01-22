package com.andreasekman.com.postpal.controller;

import com.andreasekman.com.postpal.model.Note;
import com.andreasekman.com.postpal.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class NoteController {

    @Autowired
    private NoteService noteService;

    // Get All Notes
    @RequestMapping(
            value = "/notes",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Note>> getNotes() {
        Collection<Note> notes = noteService.findAll();

        return new ResponseEntity<Collection<Note>>(notes, HttpStatus.OK);
    }

    // Get a Single Note
    @RequestMapping(
            value = "/notes/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> getNoteById(@PathVariable(value = "id") Long id) {
        Note note = noteService.findOne(id);
        if(note == null) {
            return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok().body(note);
    }

    // Create a new Note
    @RequestMapping(
            value = "/notes",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> createNote (@RequestBody Note note) {
        Note savedNote = noteService.create(note);
        return new ResponseEntity<Note>(savedNote, HttpStatus.CREATED);
    }

    // Update a Note
    @RequestMapping(
            value = "/notes/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Note> updateNote(@RequestBody Note note) {
        Note updatedNote = noteService.update(note);
        if(updatedNote == null) {
            return new ResponseEntity<Note>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Note>(updatedNote, HttpStatus.OK);
    }

    // Delete a Note
    @RequestMapping(
            value = "/notes/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Note> deleteNote (@PathVariable Long id) {
        noteService.delete(id);

        return new ResponseEntity<Note>(HttpStatus.NO_CONTENT);
    }

    //TODO implement find by
    // Get a Note's containing
    @RequestMapping(value = "/find/{input}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Note>> getNoteContaining(@PathVariable String input) {
        Collection<Note> notes = noteService.findByContent(input, input);
        if(notes == null) {
            return new ResponseEntity<Collection<Note>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<Collection<Note>>(notes, HttpStatus.OK);
    }

}

