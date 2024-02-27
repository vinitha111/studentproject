package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Student;
import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.MyNewException;
import com.example.demo.exception.NoOneExistException;

public interface StudentService {
	public Student addStudent(Student student) throws AlreadyExistsException;

	public List<Student> getlist();

	public List<Student> searchbyCity(String x) throws NoOneExistException, MyNewException;

	public Optional<Student> getById(int id) throws NoOneExistException;

	public void removeStudent(int id) throws NoOneExistException;

	public void updateStudent(int id, Student st) throws NoOneExistException;

}
