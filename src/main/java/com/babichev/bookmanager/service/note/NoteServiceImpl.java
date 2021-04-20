package com.babichev.bookmanager.service.note;

import com.babichev.bookmanager.entity.Note;
import com.babichev.bookmanager.repository.note.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    private NoteRepository noteRepository;

    @Autowired
    public NoteServiceImpl(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @Override
    public Note add(Note note, int bookId) {
        if(note != null) {
            return noteRepository.add(note, bookId);
        }

        return null;
    }

    @Override
    public Note get(int id, int bookId) {
        return noteRepository.get(id, bookId);
    }

    @Override
    public void remove(int id, int bookId) {
        noteRepository.remove(id, bookId);
    }

    @Override
    public List<Note> getAll(int bookId) {
        return noteRepository.getAll(bookId);
    }
}