package com.ensolvers.repository;

import com.ensolvers.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByArchivedFalse();
    List<Note> findByArchivedTrue();
    List<Note> findByCategoriesContaining(String category);
}
