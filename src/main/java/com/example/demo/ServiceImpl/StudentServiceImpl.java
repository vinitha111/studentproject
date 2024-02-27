package com.example.demo.ServiceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Student;
import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.MyNewException;
import com.example.demo.exception.NoOneExistException;
import com.example.demo.repo.StudentRepo;
import com.example.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StudentRepo repo;

	@Override
	public Student addStudent(Student student) throws AlreadyExistsException {
		Optional<Student> ss = repo.findById(student.getId());
		if (ss.isEmpty()) {
			return repo.save(student);

		} else
			throw new AlreadyExistsException("Already student existed with this id " + student.getId());

	}

	@Override
	public List<Student> getlist() {
		List<Student> sl = repo.findAll();
		return sl;
	}

	@Override
	public Optional<Student> getById(int id) throws NoOneExistException {

		Optional<Student> s = repo.findById(id);
		if (s.isEmpty()) {
			throw new NoOneExistException("No Student eist with this id:" + id);
		} else {
			return s;
		}
	}

	@Override
	public void removeStudent(int id) throws NoOneExistException {
		repo.deleteById(id);

	}

	@Override
	public void updateStudent(int id, Student st) throws NoOneExistException {
		Optional<Student> s1 = repo.findById(id);

		try {
			if (s1.isEmpty()) {
				throw new NoOneExistException("No student exist with this id");
			} else {
				Student s = s1.get();
				s.setId(st.getId());
				s.setName(st.getName());
				s.setLocation(st.getLocation());
				repo.save(s);
			}
		} catch (NoOneExistException e) {

			e.printStackTrace();
		}
	}

	@Override
	public List<Student> searchbyCity(String x) throws MyNewException {

		List<Student> list = repo.findAll().stream().filter(s -> s.getLocation().equals(x))
				.collect(Collectors.toList());
		if (list.isEmpty()) {
			throw new MyNewException("No one exist in this location");
		}
		return list;
	}
}
