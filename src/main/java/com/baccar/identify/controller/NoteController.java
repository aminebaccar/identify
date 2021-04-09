package com.baccar.identify.controller;

import com.baccar.identify.ResourceNotFoundException;
import com.baccar.identify.models.Note;
import com.baccar.identify.repositories.NoteRepository;
import com.baccar.identify.repositories.PictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class NoteController {

    @Autowired
    private NoteRepository noteRepository;
    @Autowired
    private PictureRepository pictureRepository;

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    @GetMapping("/notes/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable(value = "id") Long picId)
            throws ResourceNotFoundException {
        Note note = noteRepository.findById(picId)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found for this id :: " + picId));
        return ResponseEntity.ok().body(note);
    }

    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        Long picture_id = note.getPicture().getId();
        note.setPicture(pictureRepository.findById(picture_id).get());
        note.setDate_created(new Timestamp(new Date().getTime())+"");
        return noteRepository.save(note);
    }

    @PutMapping("/notes/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable(value = "id") Long picId,
                                           @Valid @RequestBody Note noteDetails) throws ResourceNotFoundException {
        Note note = noteRepository.findById(picId)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found for this id :: " + picId));

        note.setContent(noteDetails.getContent());
        note.setDate_updated(new Timestamp(new Date().getTime()) + "");
        final Note updatedNote = noteRepository.save(note);
        return ResponseEntity.ok(updatedNote);
    }

    @DeleteMapping("/notes/{id}")
    public Map<String, Boolean> deleteNote(@PathVariable(value = "id") Long picId)
            throws ResourceNotFoundException {
        Note note = noteRepository.findById(picId)
                .orElseThrow(() -> new ResourceNotFoundException("Note not found for this id :: " + picId));

        noteRepository.delete(note);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
