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

import com.example.demo.entity.Student;
import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.MyNewException;
import com.example.demo.exception.NoOneExistException;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping("/api")

public class StudentController {

	@Autowired
	private StudentService stuser;

//	@PostMapping("/add")
//	public ResponseEntity<Student> savestudent(@RequestBody Student sttd) throws AlreadyExistsException {
//		Student savest=stuser.addStudent(sttd);
//		return new ResponseEntity<>(savest,HttpStatus.CREATED);
//		
//	}
	@PostMapping("/student/save")
	public void saveStudent1(@RequestBody Student sttd) throws AlreadyExistsException {
		stuser.addStudent(sttd);
	}

	@GetMapping("/student/list")
	public ResponseEntity<List<Student>> getliset() {
		return new ResponseEntity<>(stuser.getlist(), HttpStatus.OK);
	}

	@GetMapping("/student/list/{id}")
	public ResponseEntity<Student> getstudent(@PathVariable int id) throws NoOneExistException {
		Optional<Student> st = stuser.getById(id);
		if (st.isPresent()) {
			return new ResponseEntity<>(st.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@GetMapping("/student/search/{loc}")
	public ResponseEntity<List<Student>> searchbyCity(@PathVariable String loc) throws MyNewException, NoOneExistException {
		List<Student> st = stuser.searchbyCity(loc);
		if (st.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(st, HttpStatus.OK);
		}
	}

	@PutMapping("student/update/{id}")
	public ResponseEntity<Student> updatestudent(@PathVariable int id, @RequestBody Student std)
			throws AlreadyExistsException, NoOneExistException {
		Optional<Student> st = stuser.getById(id);
		stuser.updateStudent(st.get().getId(), std);

		if (st.isPresent())

		{
			st.get().setName(std.getName());

			return new ResponseEntity<>(stuser.addStudent(st.get()), HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}

	@DeleteMapping("/student/del/{id}")
	public ResponseEntity<Void> delete(@PathVariable int id) throws NoOneExistException {
		Optional<Student> st = stuser.getById(id);
		if (st.isPresent()) {
			stuser.removeStudent(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
}
