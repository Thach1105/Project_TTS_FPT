package TTS.example.Project.service;

import TTS.example.Project.model.Course;
import TTS.example.Project.model.Enrollments;
import TTS.example.Project.repository.EnrollmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    @Autowired
    EnrollmentRepo enrollmentRepo;

    public List<Enrollments> findAll(){
        return enrollmentRepo.findAll();
    }

    public Optional<Enrollments> findEnrollments(Long id){
        return enrollmentRepo.findById(id);
    }
    public void deleteEnrollment(Long id){
        enrollmentRepo.deleteById(id);
    }

    public Enrollments addEnrollment(Enrollments enrollment) {
        if (enrollmentRepo.existsByStudentAndCourse(enrollment.getStudent(), enrollment.getCourse())) {
            throw new IllegalArgumentException("Enrollment already exists for this student and course.");
        }
        return enrollmentRepo.save(enrollment);
    }


}
