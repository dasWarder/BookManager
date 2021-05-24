package com.babichev.bookmanager.repository.note;

import com.babichev.bookmanager.entity.Note;

import java.util.List;

/**
 * An interface describes the layer between the service and the database for the note entity
 */
public interface NoteRepository {

    /**
     * The method to store a new note to database for a book with @param the book ID
     * @param note an entity of the note, that must be stored to the database
     * @param bookId the ID of a book which one the note is belong
     * @return a note object with @param id, stored to database
     */
    Note add(Note note, int bookId);

    /**
     * The method to get a note from the database by its @param ID and @param the book ID
     * @param id the ID of a note, that must be gotten form the database
     * @param bookId the ID of a book which one the note is belong
     * @return the note from a database
     */
    Note get(int id, int bookId);

    /**
     * The method to remove a note from the database by its @param ID and @paramthe book ID
     * @param id the ID of a note, that must be removed
     * @param bookId the ID of a book which one the note is belong
     */
    void remove(int id, int bookId);

    /**
     * The method to get all notes from the database with @param the ID of a book
     * @param bookId the ID of the book for which all notes must be received
     * @return the List of notes from the database
     */
    List<Note> getAll(int bookId);
}
