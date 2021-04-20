package com.babichev.bookmanager.controller;

import com.babichev.bookmanager.entity.Note;
import com.babichev.bookmanager.service.note.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class NoteController {

    private NoteService noteService;

    @Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @GetMapping(value = "/books/book/{id}/notes")
    public String show(@PathVariable("id") int id, Model model) {
        List<Note> all = noteService.getAll(id);

        model.addAttribute("notes", all);

        return "bookNotes";

    }
}
