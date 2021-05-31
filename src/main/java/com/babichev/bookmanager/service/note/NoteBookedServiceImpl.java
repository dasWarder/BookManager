package com.babichev.bookmanager.service.note;

import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Note;
import com.babichev.bookmanager.exception.BookNotFoundException;
import com.babichev.bookmanager.repository.book.BookRepository;
import com.babichev.bookmanager.repository.note.NoteRepository;
import com.babichev.bookmanager.service.AbstractBookedService;
import com.babichev.bookmanager.util.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;


/**
 * The service class that implements NoteService interface
 */
@Service
public class NoteBookedServiceImpl extends AbstractBookedService implements NoteService {

    /**
     * The field with a note repository bean
     * @see NoteRepository
     */
    private NoteRepository noteRepository;

    public NoteBookedServiceImpl(BookRepository bookRepository, SecurityUtil securityUtil, NoteRepository noteRepository) {
        super(bookRepository, securityUtil);
        this.noteRepository = noteRepository;
    }

    /**
     * The method to validate a note object for to save command
     * @param note a note object that must be validated
     * @param bookId the ID of a book which one the note is belong
     * @return the note object that received from the repository layer
     */
    @Override
    @Transactional
    public Note add(Note note, int bookId) throws BookNotFoundException {
        Book bookFromDb = bookRepository.getBookByIdAndCustomer_Id(bookId, getLoggedUserId());

        Assert.notNull(note, "note must not be null");
        Assert.notNull(bookFromDb, "the book must be not null");

        if(note.getBook() == null) {

            note.setBook(bookFromDb);
            /**
             * @see NoteRepository#add(Note, int)
             */
            return noteRepository.save(note);
        }

        Book book = note.getBook();

        if(book.getId() == bookId) {
            return noteRepository.save(note);
        }

        throw new BookNotFoundException("Wrong book id");
    }

    /**
     * The method to validate a note object for to get command
     * @param id the ID of a note
     * @param bookId the ID of a book which one the note is belong
     * @return the note object from the database
     */
    @Override
    @Transactional
    public Note get(int id, int bookId) {
        /**
         * @see NoteRepository#get(int, int)
         */
        return noteRepository.getNoteByIdAndBook_Id(id, bookId);
    }

    /**
     * The method to validate a note object for to remove command
     * @param id the ID of a note
     * @param bookId the ID of a book which one the note is belong
     */
    @Override
    @Transactional
    public void remove(int id, int bookId) {
        /**
         * @see NoteRepository#remove(int, int)
         */
        noteRepository.deleteNoteByIdAndBook_Id(id, bookId);
    }

    /**
     * The method to validate a note object for to getAll command
     * @param bookId the ID of a book which one the list of notes is belong
     * @return the list of all note objects for a customer with @param bookId from the database
     */
    @Override
    public List<Note> getAll(int bookId) {
        return noteRepository.getNotesByBook_Id(bookId);
    }
}
