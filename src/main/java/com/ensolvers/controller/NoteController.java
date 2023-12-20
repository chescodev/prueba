package com.ensolvers.controller;

import com.ensolvers.model.Note;
import com.ensolvers.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/notes")
public class NoteController {
    @Autowired
    private NoteRepository noteRepository;

    @PostMapping
    public Note addNote(@RequestBody Note note) {
        return noteRepository.save(note);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note updatedNote) {
        Optional<Note> existing = noteRepository.findById(id);
        if (existing.isPresent()) {
            Note curuent = existing.get();
            curuent.setTitle(updatedNote.getTitle());
            curuent.setBody((updatedNote.getBody()));

            noteRepository.save(curuent);
            return ResponseEntity.ok(curuent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/archive")
    public ResponseEntity<Note> archiveNote(@PathVariable Long id) {
        Optional<Note> existing = noteRepository.findById(id);
        if (existing.isPresent()) {
            Note current = existing.get();
            current.setArchived(true);
            noteRepository.save(current);
            return ResponseEntity.ok(current);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/unarchive")
    public ResponseEntity<Note> unarchiveNote(@PathVariable Long id) {
        Optional<Note> existing = noteRepository.findById(id);
        if (existing.isPresent()) {
            Note current = existing.get();
            current.setArchived(false);
            noteRepository.save(current);
            return ResponseEntity.ok(current);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/active")
    public List<Note> getActiveNotes() {
        return noteRepository.findByArchivedFalse();
    }

    @GetMapping("/archived")
    public List<Note> getArchivedNotes() {
        return noteRepository.findByArchivedTrue();
    }

    @PostMapping("/{id}/add-category")
    public ResponseEntity<Note> addCategory(@PathVariable Long id, @RequestParam String category) {
        Optional<Note> existing = noteRepository.findById(id);
        if (existing.isPresent()) {
            Note current = existing.get();
            current.getCategories().add(category);
            noteRepository.save(current);
            return ResponseEntity.ok(current);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/remove-category")
    public ResponseEntity<Note> deleteCategory(@PathVariable Long id, @RequestParam String category) {
        Optional<Note> existing = noteRepository.findById(id);
        if (existing.isPresent()) {
            Note current = existing.get();
            current.getCategories().remove(category);
            noteRepository.save(current);
            return ResponseEntity.ok(current);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/by-category")
    public List<Note> getNotesByCategory(@RequestParam String category) {
        return noteRepository.findByCategoriesContaining(category);
    }

}
