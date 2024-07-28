package com.example.demo.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Entity.Note;
import com.example.demo.Service.ServiceNotesImp;

@Controller
@RequestMapping("/notes")
public class ControllerNote {
	
	@Autowired
	ServiceNotesImp serviceImp;
	
	@GetMapping("")
	public String verPaginaDeInicio(Model model) {
		var notes = serviceImp.readNotes();
		model.addAttribute("notes", notes);
		return "index";
	}
	
	@GetMapping("/add")
	public String add(Note note) {
		return "form";
	}
	
	@GetMapping("/edit/{idTask}")
	public String edit(@PathVariable int idTask, Note note, Model model) {
		Optional<Note> uypdateNote = serviceImp.viewNoteId(idTask);
		model.addAttribute("note", uypdateNote);
		return "form";
	}
	
	@GetMapping("/delete/{idTask}")
	public String delete(@PathVariable int idTask) {
		serviceImp.deleteNote(idTask);
		return "redirect:/notes";
	}
	
	@PostMapping("/save")
	public String save(Note note) {
		serviceImp.createNote(note);
		return "redirect:/notes";
	}
	
	@GetMapping("/complete/{idTask}")
	public String complete(@PathVariable int idTask) {
		Optional<Note> updateNote = serviceImp.viewNoteId(idTask);
		if(updateNote.isPresent()) {
			Note note = updateNote.get();
			note.setComplete(true);
			serviceImp.createNote(note);
		}
		return "redirect:/notes";
	}
}
