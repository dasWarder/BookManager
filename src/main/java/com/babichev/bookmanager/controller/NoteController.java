package com.babichev.bookmanager.controller;

import com.babichev.bookmanager.entity.Book;
import com.babichev.bookmanager.entity.Note;
import com.babichev.bookmanager.exception.BookNotFoundException;
import com.babichev.bookmanager.service.book.BookService;
import com.babichev.bookmanager.service.note.NoteService;
import com.babichev.bookmanager.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@Slf4j
public class NoteController {

    private NoteService noteService;

    private BookService bookService;

    @Autowired
    public NoteController(NoteService noteService, BookService bookService) {
        this.noteService = noteService;
        this.bookService = bookService;
    }


    @GetMapping(value = "/books/book/{id}/notes")
    public String getAll(@PathVariable("id") int bookId, Model model) {
        log.info("Get notes for book with id {}", bookId);
        List<Note> all = noteService.getAll(bookId);

        model.addAttribute("bookId", bookId);
        model.addAttribute("notes", all);

        return "bookNotes";

    }


    @PostMapping(value = "/books/book/{id}/notes/add")
    public String add(@PathVariable("id") int bookId, @RequestParam("comment") String comment,
                      Model model) throws BookNotFoundException {

        log.info("Add a note for book with id {}", bookId);
        Note note = noteService.add(new Note(LocalDateTime.now(), comment), bookId);
        log.info("Note with id {} was successfully added", note.getId());

        model.addAttribute("comment", note);
        log.info("Get all notes for book with id {}", bookId);
        model.addAttribute("notes", noteService.getAll(bookId));
        model.addAttribute("bookId", bookId);

        return "bookNotes";
    }

    @GetMapping(value = "/books/book/{id}/{noteId}")
    public String remove(@PathVariable("id") int bookId, @PathVariable("noteId") int noteId, Model model) {
        log.info("Remove note with id {} for book with id {}", noteId, bookId);
        noteService.remove(noteId, bookId);
        List<Note> notes = noteService.getAll(bookId);

        model.addAttribute("bookId", bookId);
        model.addAttribute("notes", notes);

        return "bookNotes";
    }

}
