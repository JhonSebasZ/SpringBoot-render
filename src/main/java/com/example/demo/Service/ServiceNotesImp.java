package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Note;
import com.example.demo.Repository.RepositoryNotes;

@Service
public class ServiceNotesImp implements ServiceNotes{
	
	@Autowired
	private RepositoryNotes repositoryNotes;
	
	@Override
	public Note createNote(Note note) {
		Note newNote = repositoryNotes.save(note);
		return newNote;
	}

	@Override
	public Optional<Note> viewNoteId(int id) {
		Optional<Note> findNote = repositoryNotes.findById(id);
		return findNote;
	}

	@Override
	public List<Note> readNotes() {
		List<Note> listNote = repositoryNotes.findAll();
		return listNote;
	}

	@Override
	public Note updateNote(Note note) {
		Note updateNote = repositoryNotes.save(note);
		return updateNote;
	}

	@Override
	public void deleteNote(int id) {
		repositoryNotes.deleteById(id);;
	}

}
