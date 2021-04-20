package com.babichev.bookmanager.repository.note;

import com.babichev.bookmanager.entity.Note;

import java.util.List;

public interface NoteRepository {
    Note add(Note note, int bookId);

    Note get(int id, int bookId);

    void remove(int id, int bookId);

    List<Note> getAll(int bookId);
}
