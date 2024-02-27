package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.entity.Teacher;
import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.NoOneExistException;

public interface TeacherService {
	public Teacher addTeacher(Teacher teacher) throws AlreadyExistsException;

	public List<Teacher> getList();

	public Optional<Teacher> getById(int id) throws NoOneExistException;

	public void removeTeacher(int id) throws NoOneExistException;

	public void updateTeacher(int id, Teacher t) throws NoOneExistException;

}
