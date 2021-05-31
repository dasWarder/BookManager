package com.babichev.bookmanager.repository.note;

import com.babichev.bookmanager.entity.Note;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * An interface describes the layer between the service and the database for the note entity
 */
@Repository
public interface NoteRepository extends CrudRepository<Note, Integer> {

    /**
     * The method to get a note by its id and bookId
     * @param noteId the id of note
     * @param bookId the id of book which one the note is belong
     * @return the note received from the database
     */
    Note getNoteByIdAndBook_Id(int noteId, int bookId);

    /**
     * The method to remove a note by its id and bookId
     * @param noteId the id of the note
     * @param bookId the id of the book which one the note is belong
     */
    @Transactional
    void deleteNoteByIdAndBook_Id(int noteId, int bookId);

    /**
     * The method to receive a collection of notes for book with id=@param bookId
     * @param bookId the book id which one the notes are belongs
     * @return the collection of the notes
     */
    List<Note> getNotesByBook_Id(int bookId);
}
