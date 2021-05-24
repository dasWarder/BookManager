package com.babichev.bookmanager.repository.note;


import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Note;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import static java.util.Objects.isNull;

/**
 * An implementation of NoteRepository interface
 */
@Repository
public class NoteRepositoryJpa implements NoteRepository {

    /**
     * An object of entity manager to interact with the database
     */
    @PersistenceContext
    private EntityManager em;


    /**
     * The method that implements to save command
     * @param note an entity of the note, that must be stored to the database
     * @param bookId the ID of a book which one the note is belong
     * @return the note stored to the database
     */
    @Override
    @Transactional
    public Note add(Note note, int bookId) {
        /**
         * @see EntityManager#getReference(Class, Object) 
         */
        note.setBook(em.getReference(Book.class, bookId));

        if(isNull(note.getId())) {
            /**
             * @see EntityManager#persist(Object) 
             */
            em.persist(note);
            return note;
        } else if (isNull(get(note.getId(), bookId))) {
            return null;
        }

        /**
         * @see EntityManager#merge(Object) 
         */
        return em.merge(note);
    }

    /**
     * Th method that implements to get command
     * @param id the ID of a note, that must be gotten form the database
     * @param bookId the ID of a book which one the note is belong
     * @return the note from the database
     */
    @Override
    public Note get(int id, int bookId) {
        /**
         * @see EntityManager#find(Class, Object) 
         */
        Note note = em.find(Note.class, id);

        return !isNull(note)
                && note.getBook().getId() == bookId? note : null;
    }

    /**
     * THe method that implements to remove command
     * @param id the ID of a note, that must be removed
     * @param bookId the ID of a book which one the note is belong
     */
    @Override
    @Transactional
    public void remove(int id, int bookId) {
        Note note = get(id, bookId);

        /**
         * @see EntityManager#remove(Object) 
         */
        em.remove(note);
    }

    /**
     * the method that implements to getAll command
     * @param bookId the ID of the book for which all notes must be received
     * @return the list of notes received from the database
     */
    @Override
    public List<Note> getAll(int bookId) {
        /**
         * @see EntityManager#createQuery(String) 
         */
        Query select_n_from_note_where_book_id = em.createQuery("SELECT n FROM Note n WHERE n.book.id=:bookId")
                .setParameter("bookId", bookId);

        return select_n_from_note_where_book_id.getResultList();
    }
}
