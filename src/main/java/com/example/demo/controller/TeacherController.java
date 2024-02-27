package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Teacher;
import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.NoOneExistException;
import com.example.demo.service.TeacherService;

@RequestMapping("/api")
@RestController

public class TeacherController {
	@Autowired
	private TeacherService tecser;

//	@PostMapping("/add")
//	public ResponseEntity<Student> savestudent(@RequestBody Student sttd) throws AlreadyExistsException {
//		Student savest=stuser.addStudent(sttd);
//		return new ResponseEntity<>(savest,HttpStatus.CREATED);
//		
//	}
	@PostMapping("/teacher/save")
	public void saveTeacher(@RequestBody Teacher td) throws AlreadyExistsException, NoOneExistException {
		tecser.addTeacher(td);
	}

	@GetMapping("/teacher/list")
	public ResponseEntity<List<Teacher>> getlist() {
		return new ResponseEntity<>(tecser.getList(), HttpStatus.OK);
	}

	@GetMapping("/teacher/list/{id}")
	public ResponseEntity<Teacher> getTeacher(@PathVariable int id) throws NoOneExistException {
		Optional<Teacher> t = tecser.getById(id);
		if (t.isPresent()) {
			return new ResponseEntity<>(t.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping("/techer/update/{id}")
	public ResponseEntity<Teacher> updateTeacher(@PathVariable int id, @RequestBody Teacher td)
			throws NoOneExistException, AlreadyExistsException {
		Optional<Teacher> t = tecser.getById(id);
		tecser.updateTeacher(t.get().getId(), td);

		if (t.isPresent())

		{
			t.get().setName(td.getName());

			return new ResponseEntity<>(tecser.addTeacher(t.get()), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/del/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) throws NoOneExistException {
		Optional<Teacher> t = tecser.getById(id);
		if (t.isPresent()) {
			tecser.removeTeacher(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

}
