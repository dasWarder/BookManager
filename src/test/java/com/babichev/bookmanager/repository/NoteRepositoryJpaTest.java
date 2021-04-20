package com.babichev.bookmanager.repository;

import com.babichev.bookmanager.data.TestData;
import com.babichev.bookmanager.entity.Details;
import com.babichev.bookmanager.entity.Note;
import com.babichev.bookmanager.repository.detail.DetailsRepository;
import com.babichev.bookmanager.repository.note.NoteRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static com.babichev.bookmanager.data.TestData.*;
import static com.babichev.bookmanager.data.TestData.FIRST_BOOK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@Sql(scripts = {"/db/clearDb.sql", "/db/populateDb.sql"})
public class NoteRepositoryJpaTest {

    @Autowired
    private NoteRepository noteRepository;

    @Test
    public void add() {
        Note note = createNote();
        Note add = noteRepository.add(note, FIRST_BOOK.getId());
        note.setId(add.getId());

        Assert.assertEquals(note, add);
    }

    @Test
    public void update() {
        Note note = noteRepository.get(FIRST_NOTE.getId(), SECOND_BOOK.getId());
        int id = note.getId();
        Note forUpdate = TestData.updateNote(note);
        forUpdate = noteRepository.add(forUpdate, SECOND_BOOK.getId());

        assertThat(forUpdate)
                .usingRecursiveComparison()
                .ignoringFields("book")
                .isEqualTo(noteRepository.get(id, SECOND_BOOK.getId()));
    }

    @Test
    public void getById() {
        Note note = noteRepository.get(FIRST_NOTE.getId(), SECOND_BOOK.getId());
        Note firstNote = FIRST_NOTE;
        firstNote.setBook(note.getBook());

        assertThat(firstNote)
                .usingRecursiveComparison()
                .ignoringFields("book")
                .isEqualTo(note);
    }

    @Test
    public void remove() {
        Note note = FIRST_NOTE;

        noteRepository.remove(note.getId(), SECOND_BOOK.getId());

        Assert.assertEquals(null, noteRepository.get(note.getId(), SECOND_BOOK.getId()));
    }


    @Test
    public void getAll() {
        List<Note> testNotes = notes;

        List<Note> originNotes = noteRepository.getAll(SECOND_BOOK.getId());

        assertThat(testNotes)
                .usingRecursiveComparison()
                .ignoringFields("book")
                .isEqualTo(originNotes);
    }
}