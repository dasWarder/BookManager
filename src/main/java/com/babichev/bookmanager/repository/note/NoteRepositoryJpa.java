package com.babichev.bookmanager.repository.note;


import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Note;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class NoteRepositoryJpa implements NoteRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Note add(Note note, int bookId) {
        note.setBook(em.getReference(Book.class, bookId));
        if(note.getId() == null) {
            em.persist(note);
            return note;
        } else if (get(note.getId(), bookId) == null) {
            return null;
        }

        return em.merge(note);
    }

    @Override
    public Note get(int id, int bookId) {
        Note note = em.find(Note.class, id);

        return note != null && note.getBook().getId() == bookId? note : null;
    }

    @Override
    @Transactional
    public void remove(int id, int bookId) {
        Note note = get(id, bookId);
        em.remove(note);
    }

    @Override
    public List<Note> getAll(int bookId) {
        Query select_n_from_note_where_book_id = em.createQuery("SELECT n FROM Note n WHERE n.book.id=:bookId")
                .setParameter("bookId", bookId);

        return select_n_from_note_where_book_id.getResultList();
    }
}
