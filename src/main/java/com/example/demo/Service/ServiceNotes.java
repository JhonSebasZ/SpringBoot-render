package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Entity.Note;

public interface ServiceNotes {
	public Note createNote(Note note);
	public Optional<Note> viewNoteId(int id);
	public List<Note> readNotes();
	public Note updateNote(Note note);
	public void deleteNote(int id);
}
