package TTS.example.Project.service;

import TTS.example.Project.model.Course;
import TTS.example.Project.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    public Optional<Course> findById(Integer id) {
        return courseRepo.findById(id);
    }

    public Course addAndUpdate(Course course){
        return courseRepo.save(course);
    }

    public void delete(Course course){
        courseRepo.delete(course);
    }

    public List<Course> getAllCourses(){
        return courseRepo.findAll();
    }


}
