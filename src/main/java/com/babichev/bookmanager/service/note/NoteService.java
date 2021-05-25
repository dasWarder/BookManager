package com.babichev.bookmanager.service.note;

import com.babichev.bookmanager.entity.Note;

import java.util.List;

/**
 * An interface that describes a behavior of a class on the service layer (between the controller and the repository)
 */
public interface NoteService {

    /**
     * The method to validate a note object for storage it to the database
     * @param note a note object that must be validated
     * @param bookId the ID of a book which one the note is belong
     * @return the note object with ID stored to the database
     */
    Note add(Note note, int bookId);

    /**
     * The method to validate a note object for receiving it from the database
     * @param id the ID of a note
     * @param bookId the ID of a book which one the note is belong
     * @return the note object from the database
     */
    Note get(int id, int bookId);

    /**
     * The method to validate a note object for removing from the database
     * @param id the ID of a note
     * @param bookId the ID of a book which one the note is belong
     */
    void remove(int id, int bookId);

    /**
     * THe method to validate a list of all note objects from the database
     * @param bookId the ID of a book which one the list of notes is belong
     * @return the list of all note objects
     */
    List<Note> getAll(int bookId);
}
