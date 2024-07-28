package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Note;

@Repository
public interface RepositoryNotes extends JpaRepository<Note, Integer>{

}
