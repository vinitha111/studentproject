package com.example.demo.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Teacher;
import com.example.demo.exception.AlreadyExistsException;
import com.example.demo.exception.NoOneExistException;
import com.example.demo.repo.TeacherRepo;
import com.example.demo.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
	@Autowired
	TeacherRepo repo;

	@Override
	public Teacher addTeacher(Teacher teacher) throws AlreadyExistsException {
		// Optional<Teacher> t=repo.findById(teacher.getId());
		java.util.Optional<Teacher> t = repo.findById(teacher.getId());
		if (t.isEmpty()) {
			return repo.save(teacher);

		} else {
			throw new AlreadyExistsException("Already existed with this id ");
		}

	}

	@Override
	public List<Teacher> getList() {
		List<Teacher> tl = repo.findAll();
		return tl;

	}

	@Override
	public java.util.Optional<Teacher> getById(int id) throws NoOneExistException {
		java.util.Optional<Teacher> t = repo.findById(id);
		if (t.isEmpty())
			throw new NoOneExistException("No Teacher found with this id :" + id);
		else
			return t;

	}

	@Override
	public void removeTeacher(int id) throws NoOneExistException {
		java.util.Optional<Teacher> t = repo.findById(id);
		if (t.isEmpty()) {
			throw new NoOneExistException("No Teacher found with this id :" + id);
		} else {
			repo.deleteById(id);
		}

	}

	@Override
	public void updateTeacher(int id, Teacher t) throws NoOneExistException {
		java.util.Optional<Teacher> t1 = repo.findById(id);

		try {
			if (t1.isEmpty()) {
				throw new NoOneExistException("No Teacher exist with this id");
			} else {
				Teacher t2 = t1.get();
				t2.setId(t.getId());
				t2.setName(t.getName());
				t2.setPhoneNumber(t.getPhoneNumber());
				repo.save(t2);
			}
		} catch (NoOneExistException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
