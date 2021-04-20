package com.babichev.bookmanager.service.note;

import com.babichev.bookmanager.entity.Note;

import java.util.List;

public interface NoteService {
    Note add(Note note, int bookId);

    Note get(int id, int bookId);

    void remove(int id, int bookId);

    List<Note> getAll(int bookId);
}
