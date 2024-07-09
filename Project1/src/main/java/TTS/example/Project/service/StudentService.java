package TTS.example.Project.service;

import TTS.example.Project.model.Student;
import TTS.example.Project.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StudentService {
    @Autowired
    private StudentRepo studentRepo;

    public List<Student> getAllStudent(){
        return studentRepo.findAll();
    }

    public Student addAndUpdate(Student student){
        return studentRepo.save(student);
    }

    public void delete(Student student){
        studentRepo.delete(student);
    }

    public Optional<Student> getStudentById(Integer id){
        return studentRepo.findById(id);
    }
}
