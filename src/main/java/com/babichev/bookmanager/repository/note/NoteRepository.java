package com.babichev.bookmanager.repository.note;

import com.babichev.bookmanager.entity.Note;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * An interface describes the layer between the service and the database for the note entity
 */
public interface NoteRepository extends CrudRepository<Note, Integer> {

    Note getNoteByIdAndBook_Id(int noteId, int bookId);

    @Transactional
    void deleteNoteByIdAndBook_Id(int noteId, int bookId);

    List<Note> getNotesByBook_Id(int bookId);
}
